package com.rbc.stock.exception;

public class StockNotFound extends RuntimeException {

    @Override
    public String getMessage() {
        return "No record is found for this stock symbol.";
    }
}
