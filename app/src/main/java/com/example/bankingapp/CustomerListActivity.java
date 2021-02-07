package com.example.bankingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class CustomerListActivity extends AppCompatActivity {
    ListView list;
    ImageButton back,home;
    public void startAnimate(){
        list.animate().translationYBy(2000).setDuration(0).withEndAction(new Runnable() {
            @Override
            public void run() {
                list.animate().translationYBy(-2000).setDuration(500);
            }
        });
        back.animate().translationXBy(-800).setDuration(0).withEndAction(new Runnable() {
            @Override
            public void run() {
                back.animate().translationXBy(800).setDuration(500);
            }
        });
        home.animate().translationXBy(-800).setDuration(0).withEndAction(new Runnable() {
            @Override
            public void run() {
                home.animate().translationXBy(800).setDuration(500);
            }
        });
    }
    public void onBack(View view){
        finish();
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
        setContentView(R.layout.activity_customer_list);
        list = (ListView) findViewById(R.id.listView);
        back = findViewById(R.id.backButton1);
        home = findViewById(R.id.homeImgbutton);
        SQLiteDatabase myDatabase = this.openOrCreateDatabase("customers", Context.MODE_PRIVATE,null);
        Cursor c = myDatabase.rawQuery("SELECT * FROM customers",null);
        int nameInd = c.getColumnIndex("name");
        int balInd = c.getColumnIndex("balance");
        int idInd = c.getColumnIndex("custid");
        c.moveToFirst();
        ArrayList<Customer> custList = new ArrayList<Customer>();
        ArrayList<String> custID = new ArrayList<String>();
        while(!c.isAfterLast()){
            custList.add(new Customer(c.getString(nameInd),c.getString(balInd)));
            custID.add(c.getString(idInd));
            c.moveToNext();
        }
        PersonListAdapter adapter = new PersonListAdapter(this,R.layout.adapter_view_layout,custList);
        list.setAdapter(adapter);
        startAnimate();
        Intent parent = this.getIntent();
        String senderID = parent.getStringExtra("SenderID");
        String senderName = parent.getStringExtra("SenderName");
        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(senderID.equals("")) {
                    Intent CustomerDetails = new Intent(getApplicationContext(), UserProfile.class);
                    CustomerDetails.putExtra("customerID", custID.get(position));
                    startActivity(CustomerDetails);
                }
                else{
                    String receiverID = custID.get(position);
                    if(senderID.equals(receiverID)){
                        Toast.makeText(CustomerListActivity.this, "Sender and receiver can not be same", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Intent transferPage = new Intent(getApplicationContext(),TransferActivity.class);
                        transferPage.putExtra("ReceiverID",receiverID);
                        transferPage.putExtra("SenderID",senderID);
                        transferPage.putExtra("ReceiverName",custList.get(position).name);
                        transferPage.putExtra("SenderName",senderName);
                        startActivity(transferPage);
                        finish();
                    }
                }
            }
        });
    }
}