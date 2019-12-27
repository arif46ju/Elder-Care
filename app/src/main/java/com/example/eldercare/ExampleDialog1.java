package com.example.eldercare;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialogFragment;

import java.util.Calendar;

public class ExampleDialog1 extends AppCompatDialogFragment {
    private EditText editmedicin,editId,editTime;
    String id,medicin,time;

    private ExampleDialog1.ExampleDialogListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //editId.setText(d_obj.itemId);
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        LayoutInflater inflater=getActivity().getLayoutInflater();
        View view=inflater.inflate(R.layout.edit_layout1,null);
        editmedicin=view.findViewById(R.id.editmedicin);
        editId=view.findViewById(R.id.editID);
        editTime=view.findViewById(R.id.edittime);
        editId.setText(id);
        editmedicin.setText(medicin);
        editTime.setText(time);
        editTime.setOnClickListener(new View.OnClickListener() {
            Calendar calendar=Calendar.getInstance();
            final int hour=calendar.get(Calendar.HOUR_OF_DAY);
            final int minute1=calendar.get(Calendar.MINUTE);
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog=new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        StringBuffer tmp1,tmp2;
                        tmp1=new StringBuffer();
                        tmp2=new StringBuffer();
                        editTime.setText("");

                        if(hourOfDay<10){
                           tmp1.append("0"+hourOfDay) ;
                        }
                        else tmp1.append(hourOfDay);
                        if(minute<10){
                            tmp2.append("0"+minute);
                        }
                        else{
                            tmp2.append(minute);
                        }

                        editTime.setText(tmp1+":"+tmp2);
                        tmp1=null;
                        tmp2=null;




                    }
                },hour,minute1 ,android.text.format.DateFormat.is24HourFormat(getContext()));
                timePickerDialog.show();

            }
        });
        builder.setView(view)
                .setCancelable(false)
                .setPositiveButton("save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String editTextId=editId.getText().toString();
                        String editTextActivity=editmedicin.getText().toString();
                        String editTextTime=editTime.getText().toString();

                        listener.applyText(editTextId,editTextActivity,editTextTime);
                       // listener.applyText(editTextId,editTextActivity,editTextTime);
                        listener.applyText(editTextActivity,editTextTime);



                    }
                })
                .setNeutralButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        return builder.create();

    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            listener= (ExampleDialog1.ExampleDialogListener) context;
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    public interface ExampleDialogListener{
        void applyText(String editTextId,String EditTextActivity,String EditTextTime);
        void applyText(String editTextActivity, String EditTextTime);
    }
}
