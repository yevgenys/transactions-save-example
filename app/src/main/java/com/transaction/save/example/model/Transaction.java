package com.transaction.save.example.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Transaction {

    public static final long NO_PARENT = Long.MIN_VALUE;

    public double amount;
    public String type;
    public long parent_id = NO_PARENT;

    @Override
    public String toString() {
        return "Transaction{" +
                "amount=" + amount +
                ", type='" + type + '\'' +
                ", parent_id=" + parent_id +
                '}';
    }
}
