package com.transaction.save.example.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.transaction.save.example.model.Transaction;

public class TransactionResponse extends SuccessResponse {
    private final Transaction transaction;

    public TransactionResponse(Transaction transaction) {
        this.transaction = transaction;
    }

    @JsonProperty("amount")
    public double getAmount() {
        return transaction.amount;
    }

    @JsonProperty("type")
    public String getType() {
        return transaction.type;
    }

    @JsonProperty("parent_id")
    public long getParentId() {
        return transaction.parent_id;
    }
}
