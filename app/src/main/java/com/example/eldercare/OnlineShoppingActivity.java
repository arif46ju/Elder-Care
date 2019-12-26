package com.example.eldercare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class OnlineShoppingActivity extends AppCompatActivity implements View.OnClickListener {
Button daraz,aliexpress,foodPanda,search;
FusedLocationProviderClient client;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_shopping);
        daraz=findViewById(R.id.daraz);
       aliexpress=findViewById(R.id.aliexpress);
        foodPanda=findViewById(R.id.foodpanda);
        search=findViewById(R.id.map);
        daraz.setOnClickListener(OnlineShoppingActivity.this);
       aliexpress.setOnClickListener(OnlineShoppingActivity.this);
       foodPanda.setOnClickListener(OnlineShoppingActivity.this);
        search.setOnClickListener(OnlineShoppingActivity.this);


        client= LocationServices.getFusedLocationProviderClient(this);

        client.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent(this,shoppingActivity.class);
        if(v.getId()==R.id.daraz){
            intent.putExtra("button","daraz");

        }
        if(v.getId()==R.id.aliexpress){
            intent.putExtra("button","aliexpress");

        }
        if(v.getId()==R.id.foodpanda){
            intent.putExtra("button","foodpanda");
        }
        if(v.getId()==R.id.map){
            intent.putExtra("button","map");

        }
        startActivity(intent);
    }

}
