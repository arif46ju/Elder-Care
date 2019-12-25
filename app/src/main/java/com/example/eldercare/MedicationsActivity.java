package com.example.eldercare;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MedicationsActivity extends AppCompatActivity implements ExampleDialog1.ExampleDialogListener {

    private ListView listView;
    public String itemId,medicin,time;
    databaseHelper myDb;
    SQLiteDatabase db;
    ArrayList<String> countryNames = new ArrayList<>();
    ArrayAdapter<String> adapter, f_adapter;
    ExampleDialog1 exampleDialog=new ExampleDialog1();
    Menu addNew;
    int f=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medications);
        listView = findViewById(R.id.listViewID);
        addNew=findViewById(R.id.addNew);
        myDb = new databaseHelper(this);
        final Cursor res = myDb.getAllMedicin();

        if (res.getCount() == 0) {
            Toast.makeText(getApplicationContext(), "no Medication found", Toast.LENGTH_LONG).show();
        } else {
            countryNames.add("ID" + "     " + "Medication" + "         " + "Time");
            while (res.moveToNext()) {
                String c1,c2;
                c1=res.getString(1);
                c2=res.getString(2);
                if(c1.length()<=15){
                    String tmp="                   ";
                    c1=c1+tmp.substring(0,19-c1.length()-1);
                }
                countryNames.add(res.getString(0) + "      " + c1 + "       " + res.getString(2));


            }
        }



        adapter = new ArrayAdapter<String>(MedicationsActivity.this, R.layout.daily_list_sample_layout,R.id.textViewID, countryNames);
        listView.setAdapter(adapter);
        ListView f_listview = new ListView(getApplicationContext());
        List<String> f_option = new ArrayList<>();
        f_option.add("Delete");
        f_option.add("Edit");
        ColorDrawable color = new ColorDrawable(getResources().getColor(R.color.colorPrimary));
        f_listview.setDivider(color);
        f_listview.setDividerHeight(1);
        f_adapter = new ArrayAdapter<String>(MedicationsActivity.this, android.R.layout.simple_list_item_1, f_option);
        f_listview.setAdapter(f_adapter);
        final AlertDialog.Builder builder = new AlertDialog.Builder(MedicationsActivity.this);
        builder.setTitle("File Options");
        builder.setCancelable(true);
        builder.setView(f_listview);
        final AlertDialog dialog = builder.create();

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                String value = adapter.getItem(position);
                itemId=value.valueOf(value.substring(0,3));
                medicin=value.valueOf(value.substring(6,value.length()-5));
                time=value.valueOf(value.substring(value.length()-5,value.length()));
                // Toast.makeText(getApplicationContext(),itemid,Toast.LENGTH_LONG).show();
                dialog.show();
                return false;
            }
        });
        f_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String value = f_adapter.getItem(position);
                //Toast.makeText(getApplicationContext(), "clicked", Toast.LENGTH_SHORT).show();
                if (value.equals("Delete")) {
                    dialog.cancel();
                    final AlertDialog.Builder builder2 = new AlertDialog.Builder(MedicationsActivity.this);
                    builder2.setCancelable(true);

                    builder2.setMessage("Are you sure you want to delete?");
                    builder2.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            myDb.deleteMedicationData(itemId);
                            finish();
                            startActivity(getIntent());
                        }
                    });

                    builder2.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    final AlertDialog dialog1 = builder2.create();
                    dialog1.show();


                } else if (value.equals("Edit")) {
                    dialog.cancel();
                    openDialog();

                }

            }

        });


    }
    public void openDialog(){

        exampleDialog.id=itemId;
        exampleDialog.medicin=medicin;
        exampleDialog.time=time;
        exampleDialog.show(getSupportFragmentManager(),"example dialog");

    }

    @Override
    public void applyText(String editTextId, String EditTextActivity, String EditTextTime) {
        myDb.updateMedicationData(editTextId,EditTextActivity,EditTextTime);

        finish();
        startActivity(getIntent());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.add_new,menu);
        return super.onCreateOptionsMenu(menu);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.addNew){
            medicin="";
            itemId="";
            time="";
            openDialog();
            f=1;

            //Toast.makeText(getApplicationContext(),"addNew clicked",Toast.LENGTH_LONG).show();
        }
        if (item.getItemId()==R.id.deleteAllActivities){
            myDb.deleteAllMedication();
            finish();
            startActivity(getIntent());
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void applyText(String EditTextActivity,String EditTextTime) {
        if(f==1){
            myDb.insertMedicationData(EditTextActivity,EditTextTime);
            finish();
            startActivity(getIntent());
        }
    }
}
