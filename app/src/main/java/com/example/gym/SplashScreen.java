package com.example.gym;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.lusfold.spinnerloading.SpinnerLoading;

public class SplashScreen extends AppCompatActivity {

    SpinnerLoading spinnerLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        spinnerLoading = findViewById(R.id.spinnerloading);
        spinnerLoading.setCircleRadius(20);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        },4000);
    }
}