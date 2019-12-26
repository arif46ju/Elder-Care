package com.example.eldercare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class shoppingActivity extends AppCompatActivity {
   WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);
        webView=findViewById(R.id.webView);
        WebSettings webSettings=webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());

        Bundle bundle= getIntent().getExtras();
        try{
            permission();
        }catch (Exception e){
            e.printStackTrace();
        }
        if(bundle!=null){
            String value= bundle.getString("button");
            if(value.equals("daraz")){
                webView.loadUrl("https://www.daraz.com.bd/");
            }
            if(value.equals("aliexpress")){
                webView.loadUrl("https://www.aliexpress.com/");
            }
            if(value.equals("foodpanda")){
                webView.loadUrl("https://www.foodpanda.com.bd/");
            }
            if(value.equals("map")){
                webView.loadUrl("https://www.google.com/maps/search/nearby+market/@23.8702083,90.2626406,14z/data=!3m1!4b1");
            }

        }

    }

    @Override
    public void onBackPressed() {
        if(webView.canGoBack()){
            webView.goBack();
        }
        else{
            super.onBackPressed();
        }

    }
    public void permission(){
        ActivityCompat.requestPermissions(this,new String[]{ACCESS_FINE_LOCATION},1);
    }
}
