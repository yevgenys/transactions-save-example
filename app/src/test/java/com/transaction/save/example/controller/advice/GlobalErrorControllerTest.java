package com.transaction.save.example.controller.advice;

import com.transaction.save.example.controller.transformer.ErrorTransformer;
import com.transaction.save.example.controller.response.Response;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class GlobalErrorControllerTest extends ErrorController {

    private static final Exception e = new Exception("OMG EXCEPTION");

    @Mock private ErrorTransformer transformer;
    @InjectMocks private GlobalErrorController controller = new GlobalErrorController();

    @Override
    protected ErrorTransformer getTransformer() {
        return transformer;
    }

    @Override
    protected Response callController() {
        return controller.defaultErrorHandler(e);
    }

    @Override
    protected Exception getException() {
        return e;
    }
}