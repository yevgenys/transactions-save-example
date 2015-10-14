package com.transaction.save.example.controller.response;

public enum ResponseStatus {
    SUCCESS("ok"),
    ERROR("error");

    private final String status;

    ResponseStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
