package com.transaction.save.example.controller.advice;

import com.transaction.save.example.BaseTestClass;
import com.transaction.save.example.controller.transformer.ErrorTransformer;
import com.transaction.save.example.controller.response.ErrorResponse;
import com.transaction.save.example.controller.response.Response;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public abstract class ErrorController extends BaseTestClass {

    @Mock private ErrorResponse response;

    @Before
    public void setUp() throws Exception {
        mockTransformer();
    }

    @Test
    public void testCallTransformer() throws Exception {
        callController();
        verify(getTransformer()).transform(getException());
    }

    @Test
    public void testReturnErrorResponse() throws Exception {
        Response response = callController();
        assertThat(response).isInstanceOf(ErrorResponse.class);
    }

    protected abstract ErrorTransformer getTransformer();

    protected abstract Response callController();

    protected abstract Exception getException();

    private void mockTransformer() {
        when(getTransformer().transform(any(Exception.class))).thenReturn(response);
    }

}
