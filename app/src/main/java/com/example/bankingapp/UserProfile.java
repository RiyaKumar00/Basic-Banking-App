package com.example.bankingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class UserProfile extends AppCompatActivity {
    String custID,custName;
    TextView name,email,phn,bank,bal,emailLabel,contactLabel,bankLabel,accLabel;
    ImageButton back ,custButton, home;
    ImageView userImg;
    public void onHome(View view){
        Intent homepage = new Intent(getApplicationContext(),MainActivity.class);
        homepage.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(homepage);
        finish();
    }
    public void onTransfer(View view){
        Intent customerList = new Intent(getApplicationContext(),CustomerListActivity.class);
        customerList.putExtra("SenderID",custID);
        customerList.putExtra("SenderName",custName);
        startActivity(customerList);
    }
    public void onBack(View view){
        finish();
    }
    void startAnimate(){
        userImg.animate().translationYBy(-800).setDuration(0).withEndAction(new Runnable() {
            @Override
            public void run() {
                userImg.animate().translationYBy(800).setDuration(500);
            }
        });
        name.animate().translationYBy(-800).setDuration(0).withEndAction(new Runnable() {
            @Override
            public void run() {
                name.animate().translationYBy(800).setDuration(500);
            }
        });
        email.animate().translationXBy(-800).setDuration(0).withEndAction(new Runnable() {
            @Override
            public void run() {
                email.animate().translationXBy(800).setDuration(500);
            }
        });
        phn.animate().translationXBy(-800).setDuration(0).withEndAction(new Runnable() {
            @Override
            public void run() {
                phn.animate().translationXBy(800).setDuration(500);
            }
        });
        bank.animate().translationXBy(-800).setDuration(0).withEndAction(new Runnable() {
            @Override
            public void run() {
                bank.animate().translationXBy(800).setDuration(500);
            }
        });
        bal.animate().translationXBy(-800).setDuration(0).withEndAction(new Runnable() {
            @Override
            public void run() {
                bal.animate().translationXBy(800).setDuration(500);
            }
        });
        emailLabel.animate().translationXBy(-800).setDuration(0).withEndAction(new Runnable() {
            @Override
            public void run() {
                emailLabel.animate().translationXBy(800).setDuration(500);
            }
        });
        contactLabel.animate().translationXBy(-800).setDuration(0).withEndAction(new Runnable() {
            @Override
            public void run() {
                contactLabel.animate().translationXBy(800).setDuration(500);
            }
        });
        bankLabel.animate().translationXBy(-800).setDuration(0).withEndAction(new Runnable() {
            @Override
            public void run() {
                bankLabel.animate().translationXBy(800).setDuration(500);
            }
        });
        accLabel.animate().translationXBy(-800).setDuration(0).withEndAction(new Runnable() {
            @Override
            public void run() {
                accLabel.animate().translationXBy(800).setDuration(500);
            }
        });
        back.animate().translationXBy(800).setDuration(0).withEndAction(new Runnable() {
            @Override
            public void run() {
                back.animate().translationXBy(-800).setDuration(500);
            }
        });
        home.animate().translationXBy(800).setDuration(0).withEndAction(new Runnable() {
            @Override
            public void run() {
                home.animate().translationXBy(-800).setDuration(500);
            }
        });
        custButton.animate().translationXBy(800).setDuration(0).withEndAction(new Runnable() {
            @Override
            public void run() {
                custButton.animate().translationXBy(-800).setDuration(500);
            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        Intent parent = this.getIntent();
        String customerID = parent.getStringExtra("customerID");
        name = findViewById(R.id.nameview);
        email = findViewById(R.id.emailview);
        phn = findViewById(R.id.phnview);
        bank = findViewById(R.id.bankview);
        bal = findViewById(R.id.balview);
        userImg = findViewById(R.id.userImg);
        emailLabel=findViewById(R.id.emailID);
        contactLabel=findViewById(R.id.contactNo);
        bankLabel=findViewById(R.id.bankName);
        accLabel=findViewById(R.id.accBal);
        back = findViewById(R.id.backButton);
        home = findViewById(R.id.homeButton2);
        custButton = findViewById(R.id.button);
        SQLiteDatabase myDatabase = this.openOrCreateDatabase("customers", Context.MODE_PRIVATE,null);
        Cursor c = myDatabase.rawQuery("SELECT * FROM customers WHERE custid='"+customerID+"'",null);
        int nameInd = c.getColumnIndex("name");
        int emailInd = c.getColumnIndex("email");
        int phnInd = c.getColumnIndex("phn");
        int bankInd = c.getColumnIndex("bank");
        int balInd = c.getColumnIndex("balance");
        c.moveToFirst();
        while(!c.isAfterLast()){
            name.setText(c.getString(nameInd));
            custName = c.getString(nameInd);
            email.setText(c.getString(emailInd));
            phn.setText(c.getString(phnInd));
            bank.setText(c.getString(bankInd));
            bal.setText("Rs."+c.getString(balInd));
            c.moveToNext();
        }
        custID=customerID;
        startAnimate();
    }
}