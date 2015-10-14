package com.transaction.save.example.controller;

import com.transaction.save.example.BaseTestClass;
import com.transaction.save.example.controller.response.*;
import com.transaction.save.example.controller.transformer.TransactionTransformer;
import com.transaction.save.example.model.Transaction;
import com.transaction.save.example.service.TransactionService;
import com.transaction.save.example.verifier.TransactionControllerVerifier;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TransactionControllerTest extends BaseTestClass {

    private static final long ID = 1231;
    private static final String TYPE = "testType";
    private static final double SUM = 1002010432.0;

    @Mock private TransactionService service;
    @Mock private Transaction transaction;
    @Mock private Transaction serviceTransaction;
    @Mock private TransactionControllerVerifier verifier;
    @Mock private TransactionTransformer transactionTransformer;
    @Mock private TransactionResponse transactionResponse;
    @Mock private IdsResponse idsResponse;
    @Mock private SumResponse sumResponse;
    @Mock private List<Long> ids;
    @InjectMocks private TransactionController controller = new TransactionController();

    @Test
    public void testTransactionServiceCallOnUpdate() throws Exception {
        controller.transaction(ID, transaction);
        verify(service).update(ID, transaction);
    }

    @Test
    public void testSuccessResponseReturnOnUpdate() throws Exception {
        Response response = controller.transaction(ID, transaction);
        assertThat(response).isInstanceOf(SuccessResponse.class);
    }

    @Test
    public void testCallVerifierOnUpdate() throws Exception {
        controller.transaction(ID, transaction);
        verify(verifier).verify(transaction, ID);
    }

    @Test
    public void testCallServiceOnGet() throws Exception {
        controller.getTransaction(ID);
        verify(service).get(ID);
    }

    @Test
    public void testCallTransformerOnGet() throws Exception {
        mockTransformer();
        mockService();
        controller.getTransaction(ID);
        verify(transactionTransformer).transform(serviceTransaction);
    }

    @Test
    public void testResponseOnGet() throws Exception {
        mockTransformer();
        mockService();
        Response response = controller.getTransaction(ID);
        assertThat(response).isInstanceOf(TransactionResponse.class);
    }

    @Test
    public void testResponseOnGetIds() throws Exception {
        mockTransformer();
        mockService();
        Response response = controller.getIds(TYPE);
        assertThat(response).isInstanceOf(IdsResponse.class);
    }

    @Test
    public void testServiceCallOnGetIds() throws Exception {
        controller.getIds(TYPE);
        verify(service).getIds(TYPE);
    }

    @Test
    public void testCallTransformerOnGetIds() throws Exception {
        mockService();
        controller.getIds(TYPE);
        verify(transactionTransformer).transform(ids);
    }

    @Test
    public void testCallServiceOnSum() throws Exception {
        controller.getSum(ID);
        verify(service).getSum(ID);
    }

    @Test
    public void testCallTransformerOnSum() throws Exception {
        mockService();
        controller.getSum(ID);
        verify(transactionTransformer).transform(SUM);
    }

    @Test
    public void testResponseOnSum() throws Exception {
        mockService();
        mockTransformer();
        Response response = controller.getSum(ID);
        assertThat(response).isInstanceOf(SumResponse.class);
    }

    private void mockService() {
        when(service.get(anyLong())).thenReturn(serviceTransaction);
        when(service.getIds(anyString())).thenReturn(ids);
        when(service.getSum(anyLong())).thenReturn(SUM);
    }

    private void mockTransformer() {
        when(transactionTransformer.transform(any(Transaction.class)))
                .thenReturn(transactionResponse);
        when(transactionTransformer.transform(Matchers.<List<Long>>any())).thenReturn(idsResponse);
        when(transactionTransformer.transform(anyDouble())).thenReturn(sumResponse);
    }
}