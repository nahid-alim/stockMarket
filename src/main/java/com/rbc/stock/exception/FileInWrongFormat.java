package com.rbc.stock.exception;

public class FileInWrongFormat extends RuntimeException {

    @Override
    public String getMessage() {
        return "Uploaded file is in wrong format.";
    }
}