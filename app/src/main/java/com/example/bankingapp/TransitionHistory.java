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
import android.widget.ListView;

import java.util.ArrayList;

public class TransitionHistory extends AppCompatActivity {
    ImageButton newTrans,home;
    ListView list;
    public void startAnimate(){
        newTrans.animate().translationXBy(800).setDuration(0).withEndAction(new Runnable() {
            @Override
            public void run() {
                newTrans.animate().translationXBy(-800).setDuration(500);
            }
        });
        home.animate().translationXBy(800).setDuration(0).withEndAction(new Runnable() {
            @Override
            public void run() {
                home.animate().translationXBy(-800).setDuration(500);
            }
        });
        list.animate().translationYBy(2000).setDuration(0).withEndAction(new Runnable() {
            @Override
            public void run() {
                list.animate().translationYBy(-2000).setDuration(500);
            }
        });
    }
    public void viewCustomerList(View view){
        Intent customerList = new Intent(getApplicationContext(),CustomerListActivity.class);
        customerList.putExtra("SenderID","");
        startActivity(customerList);
    }
    public void onHome(View view){
        Intent homepage = new Intent(getApplicationContext(),MainActivity.class);
        homepage.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(homepage);
        finish();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition_history);
        ArrayList<Transition> transHist = new ArrayList<Transition>();
        SQLiteDatabase myDataBase = this.openOrCreateDatabase("customers", Context.MODE_PRIVATE,null);
        Cursor c1 = myDataBase.rawQuery("SELECT * FROM transitions",null);
        int senderInd = c1.getColumnIndex("sender");
        int receiverInd = c1.getColumnIndex("receiver");
        int amtInd = c1.getColumnIndex("amount");
        int statusInd = c1.getColumnIndex("status");
        c1.moveToFirst();
        while(!c1.isAfterLast()){
            transHist.add(0,new Transition(c1.getString(senderInd),c1.getString(receiverInd),c1.getString(amtInd),c1.getString(statusInd)));
            Log.i("sender: ",c1.getString(senderInd));
            Log.i("receiver: ",c1.getString(receiverInd));
            Log.i("amt: ",c1.getString(amtInd));
            Log.i("status: ",c1.getString(statusInd));
            c1.moveToNext();
        }
        TransactionAdapter adapter = new TransactionAdapter(this,R.layout.adapter_view_layout_transactions,transHist);
        list = (ListView) findViewById(R.id.listView);
        newTrans = findViewById(R.id.newButton);
        home = findViewById(R.id.homeButton1);
        startAnimate();
        list.setAdapter(adapter);
    }
}