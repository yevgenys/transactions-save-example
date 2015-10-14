package com.transaction.save.example.verifier;

public class VerificationError extends RuntimeException {
    public VerificationError(String message) {
        super(message);
    }

    public VerificationError(String message, Throwable cause) {
        super(message, cause);
    }
}
