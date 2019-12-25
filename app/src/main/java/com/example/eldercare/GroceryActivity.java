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

public class GroceryActivity extends AppCompatActivity implements ExampleDialog.ExampleDialogListener{
    private ListView listView;
    public String itemId,itemActivity;
    databaseHelper myDb;
    SQLiteDatabase db;
    ArrayList<String> countryNames = new ArrayList<>();
    ArrayAdapter<String> adapter, f_adapter;
    ExampleDialog exampleDialog=new ExampleDialog();
    Menu addNew;
    int f=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery);
        listView = findViewById(R.id.listViewID);
        addNew=findViewById(R.id.addNew);
        myDb = new databaseHelper(this);
        final Cursor res = myDb.getAllGrocery();

        if (res.getCount() == 0) {
            Toast.makeText(getApplicationContext(), "no Activity found", Toast.LENGTH_LONG).show();
        } else {
            countryNames.add("ID" + " \t " + "Item");
            while (res.moveToNext()) {

                countryNames.add(res.getString(0) + " \t " + res.getString(1));


            }
        }


        adapter = new ArrayAdapter<String>(GroceryActivity.this, R.layout.daily_list_sample_layout,R.id.textViewID, countryNames);
        listView.setAdapter(adapter);

        ListView f_listview = new ListView(getApplicationContext());
        List<String> f_option = new ArrayList<>();
        f_option.add("Delete");
        f_option.add("Edit");
        ColorDrawable color = new ColorDrawable(getResources().getColor(R.color.colorPrimary));
        f_listview.setDivider(color);
        f_listview.setDividerHeight(1);
        f_adapter = new ArrayAdapter<String>(GroceryActivity.this, android.R.layout.simple_list_item_1, f_option);
        f_listview.setAdapter(f_adapter);
        final AlertDialog.Builder builder = new AlertDialog.Builder(GroceryActivity.this);
        builder.setTitle("File Options");
        builder.setCancelable(true);
        builder.setView(f_listview);
        final AlertDialog dialog = builder.create();

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                String value = adapter.getItem(position);
                itemId=value.valueOf(value.substring(0,3));
                itemActivity=value.valueOf(value.substring(4));
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
                    final AlertDialog.Builder builder2 = new AlertDialog.Builder(GroceryActivity.this);
                    builder2.setCancelable(true);

                    builder2.setMessage("Are you sure you want to delete?");
                    builder2.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            myDb.deleteGroceryData(itemId);
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
        exampleDialog.activity=itemActivity;
        exampleDialog.show(getSupportFragmentManager(),"example dialog");

    }

    @Override
    public void applyText(String editTextId, String EditTextActivity) {
        myDb.updateGroceryData(editTextId,EditTextActivity);

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
            itemActivity="";
            itemId="";
            openDialog();
            f=1;

            //Toast.makeText(getApplicationContext(),"addNew clicked",Toast.LENGTH_LONG).show();
        }
        if (item.getItemId()==R.id.deleteAllActivities){
            myDb.deleteAllGrocery();
            finish();
            startActivity(getIntent());
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void applyText(String EditTextActivity) {
        if(f==1){
            myDb.insertGroceryData(EditTextActivity);
            finish();
            startActivity(getIntent());
        }
    }
}
