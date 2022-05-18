package com.example.gym.data;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.gym.R;

public class RegisterOwner_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        int gymID = intent.getIntExtra("gymID", 0);
        setContentView(R.layout.activity_register_owner);


    }
}