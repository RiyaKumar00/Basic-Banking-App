package com.example.bankingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Pre_MainActivity extends AppCompatActivity {
    TextView by,name,basic,banking;
    ImageView coin,bg1,bg2;
    Button startButton;
    public void startAnimate(){
        bg1.animate().alpha(0).setDuration(0).withEndAction(new Runnable() {
            @Override
            public void run() {
                bg1.animate().alpha(1).setDuration(1000);
            }
        });
        bg2.animate().alpha(0).setDuration(0).withEndAction(new Runnable() {
            @Override
            public void run() {
                bg2.animate().alpha(1).setDuration(1000);
            }
        });
        coin.animate().translationYBy(-2000).setDuration(0).withEndAction(new Runnable() {
            @Override
            public void run() {
                coin.animate().translationYBy(2000).setDuration(1000);
            }
        });
        basic.animate().scaleX(0f).scaleY(0f).setDuration(0).withEndAction(new Runnable() {
            @Override
            public void run() {
                basic.animate().scaleX(1f).scaleY(1f).setDuration(1000);
            }
        });
        banking.animate().scaleX(0f).scaleY(0f).setDuration(0).withEndAction(new Runnable() {
            @Override
            public void run() {
                banking.animate().scaleX(1f).scaleY(1f).setDuration(1000);
            }
        });
        by.animate().translationYBy(2000).setDuration(0).withEndAction(new Runnable() {
            @Override
            public void run() {
                by.animate().translationYBy(-2000).setDuration(1000);
            }
        });
        name.animate().translationYBy(2000).setDuration(0).withEndAction(new Runnable() {
            @Override
            public void run() {
                name.animate().translationYBy(-2000).setDuration(1000);
            }
        });
        startButton.animate().translationXBy(800).setDuration(0).withEndAction(new Runnable() {
            @Override
            public void run() {
                startButton.animate().translationXBy(-800).setDuration(1000);
            }
        });
    }
    public void start(View view){
        Intent startAct= new Intent(getApplicationContext(),MainActivity.class);
        startActivity(startAct);
        finish();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre__main);
        by = findViewById(R.id.byText);
        name = findViewById(R.id.nameText);
        basic = findViewById(R.id.basic);
        banking = findViewById(R.id.bankingLabel);
        coin = findViewById(R.id.coinImg);
        bg1 = findViewById(R.id.whitebg1);
        bg2 = findViewById(R.id.whitebg2);
        startButton = findViewById(R.id.startButton);
        startAnimate();
    }
}