package com.transaction.save.example.service;

import com.transaction.save.example.dao.Dao;
import com.transaction.save.example.model.Transaction;
import com.transaction.save.example.verifier.TransactionServiceVerifier;
import com.transaction.save.example.verifier.VerificationError;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    @Autowired private Dao dao;
    @Autowired private TransactionServiceVerifier verifier;

    private static final Logger logger = Logger.getLogger(TransactionService.class);

    public void update(final long id, final Transaction transaction){
        try {
            logger.info("Updating transaction with id = " + id);
            logger.debug("transaction = " + transaction.toString());
            verifier.verifyNewTransaction(transaction, id);
            dao.put(id, transaction);
        }catch(VerificationError e){
            throw new ServiceError(e);
        }
    }

    public Transaction get(final long id) {
        try {
            logger.info("Getting transaction by id = " + id);
            Transaction transaction = dao.get(id);
            verifier.verifyExistTransaction(transaction, id);
            return transaction;
        }catch(VerificationError e){
            throw new ServiceError(e);
        }
    }

    public List<Long> getIds(final String type){
        logger.info("Getting all ids for type = " + type);
        return dao.getIds(type);
    }

    public double getSum(long id) {
        Transaction transaction = dao.get(id);
        if(transaction.parent_id == Transaction.NO_PARENT){
            return transaction.amount;
        }
        return transaction.amount + getSum(transaction.parent_id);
    }
}