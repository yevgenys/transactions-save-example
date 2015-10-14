package com.transaction.save.example.controller.transformer;

import com.transaction.save.example.controller.response.ErrorResponse;
import com.transaction.save.example.controller.response.Response;
import org.springframework.stereotype.Component;

@Component
public class ErrorTransformer {
    public Response transform(Exception e){
        String details = this.buildErrorDetails(e);
        String msg = e.getMessage();
        return new ErrorResponse(msg, details);
    }

    protected String buildErrorDetails(Exception ex) {
        StringBuilder details = new StringBuilder(ex.toString());
        Throwable cause = ex.getCause();
        while(cause != null && details.length() < 1000) {
            details.append("\nCaused by: ").append(cause.toString());
            cause = cause.getCause();
        }

        return details.toString();
    }

}
