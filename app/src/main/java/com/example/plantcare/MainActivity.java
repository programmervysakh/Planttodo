package com.example.plantcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       Handler handler = new Handler();
        Runnable r = new Runnable() {
            public void run() {
                Intent loginIntent = new Intent(getApplicationContext(),HomeActivity.class);
                startActivity(loginIntent);
            }
        };
        handler.postDelayed(r, 2000);


    }
}