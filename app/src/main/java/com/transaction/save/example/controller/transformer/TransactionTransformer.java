package com.transaction.save.example.controller.transformer;

import com.transaction.save.example.controller.response.IdsResponse;
import com.transaction.save.example.controller.response.Response;
import com.transaction.save.example.controller.response.SumResponse;
import com.transaction.save.example.controller.response.TransactionResponse;
import com.transaction.save.example.model.Transaction;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TransactionTransformer {
    public Response transform(final Transaction transaction){
        return new TransactionResponse(transaction);
    }

    public Response transform(final List<Long> transaction){
        return new IdsResponse(transaction);
    }

    public Response transform(final double sum){
        return new SumResponse(sum);
    }

}
