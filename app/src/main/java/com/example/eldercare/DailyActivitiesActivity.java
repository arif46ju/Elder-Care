package com.example.eldercare;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.Navigation;

import com.google.android.material.navigation.NavigationView;

public class DailyActivitiesActivity extends AppCompatActivity implements View.OnClickListener {
EditText id,activity;
Button insert,update,delete,view;
databaseHelper myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_activities);
        myDb=new databaseHelper(this);
        id=findViewById(R.id.id);
        activity=findViewById(R.id.activities);
        insert=findViewById(R.id.insert);
        update=findViewById(R.id.update);
        delete=findViewById(R.id.delate);
        view=findViewById(R.id.view);
        insert.setOnClickListener(this);
        update.setOnClickListener(this);
        delete.setOnClickListener(this);
        view.setOnClickListener(this);




    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.insert){
            boolean isInserted = myDb.insertData(activity.getText().toString());
            if(isInserted == true)
                Toast.makeText(getApplicationContext(),"Data Inserted",Toast.LENGTH_LONG).show();
            else
                Toast.makeText(getApplicationContext(),"Data not Inserted",Toast.LENGTH_LONG).show();
        }
        else if(v.getId()==R.id.update){
            boolean isUpdate = myDb.updateData(id.getText().toString(), activity.getText().toString());
            if(isUpdate == true)
                Toast.makeText(getApplicationContext(),"Data Update",Toast.LENGTH_LONG).show();
            else
                Toast.makeText(getApplicationContext(),"Data not Updated",Toast.LENGTH_LONG).show();

        }
        else if(v.getId()==R.id.delate){
            Integer deletedRows = myDb.deleteData(id.getText().toString());
            if(deletedRows > 0)
                Toast.makeText(getApplicationContext(),"Data Deleted",Toast.LENGTH_LONG).show();
            else
                Toast.makeText(getApplicationContext(),"Data not Deleted",Toast.LENGTH_LONG).show();

        }
        else if(v.getId()==R.id.view){
           Intent intent=new Intent(getApplicationContext(),dailyActivitiesList.class);
            startActivity(intent);

/*
            Cursor res = myDb.getAllData();
            if(res.getCount() == 0) {
                // show message
                showMessage("Error","Nothing found");
                return;
            }

            StringBuffer buffer = new StringBuffer();
            while (res.moveToNext()) {
                buffer.append("Id :"+ res.getString(0)+"\n");
                buffer.append("activity :"+ res.getString(1)+"\n");

            }

            // Show all data
            showMessage("Data",buffer.toString());*/
        }

    }
    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }


}
