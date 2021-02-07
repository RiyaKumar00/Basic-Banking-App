package com.example.bankingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ImageButton transfer,history;
    TextView transferLabel,historyLabel;
    public void onHome(View view){
    }
    public void onBack(View view){
    }
    public void startAnimate(){
        transfer.animate().translationXBy(800).setDuration(0).withEndAction(new Runnable() {
            @Override
            public void run() {
                transfer.animate().translationXBy(-800).setDuration(500);
            }
        });
        transferLabel.animate().translationXBy(800).setDuration(0).withEndAction(new Runnable() {
            @Override
            public void run() {
                transferLabel.animate().translationXBy(-800).setDuration(500);
            }
        });
        history.animate().translationXBy(-800).setDuration(0).withEndAction(new Runnable() {
            @Override
            public void run() {
                history.animate().translationXBy(800).setDuration(500);
            }
        });
        historyLabel.animate().translationXBy(-800).setDuration(0).withEndAction(new Runnable() {
            @Override
            public void run() {
                historyLabel.animate().translationXBy(800).setDuration(500);
            }
        });
    }
    public void viewCustomerList(View view){
        Intent customerList = new Intent(getApplicationContext(),CustomerListActivity.class);
        customerList.putExtra("SenderID","");
        startActivity(customerList);
    }
    public void viewTranctionHistory(View view){
        Intent trasachist = new Intent(getApplicationContext(),TransitionHistory.class);
        startActivity(trasachist);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        transfer = findViewById(R.id.transferImgButton);
        history = findViewById(R.id.historyImgButton);
        transferLabel = findViewById(R.id.transferButtonText);
        historyLabel = findViewById(R.id.historyButtonText);
        startAnimate();
        SQLiteDatabase myDataBase = this.openOrCreateDatabase("customers", Context.MODE_PRIVATE,null);
        //myDataBase.execSQL("DROP TABLE customers");
        myDataBase.execSQL("CREATE TABLE IF NOT EXISTS customers(custid VARCHAR PRIMARY KEY, name VARCHAR, email VARCHAR, phn INT(10), bank VARCHAR, balance DOUBLE)");
        //myDataBase.execSQL("DROP TABLE transitions");
        myDataBase.execSQL("CREATE TABLE IF NOT EXISTS transitions(transitionid VARCHAR PRIMARY KEY, sender VARCHAR, receiver VARCHAR, amount VARCHAR, status VARCHAR)");
        //myDataBase.execSQL("INSERT INTO customers VALUES('C100','Riya', 'riyakumar2709@gmail.com',9958439926,'ICICI Bank',1000.50)");
        //myDataBase.execSQL("INSERT INTO customers VALUES('C101','Sarah', 'sarahh2020@gmail.com',9057893984,'HDFC Bank',2000.75)");
    }
}