package com.example.bankingapp;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class TransactionAdapter extends ArrayAdapter<Transition> {
    private Context mContext;
    int mResource;
    public TransactionAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Transition> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String sender = getItem(position).getSender();
        String receiver = getItem(position).getReceiver();
        String amount = getItem(position).getAmount();
        String status = getItem(position).getStatus();
        Transition T = new Transition(sender,receiver,amount,status);
        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource,parent,false);
        TextView senderView = (TextView) convertView.findViewById(R.id.senderView);
        TextView receiverView = (TextView) convertView.findViewById(R.id.receiverView);
        TextView amountView = (TextView) convertView.findViewById(R.id.amountView);
        TextView statusView = (TextView) convertView.findViewById(R.id.statusView);
        senderView.setText(sender);
        receiverView.setText(receiver);
        amountView.setText("Rs."+amount);
        statusView.setText(status);
        if(status.equals("SUCCESS"))
            statusView.setTextColor(Color.parseColor("#14b324"));
        else
            statusView.setTextColor(Color.parseColor("#e61e17"));
        return convertView;
    }
}
