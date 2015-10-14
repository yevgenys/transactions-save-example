package com.transaction.save.example.controller.advice;

import com.transaction.save.example.controller.transformer.ErrorTransformer;
import com.transaction.save.example.controller.response.Response;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalErrorController {
    private static final Logger logger = Logger.getLogger(GlobalErrorController.class);
    @Autowired private ErrorTransformer transformer;

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Response defaultErrorHandler(Exception e){
        logger.warn("Got unhandled exception: " + e.getMessage());
        return transformer.transform(e);
    }

}
