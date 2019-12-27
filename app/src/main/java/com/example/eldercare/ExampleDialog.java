package com.example.eldercare;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatDialogFragment;

public class ExampleDialog extends AppCompatDialogFragment {
    private EditText editActivity,editId;
    String id,activity;
    private ExampleDialogListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //editId.setText(d_obj.itemId);
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        LayoutInflater inflater=getActivity().getLayoutInflater();
        View view=inflater.inflate(R.layout.edit_layout,null);
        editActivity=view.findViewById(R.id.editactivity);
        editId=view.findViewById(R.id.editID);
        editId.setText(id);
        editActivity.setText(activity);
        builder.setView(view)
                .setCancelable(false)
                .setPositiveButton("save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String editTextId=editId.getText().toString();
                        String editTextActivity=editActivity.getText().toString();
                        listener.applyText(editTextId,editTextActivity);
                        listener.applyText(editTextActivity);
                    }
                })
 .setNeutralButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        return builder.create();

    };

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            listener= (ExampleDialogListener) context;
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    public interface ExampleDialogListener{
        void applyText(String editTextId,String EditTextActivity);
        void applyText(String EditTextActivity);
    }
}
