package com.transaction.save.example.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorResponse extends Response {
    private final String msg;
    private final String details;

    public ErrorResponse(String msg, String details) {
        super(ResponseStatus.ERROR);
        this.msg = msg;
        this.details = details;
    }

    @JsonProperty("message")
    public String getMsg() {
        return msg;
    }

    @JsonProperty("message_details")
    public String getDetails() {
        return details;
    }
}
