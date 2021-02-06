package com.example.bankingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public void onHome(View view){
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
        SQLiteDatabase myDataBase = this.openOrCreateDatabase("customers", Context.MODE_PRIVATE,null);
        //myDataBase.execSQL("DROP TABLE customers");
        myDataBase.execSQL("CREATE TABLE IF NOT EXISTS customers(custid VARCHAR PRIMARY KEY, name VARCHAR, email VARCHAR, phn INT(10), bank VARCHAR, balance DOUBLE)");
        //myDataBase.execSQL("DROP TABLE transitions");
        myDataBase.execSQL("CREATE TABLE IF NOT EXISTS transitions(transitionid VARCHAR PRIMARY KEY, sender VARCHAR, receiver VARCHAR, amount VARCHAR, status VARCHAR)");
        //myDataBase.execSQL("INSERT INTO customers VALUES('C100','Riya', 'riyakumar2709@gmail.com',9958439926,'ICICI Bank',1000.50)");
        //myDataBase.execSQL("INSERT INTO customers VALUES('C101','Sarah', 'sarahh2020@gmail.com',9057893984,'HDFC Bank',2000.75)");
        Cursor c = myDataBase.rawQuery("SELECT * FROM customers",null);
        int nameInd = c.getColumnIndex("name");
        int emailInd = c.getColumnIndex("email");
        int phnInd = c.getColumnIndex("phn");
        int bankInd = c.getColumnIndex("bank");
        int balInd = c.getColumnIndex("balance");
        c.moveToFirst();
        while(!c.isAfterLast()){
            Log.i("name: ",c.getString(nameInd));
            Log.i("email: ",c.getString(emailInd));
            Log.i("phn: ",c.getString(phnInd));
            Log.i("bank: ",c.getString(bankInd));
            Log.i("balance: ",c.getString(balInd));
            c.moveToNext();
        }
        Cursor c1 = myDataBase.rawQuery("SELECT * FROM transitions",null);
        int senderInd = c1.getColumnIndex("sender");
        int receiverInd = c1.getColumnIndex("receiver");
        int amtInd = c1.getColumnIndex("amount");
        int statusInd = c1.getColumnIndex("status");
        c1.moveToFirst();
        while(!c1.isAfterLast()){
            Log.i("sender: ",c1.getString(senderInd));
            Log.i("receiver: ",c1.getString(receiverInd));
            Log.i("amt: ",c1.getString(amtInd));
            Log.i("status: ",c1.getString(statusInd));
            c1.moveToNext();
        }
    }
}