package com.transaction.save.example.verifier;

import com.transaction.save.example.BaseTestClass;
import com.transaction.save.example.dao.Dao;
import com.transaction.save.example.model.Transaction;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;

public class TransactionServiceVerifierTest extends BaseTestClass {
    private static final long ID = 123;

    @Mock private Dao dao;
    @Mock private Transaction transaction;
    @Mock private Transaction daoTransaction;
    @InjectMocks private TransactionServiceVerifier verifier = new TransactionServiceVerifier();

    @Test
    public void testSuccessVerification() throws Exception {
        mockDaoNewTransaction();
        verifier.verifyNewTransaction(transaction, ID);

        //no Exceptions
    }

    @Test(expected = VerificationError.class)
    public void testNoParentId() throws Exception {
        mockDaoNotExistParent();
        verifier.verifyNewTransaction(transaction, ID);
    }

    @Test(expected = VerificationError.class)
    public void testTransactionExists() throws Exception {
        mockDaoSameIdExists();
        verifier.verifyNewTransaction(transaction, ID);
    }

    @Test(expected = VerificationError.class)
    public void testNullTransaction() throws Exception {
        verifier.verifyExistTransaction(null, 10);
    }

    private void mockDaoNewTransaction() {
        when(dao.containsTransaction(anyLong())).thenReturn(false, true);
    }

    private void mockDaoSameIdExists() {
        when(dao.containsTransaction(ID)).thenReturn(true);
    }

    private void mockDaoNotExistParent() {
        when(dao.containsTransaction(anyLong())).thenReturn(false, false);
    }

}