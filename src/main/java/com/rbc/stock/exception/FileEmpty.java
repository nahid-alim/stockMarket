package com.rbc.stock.exception;

public class FileEmpty extends RuntimeException {

    @Override
    public String getMessage() {
        return "Uploaded file is empty.";
    }
}
