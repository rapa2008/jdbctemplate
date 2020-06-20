package com.jdbc.demo;

import java.sql.Timestamp;

public class BalanceRequest {
    long id;
    String balance;
    String name;
    Long amount;
    Timestamp date;


    public BalanceRequest() {
    }

    /*public BalanceRequest(long id, String balance) {
    }*/

    public BalanceRequest(long id, String balance, String name, Long amount, Timestamp date) {
        this.id = id;
        this.balance = balance;
        this.name = name;
        this.amount = amount;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }


    @Override
    public String toString() {
        return "BalanceRequest{" +
                "id=" + id +
                ", balance='" + balance + '\'' +
                ", name='" + name + '\'' +
                ", amount=" + amount +
                ", date=" + date +
                '}';
    }
}
