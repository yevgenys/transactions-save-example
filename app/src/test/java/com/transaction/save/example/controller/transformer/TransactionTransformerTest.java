package com.transaction.save.example.controller.transformer;

import com.transaction.save.example.BaseTestClass;
import com.transaction.save.example.controller.response.IdsResponse;
import com.transaction.save.example.controller.response.Response;
import com.transaction.save.example.controller.response.TransactionResponse;
import com.transaction.save.example.model.Transaction;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TransactionTransformerTest extends BaseTestClass{

    @Mock private Transaction transaction;
    @Mock private List<Long> ids;
    @InjectMocks private TransactionTransformer transformer = new TransactionTransformer();

    @Test
    public void testTransformTransaction() throws Exception {
        Response response = transformer.transform(transaction);
        Response expectedResponse = getTransactionResponse();
        assertThat(response).isEqualToComparingFieldByField(expectedResponse);
    }

    @Test
    public void testTransformIds() throws Exception {
        Response response = transformer.transform(ids);
        Response expectedResponse = getIdsResponse();
        assertThat(response).isEqualToComparingFieldByField(expectedResponse);
    }

    private Response getIdsResponse() {
        return new IdsResponse(ids);
    }

    private Response getTransactionResponse(){
        return new TransactionResponse(transaction);
    }

}