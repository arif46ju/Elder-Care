package com.example.eldercare;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class customAdapter1 extends BaseAdapter {
    Context context;
    //String[]id;
    // String[]text;
    ArrayList<String> id=new ArrayList<>();
    ArrayList<String>text=new ArrayList<>();
    ArrayList<String>time=new ArrayList<>();
    //String[]time;
    LayoutInflater inflater;
    customAdapter1(Context context, ArrayList<String>id,ArrayList<String>text,ArrayList<String>time){
        this.context=context;
        this.id=id;
        this.text=text;
        this.time=time;

    }

    @Override
    public int getCount() {
        return id.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.sample_layout1,parent,false);
        }
        //ImageView imageView=convertView.findViewById(R.id.imgView);
        TextView idText=convertView.findViewById(R.id.id);
        TextView txtText=convertView.findViewById(R.id.txt);
        TextView timeText=convertView.findViewById(R.id.time);
        //TextView timeText=convertView.findViewById(R.id.time);
        idText.setText(id.get(position));
        txtText.setText(text.get(position));
        timeText.setText(time.get(position));
        //timeText.setText(time[position]);

        return convertView;
    }
}
