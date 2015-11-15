package com.transaction.save.example.service;

import com.transaction.save.example.BaseTestClass;
import com.transaction.save.example.dao.Dao;
import com.transaction.save.example.model.Transaction;
import com.transaction.save.example.verifier.TransactionServiceVerifier;
import com.transaction.save.example.verifier.VerificationError;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;

public class TransactionServiceTest extends BaseTestClass {
    private static final long ID = 123;
    private static final String TYPE = "testType";
    private static final double AMOUNT = 100500.0;

    @Mock private Dao dao;
    @Mock private TransactionServiceVerifier verifier;
    private Transaction transaction;
    private Transaction daoTransaction;
    @InjectMocks private TransactionService service = new TransactionService();

    @Before
    public void setUp() throws Exception {
        initTransactions();
    }

    @Test
    public void testCallDaoOnUpdate() throws Exception {
        service.update(ID, transaction);
        verify(dao).put(ID, transaction);
    }

    @Test
    public void testCallDaoOnGet() throws Exception {
        service.get(ID);
        verify(dao).get(ID);
    }

    @Test
    public void testCallVerifierOnUpdate() throws Exception {
        service.update(ID, transaction);
        verify(verifier).verifyNewTransaction(transaction, ID);
    }

    @Test
    public void testCallVerifierOnGet() throws Exception {
        mockDao();
        service.get(ID);
        verify(verifier).verifyExistTransaction(daoTransaction, ID);
    }

    @Test(expected = ServiceError.class)
    public void testServiceErrorOnUpdate() throws Exception {
        mockVerifierThrowError();
        service.update(ID, transaction);
    }

    @Test(expected = ServiceError.class)
    public void testServiceErrorOnGet() throws Exception {
        mockVerifierThrowError();
        service.get(ID);
    }

    @Test
    public void testCallDaoOnGetIds() throws Exception {
        service.getIds(TYPE);
        verify(dao).getIds(TYPE);
    }

    @Test
    public void testCallDaoONGetSum() throws Exception {
        mockDao();
        service.getSum(ID);
        verify(dao).get(ID);
    }

    @Test
    public void testCalculateSum() throws Exception {
        long lastId = 2;
        Transaction tr1 = getTransaction();
        Transaction tr2 = getTransaction(tr1, 1);
        Transaction tr3 = getTransaction(tr2, lastId);
        mockDao(tr1, tr2, tr3);
        final double sum = service.getSum(lastId);
        final double expectedSum = AMOUNT * 3;
        assertThat(sum).isEqualTo(expectedSum);
    }

    private void mockDao(Transaction transaction, Transaction... transactions) {
        when(dao.get(anyInt())).thenReturn(transaction, transactions);
    }

    private Transaction getTransaction() {
        Transaction transaction = new Transaction();
        transaction.amount = AMOUNT;
        return transaction;
    }

    private Transaction getTransaction(Transaction parentTransaction, final long id) {
        Transaction transaction = getTransaction();
        transaction.parent_id = id;
        parentTransaction.child_id = id - 1;
        return transaction;
    }

    private void initTransactions() {
        transaction = getTransaction();
        daoTransaction = getTransaction();
    }

    private void mockDao() {
        when(dao.get(anyInt())).thenReturn(daoTransaction);
    }

    private void mockVerifierThrowError() {
        doThrow(new VerificationError("TestError!")).when(verifier)
                .verifyNewTransaction(any(Transaction.class), anyLong());
        doThrow(new VerificationError("TestError!")).when(verifier)
                .verifyExistTransaction(any(Transaction.class), anyInt());
    }
}