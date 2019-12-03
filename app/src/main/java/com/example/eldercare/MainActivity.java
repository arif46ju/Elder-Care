package com.example.eldercare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button button1, button2, button3, button4,button5,button6, button7, button8, button9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1=(Button) findViewById(R.id.button1);
        button2=(Button) findViewById(R.id.button2);
        button3=(Button) findViewById(R.id.button3);
        button4=(Button) findViewById(R.id.button4);
        button5=(Button) findViewById(R.id.button5);
        button6=(Button) findViewById(R.id.button6);
        button7=(Button) findViewById(R.id.button7);
        button8=(Button) findViewById(R.id.button8);
        button9=(Button) findViewById(R.id.button9);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDailyActivities();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMedicationsActivity();
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGroceryActivity();
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOnlineShoppingActivity();
            }
        });







    }

    public void openDailyActivities(){
        Intent intent=new Intent(this, DailyActivitiesActivity.class);
        startActivity(intent);
    }

    public void openMedicationsActivity(){
        Intent intent=new Intent(this, MedicationsActivity.class);
        startActivity(intent);
    }

    public void openGroceryActivity(){
        Intent intent=new Intent(this, GroceryActivity.class);
        startActivity(intent);
    }

    public void openOnlineShoppingActivity(){
        Intent intent=new Intent(this, OnlineShoppingActivity.class);
        startActivity(intent);
    }





}
