package com.transaction.save.example.verifier;

import com.transaction.save.example.BaseTestClass;
import com.transaction.save.example.model.Transaction;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class TransactionControllerVerifierTest extends BaseTestClass {
    private static final long PARENT_ID = 20;
    private static final long ID = 1;
    private static final String TYPE = "TestType";
    private static final int AMOUNT = 123123;

    @Mock private Transaction mockedTransaction;
    @InjectMocks private TransactionControllerVerifier verifier = new TransactionControllerVerifier();

    @Test
    public void testSuccess() throws Exception {
        Transaction transaction = getTransaction();
        verifier.verify(transaction, ID);

        //no exceptions
    }

    @Test(expected = VerificationError.class)
    public void testWrongParentId() throws Exception {
        Transaction transaction = getTransactionWrongId();
        verifier.verify(transaction, ID);
    }

    @Test(expected = VerificationError.class)
    public void testWrongType() throws Exception {
        Transaction transaction = getTransactionWrongType();
        verifier.verify(transaction, ID);
    }

    @Test(expected = VerificationError.class)
    public void testWrongAmount() throws Exception {
        Transaction transaction = getTransactionWrongAmount();
        verifier.verify(transaction, ID);
    }

    @Test(expected = VerificationError.class)
    public void testParentIdSameAsId() throws Exception {
        Transaction transaction = getTransaction();
        verifier.verify(transaction, PARENT_ID);
    }

    private Transaction getTransactionWrongId() {
        Transaction transaction = getTransaction();
        transaction.parent_id = -12;
        return transaction;
    }

    private Transaction getTransactionWrongAmount() {
        Transaction transaction = getTransaction();
        transaction.amount = -122;
        return transaction;
    }


    private Transaction getTransactionWrongType() {
        Transaction transaction = getTransaction();
        transaction.type = "";
        return transaction;
    }

    private Transaction getTransaction() {
        Transaction transaction = new Transaction();
        transaction.parent_id = PARENT_ID;
        transaction.type = TYPE;
        transaction.amount = AMOUNT;
        return transaction;
    }
}