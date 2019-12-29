package com.example.eldercare;

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

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class dailyActivitiesList extends AppCompatActivity implements ExampleDialog.ExampleDialogListener {
    private ListView listView;
  public String itemId,itemActivity;
    databaseHelper myDb;
    SQLiteDatabase db;
    ArrayList<String> id = new ArrayList<>();
    ArrayList<String> txt = new ArrayList<>();
    ArrayAdapter<String> f_adapter;
    ExampleDialog exampleDialog=new ExampleDialog();
    Menu addNew;
    int f=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_activities_list);
        listView = findViewById(R.id.listViewID);
        addNew=findViewById(R.id.addNew);
        myDb = new databaseHelper(this);
        final Cursor res = myDb.getAllData();

        if (res.getCount() == 0) {
            Toast.makeText(getApplicationContext(), "no Activity found", Toast.LENGTH_LONG).show();
        } else {
            id.add("ID");
            txt.add("Activities");
            while (res.moveToNext()) {

                id.add(res.getString(0));
                txt.add(res.getString(1));

            }
        }
      final customAdapter adapter = new customAdapter(getApplicationContext(), id,txt);
        listView.setAdapter(adapter);
        ListView f_listview = new ListView(getApplicationContext());
        List<String> f_option = new ArrayList<>();
        f_option.add("Delete");
        f_option.add("Edit");
        ColorDrawable color = new ColorDrawable(getResources().getColor(R.color.colorPrimary));
        f_listview.setDivider(color);
        f_listview.setDividerHeight(1);
        f_adapter = new ArrayAdapter<String>(dailyActivitiesList.this, android.R.layout.simple_list_item_1, f_option);
        f_listview.setAdapter(f_adapter);
        final AlertDialog.Builder builder = new AlertDialog.Builder(dailyActivitiesList.this);
        builder.setTitle("File Options");
        builder.setCancelable(true);
        builder.setView(f_listview);
        final AlertDialog dialog = builder.create();

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id1) {

                 itemId=id.get(position);
                 itemActivity=txt.get(position);
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
                    final AlertDialog.Builder builder2 = new AlertDialog.Builder(dailyActivitiesList.this);
                    builder2.setCancelable(true);

                    builder2.setMessage("Are you sure you want to delete?");
                    builder2.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            myDb.deleteData(itemId);
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
myDb.updateData(editTextId,EditTextActivity);

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
           myDb.deleteAll();
           finish();
           startActivity(getIntent());
       }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void applyText(String EditTextActivity) {
        if(f==1){
            myDb.insertData(EditTextActivity);
            finish();
            startActivity(getIntent());
        }

    }
}
