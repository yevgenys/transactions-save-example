package com.transaction.save.example.service;

public class ServiceError extends RuntimeException {
    public ServiceError(Throwable cause) {
        super(cause);
    }
}
