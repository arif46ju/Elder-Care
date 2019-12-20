package com.example.eldercare;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class dailyActivitiesList extends AppCompatActivity {
    private ListView listView;
    TextView txt;
    databaseHelper myDb;
    String[]countryNames;
    //ArrayList<String>countryNames;
    ArrayAdapter <String>adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_activities_list);
        listView=findViewById(R.id.listViewID);
        txt=findViewById(R.id.test);
        /*Cursor res=myDb.getAllData();
        int i=0;
        while (res.moveToNext()){

            //countryNames.add(res.getString(1));
            countryNames[i]=res.getString(1);
            i++;

        }

        adapter=new ArrayAdapter<String>(getApplicationContext(),R.layout.daily_list_sample_layout,R.id.textViewID,countryNames);
        listView.setAdapter(adapter);



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String value=adapter.getItem(position);
                Toast.makeText(getApplicationContext(),value,Toast.LENGTH_SHORT).show();
            }
        });
*/

    }
}
