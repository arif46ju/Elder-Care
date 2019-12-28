package com.example.eldercare;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
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
            countryNames.add("ID" + "     " + "Medication" + "            " + "Time");
            int cnt=0;
            while (res.moveToNext()) {
                String c1,c2;
                c1=res.getString(1);
                c2=res.getString(2);
                int h,m;
                h=Integer.parseInt(c2.substring(0,2));
                m=Integer.parseInt(c2.substring(3,5));
                //Toast.makeText(this,"current time: "+Calendar.getInstance().getTimeInMillis()/(1000*3600),Toast.LENGTH_LONG).show();
                if(c1.length()<=15){
                    String tmp="                   ";
                    c1=c1+tmp.substring(0,15-c1.length());
                }
                countryNames.add(res.getString(0) + "      " + c1 + " " + res.getString(2));
                //set Alarm
                Calendar calendar=Calendar.getInstance();
                //for alerm
                try {
                    calendar.set(
                            calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH),
                            h,
                            m,
                            0
                    );
                    if(calendar.before(Calendar.getInstance())){
                        calendar.add(Calendar.DAY_OF_MONTH,1);
                    }
                    cnt++;
                    setAlarm(calendar.getTimeInMillis(),cnt);
                }catch (Exception e){
                    e.printStackTrace();
                }



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
                itemId=value.valueOf(value.substring(0,8));
                medicin=value.valueOf(value.substring(8,value.length()-5));
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

    private void setAlarm(long timeInMillis,int f) {
        /*AlarmManager alarmManager= (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        ArrayList<PendingIntent> intentArray = new ArrayList<PendingIntent>();
        for (int i=0;i<=10;i++){
            Intent intent=new Intent(MedicationsActivity.this,myAlarm.class);
            PendingIntent pendingIntent=PendingIntent.getBroadcast(MedicationsActivity.this,i,intent,0);
            if(Calendar.getInstance().getTimeInMillis()<=timeInMillis){
                alarmManager.setExact(AlarmManager.RTC,timeInMillis,pendingIntent);
                intentArray.add(pendingIntent);
            }
        }
*/
        AlarmManager[] alarmManager=new AlarmManager[24];
        ArrayList<PendingIntent>  intentArray = new ArrayList<PendingIntent>();

            Intent intent = new Intent(MedicationsActivity.this, myAlarm.class);
       PendingIntent pi=PendingIntent.getBroadcast(MedicationsActivity.this, f,intent, 0);

            alarmManager[f] = (AlarmManager) getSystemService(ALARM_SERVICE);
            alarmManager[f].set(AlarmManager.RTC_WAKEUP,timeInMillis ,pi);

            intentArray.add(pi);




        /*Toast.makeText(this,"current time: "+Calendar.getInstance().getTimeInMillis() /(1000*60)+" alerm time: "+timeInMillis/(1000*60),
                Toast.LENGTH_LONG).show();*/

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
