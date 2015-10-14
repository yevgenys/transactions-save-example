package com.transaction.save.example.verifier;

import com.transaction.save.example.dao.Dao;
import com.transaction.save.example.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransactionServiceVerifier {

    @Autowired private Dao dao;

    public void verifyNewTransaction(final Transaction transaction, long id) {
        checkTransactionNotExists(id);
        checkParentTransactionExists(transaction);
    }

    private void checkTransactionNotExists(long id) {
        boolean transactionExist = dao.containsTransaction(id);
        if(transactionExist){
            throw new VerificationError("Can't update existing transaction");
        }
    }

    public void verifyExistTransaction(final Transaction transaction, final long id) {
        transactionNotNull(transaction, id);
    }

    private void transactionNotNull(Transaction transaction, long id) {
        if(transaction == null) {
            throw new VerificationError("No transaction with id = " + id);
        }
    }

    private void checkParentTransactionExists(Transaction transaction) {
        long parent_id = transaction.parent_id;
        if(parent_id != Transaction.NO_PARENT) {
            boolean containsTransaction = dao.containsTransaction(parent_id);
            if(!containsTransaction) {
                throw new VerificationError("No parent transaction with id = " + parent_id);
            }
        }
    }

}
