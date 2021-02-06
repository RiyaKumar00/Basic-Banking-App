package com.example.bankingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Pre_MainActivity extends AppCompatActivity {
    public void start(View view){
        Intent startAct= new Intent(getApplicationContext(),MainActivity.class);
        startActivity(startAct);
        finish();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre__main);
    }
}