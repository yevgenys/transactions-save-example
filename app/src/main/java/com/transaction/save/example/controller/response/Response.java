package com.transaction.save.example.controller.response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class Response {
    private final ResponseStatus status;

    protected Response(ResponseStatus status) {
        this.status = status;
    }

    public String getStatus() {
        return status.getStatus();
    }

    public String toJsonString() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch(JsonProcessingException e) {
            return String.format("{message:%s,status:%s}", e, getStatus());
        }
    }
}
