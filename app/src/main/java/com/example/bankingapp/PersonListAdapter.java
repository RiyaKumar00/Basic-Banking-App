package com.example.bankingapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class PersonListAdapter extends ArrayAdapter<Customer> {
    private Context mContext;
    int mResource;
    public PersonListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Customer> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String name = getItem(position).getName();
        String bal = getItem(position).getBalance();
        Customer person = new Customer(name,bal);
        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource,parent,false);
        TextView nameView = (TextView) convertView.findViewById(R.id.textView);
        TextView balView = (TextView) convertView.findViewById(R.id.textView2);
        nameView.setText(name);
        balView.setText("Current Balance: Rs."+bal);

        return convertView;
    }
}
