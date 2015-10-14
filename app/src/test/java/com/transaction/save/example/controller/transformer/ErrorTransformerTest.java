package com.transaction.save.example.controller.transformer;

import com.transaction.save.example.BaseTestClass;
import com.transaction.save.example.controller.response.ErrorResponse;
import com.transaction.save.example.controller.response.Response;
import com.transaction.save.example.controller.response.ResponseStatus;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ErrorTransformerTest extends BaseTestClass{
    private static final String ERROR_MSG = "someError";
    private static final String ERROR_DETAILS = "errorDetails";
    private Exception ex;
    private ErrorTransformer errorTransformer;

    @Before
    public void setUp() throws Exception {
        prepareException();
        errorTransformer = new ErrorTransformer();
    }

    @Test
    public void testErrorResponse() throws Exception {
        Response response = errorTransformer.transform(ex);
        assertThat(response).isInstanceOf(ErrorResponse.class);
    }

    @Test
    public void testErrorResponseMessage() throws Exception {
        ErrorResponse response = (ErrorResponse)errorTransformer.transform(ex);
        assertThat(response.getMsg()).isEqualTo(ERROR_MSG);
    }

    @Test
    public void testErrorResponseStatus() throws Exception {
        ErrorResponse response = (ErrorResponse)errorTransformer.transform(ex);
        assertThat(response.getStatus()).isEqualTo(ResponseStatus.ERROR.getStatus());
    }

    @Test
    public void testErrorResponseDetails() throws Exception {
        String expectedDetails = generateExceptionDetails();
        ErrorResponse response = (ErrorResponse)errorTransformer.transform(ex);
        assertThat(response.getDetails()).isEqualTo(expectedDetails);
    }

    private String generateExceptionDetails(){
        StringBuilder details = new StringBuilder(ex.toString());
        Throwable cause = ex.getCause();
        while(cause != null && details.length() < 1000) {
            details.append("\nCaused by: ").append(cause.toString());
            cause = cause.getCause();
        }
        return details.toString();
    }

    private void prepareException(){
        RuntimeException cause = new RuntimeException(ERROR_DETAILS);
        ex = new Exception(ERROR_MSG, cause);
    }

}