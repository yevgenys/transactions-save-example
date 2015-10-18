package com.transaction.save.example.controller.advice;

import com.transaction.save.example.controller.transformer.ErrorTransformer;
import com.transaction.save.example.controller.response.Response;
import com.transaction.save.example.service.ServiceError;
import com.transaction.save.example.verifier.VerificationError;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class TransactionErrorControllerTest extends ErrorController{

    private static final ServiceError e = new ServiceError(new VerificationError("Oh No!"));

    @Mock private ErrorTransformer transformer;
    @InjectMocks private TransactionErrorController controller = new TransactionErrorController();

    @Override
    protected ErrorTransformer getTransformer() {
        return transformer;
    }

    @Override
    protected Response callController() {
        return controller.verificationError(e);
    }

    @Override
    protected Exception getException() {
        return e;
    }
}