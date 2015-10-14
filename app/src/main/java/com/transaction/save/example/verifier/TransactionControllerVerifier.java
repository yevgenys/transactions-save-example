package com.transaction.save.example.verifier;

import com.transaction.save.example.model.Transaction;
import org.springframework.stereotype.Component;

@Component
public class TransactionControllerVerifier {

    public void verify(Transaction transaction, long id) {
        verifyAmount(transaction);
        verifyType(transaction);
        verifyParent(transaction, id);
    }

    private void verifyType(Transaction transaction) {
        if(transaction.type == null || transaction.type.isEmpty()) {
            throw new VerificationError("Type can't be empty or null");
        }
    }

    private void verifyAmount(Transaction transaction) {
        if(transaction.amount < 0) {
            throw new VerificationError("Amount can't be less that 0");
        }
    }

    private void verifyParent(Transaction transaction, long id) {
        long parent_id = transaction.parent_id;
        if(parent_id != Transaction.NO_PARENT && transaction.parent_id < 0) {
            throw new VerificationError("Wrong transaction id = " + parent_id);
        }else if(parent_id == id){
            throw new VerificationError("Parent id can't be same as transaction id");
        }
    }


}
