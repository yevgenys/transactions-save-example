package com.transaction.save.example.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true, value = {"child_id"})
public class Transaction {

    public static final long NO_PARENT = Long.MIN_VALUE;
    public static final long NO_CHILD = Long.MIN_VALUE;

    public double amount;
    public String type;
    public long parent_id = NO_PARENT;
    public long child_id = NO_CHILD;

    @Override
    public String toString() {
        return "Transaction{" +
                "amount=" + amount +
                ", type='" + type + '\'' +
                ", parent_id=" + parent_id +
                ", child_id=" + child_id +
                '}';
    }
}
