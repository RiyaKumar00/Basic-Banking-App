package com.example.bankingapp;

public class Customer {
    String name,balance;
    public Customer(String name,String balance){
        this.name=name;
        this.balance=balance;
    }
    String getName(){
        return this.name;
    }
    String getBalance(){
        return this.balance;
    }
}