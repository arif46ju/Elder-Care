package com.example.eldercare.ui.send;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.eldercare.R;

public class SendFragment extends Fragment implements View.OnClickListener {

    private SendViewModel sendViewModel;
    private Button send,cancel;
    private EditText name,feedback;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        sendViewModel =
                ViewModelProviders.of(this).get(SendViewModel.class);
        View root = inflater.inflate(R.layout.fragment_send, container, false);
        /*final TextView textView = root.findViewById(R.id.text_send);
        sendViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        send=root.findViewById(R.id.send);
        cancel=root.findViewById(R.id.cancel);
        name=root.findViewById(R.id.name);
        feedback=root.findViewById(R.id.feedback);
        send.setOnClickListener(this);
        cancel.setOnClickListener(this);

        return root;
    }

    @Override
    public void onClick(View v) {
        String nameText =name.getText().toString();
        String feedbackText =feedback.getText().toString();
        if(v.getId()==R.id.send){
            try{
                Intent intent=new Intent(Intent.ACTION_SEND);
                intent.setType("text/email");
                String title="ElderCare";
                String subject="An app for elderly people";
                String body="This app helps the elderly people with their different activities ";
                intent.putExtra(Intent.EXTRA_EMAIL,new String[]{"arifuzzaman125941@gmail.com"});
                intent.putExtra(Intent.EXTRA_SUBJECT,subject);
                intent.putExtra(Intent.EXTRA_TEXT,"Name : "+nameText+"\nMessage : "+feedbackText);
                intent.putExtra(Intent.EXTRA_TITLE,title);
                startActivity(intent.createChooser(intent,"feedback with"));
            }catch (Exception e){
                e.printStackTrace();
            }

        }
        else if(v.getId()==R.id.cancel){
            name.setText("");
            feedback.setText("");

        }
    }
}