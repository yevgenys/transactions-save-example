package com.transaction.save.example.controller;

import com.transaction.save.example.controller.response.Response;
import com.transaction.save.example.controller.response.SuccessResponse;
import com.transaction.save.example.controller.transformer.TransactionTransformer;
import com.transaction.save.example.verifier.TransactionControllerVerifier;
import com.transaction.save.example.model.Transaction;
import com.transaction.save.example.service.TransactionService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("transactionservice/")
public class TransactionController {
    private static final Logger logger = Logger.getLogger(TransactionController.class);

    @Autowired private TransactionControllerVerifier verifier;
    @Autowired private TransactionService transactionService;
    @Autowired private TransactionTransformer transactionTransformer;

    @RequestMapping(value = "transaction/{id}", method = RequestMethod.PUT)
    public Response transaction(@PathVariable long id, @RequestBody Transaction transaction) {
        logger.info("Putting transaction with id = " + id);
        logger.debug("Putting transaction = " + transaction.toString());
        verifier.verify(transaction, id);
        transactionService.update(id, transaction);
        return new SuccessResponse();
    }

    @RequestMapping(value = "transaction/{id}", method = RequestMethod.GET)
    public Response getTransaction(@PathVariable long id) {
        logger.info("Get transaction with id = " + id);
        Transaction transaction = transactionService.get(id);
        return transactionTransformer.transform(transaction);
    }

    @RequestMapping(value = "types/{type}", method = RequestMethod.GET)
    public Response getIds(@PathVariable String type) {
        logger.info("Get all ids for type = " + type);
        List<Long> ids = transactionService.getIds(type);
        return transactionTransformer.transform(ids);
    }

    @RequestMapping(value = "sum/{id}", method = RequestMethod.GET)
    public Response getSum(@PathVariable long id){
        logger.debug("Get all ids for type = " + id);
        double sum = transactionService.getSum(id);
        return transactionTransformer.transform(sum);
    }

}
