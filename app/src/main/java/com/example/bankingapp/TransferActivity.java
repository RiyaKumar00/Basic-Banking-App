package com.example.bankingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class TransferActivity extends AppCompatActivity {
    String senderID,receiverID,senderName,receiverName;
    String amt;
    SQLiteDatabase myDataBase;
    ImageView arrow1,arrow2;
    TextView text;
    Button transferB;
    EditText amtStr;
    public void onHistory(View view){
        Intent historyPage = new Intent(getApplicationContext(),TransitionHistory.class);
        historyPage.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(historyPage);
        finish();
    }
    public void onHome(View view){
        Intent homepage = new Intent(getApplicationContext(),MainActivity.class);
        homepage.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(homepage);
        finish();
    }
    public void onTransferClick(View view){
        amt = amtStr.getText().toString();
        if(amt.equals("")){
            Toast.makeText(this, "Amount should not be empty", Toast.LENGTH_SHORT).show();
            return;
        }
        new AlertDialog.Builder(this)
                .setIcon(R.drawable.ic_error)
                .setTitle("Confirmation")
                .setMessage("Transfer Rs."+amt+" from "+senderName+" to "+receiverName)
                .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String senderBal="";
                        Cursor c=myDataBase.rawQuery("SELECT * FROM customers WHERE custid='"+senderID+"'",null);
                        if(c.moveToFirst()){
                            senderBal = c.getString(c.getColumnIndex("balance"));
                        }
                        Log.i("bal: ",senderBal);
                        if(Double.parseDouble(senderBal)<Double.parseDouble(amt)){
                            myDataBase.execSQL("INSERT INTO transitions(sender,receiver,amount,status) VALUES('"+senderName+"','"+receiverName+"','"+amt+"','FAILED')");
                            Toast.makeText(TransferActivity.this, "Transaction Failed due to insufficient balance", Toast.LENGTH_LONG).show();
                            finish();
                        }
                        else {
                            myDataBase.execSQL("UPDATE customers SET balance = balance-" + amt + " WHERE custid='" + senderID + "'");
                            myDataBase.execSQL("UPDATE customers SET balance = balance+" + amt + " WHERE custid='" + receiverID + "'");
                            myDataBase.execSQL("INSERT INTO transitions(sender,receiver,amount,status) VALUES('" + senderName + "','" + receiverName + "','" + amt + "','SUCCESS')");
                            TextView result = findViewById(R.id.resultView);
                            Button homeButton = findViewById(R.id.homeButton);
                            Button historyButton = findViewById(R.id.TransactHist);
                            arrow1.animate().rotationBy(180).translationXBy(-280).translationYBy(-762).scaleX(0.8f).setDuration(1000);
                            arrow2.animate().rotationBy(180).translationXBy(300).translationYBy(762).scaleX(0.8f).setDuration(1000);
                            text.animate().alpha(0).setDuration(1000);
                            amtStr.animate().alpha(0).setDuration(1000);
                            transferB.animate().alpha(0).setDuration(1000);
                            result.animate().alpha(1).setDuration(1000);
                            historyButton.animate().alpha(1).setDuration(1000);
                            homeButton.animate().alpha(1).setDuration(1000);
                        }
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        myDataBase.execSQL("INSERT INTO transitions(sender,receiver,amount,status) VALUES('"+senderName+"','"+receiverName+"','"+amt+"','CANCELLED')");
                        Toast.makeText(TransferActivity.this, "Transaction Cancelled", Toast.LENGTH_SHORT).show();
                        onHome(view);
                    }
                })
                .show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);
        Intent parent = this.getIntent();
        senderID = parent.getStringExtra("SenderID");
        receiverID = parent.getStringExtra("ReceiverID");
        senderName = parent.getStringExtra("SenderName");
        receiverName = parent.getStringExtra("ReceiverName");
        Log.i("SENDER: ",senderID);
        Log.i("RECEIVER: ",receiverID);
        arrow1 = findViewById(R.id.arrowImg1);
        arrow2 = findViewById(R.id.arrowImg2);
        text = findViewById(R.id.textView2);
        amtStr = (EditText) findViewById(R.id.amtView);
        transferB = findViewById(R.id.transferButton);
        arrow1.animate().rotationBy(360).setDuration(1000).withEndAction(new Runnable() {
            @Override
            public void run() {
                arrow1.animate().rotationBy(180).translationXBy(300).translationYBy(740).scaleX(2.5f).setDuration(1000);
                text.animate().alpha(1).setDuration(1000);
                amtStr.animate().alpha(1).setDuration(1000);
                transferB.animate().alpha(1).setDuration(1000);
            }
        });
        arrow2.animate().rotationBy(360).setDuration(1000).withEndAction(new Runnable() {
            @Override
            public void run() {
                arrow2.animate().rotationBy(180).translationXBy(-320).translationYBy(-750).scaleX(2.5f).setDuration(1000);
            }
        });
        myDataBase = this.openOrCreateDatabase("customers", Context.MODE_PRIVATE,null);
    }
}