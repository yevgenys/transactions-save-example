package com.transaction.save.example.controller.response;

public class SumResponse extends SuccessResponse {
    private final double sum;

    public SumResponse(double sum) {
        this.sum = sum;
    }

    public double getSum() {
        return sum;
    }

}
