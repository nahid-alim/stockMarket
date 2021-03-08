package com.rbc.stock.exception;

import com.rbc.stock.api.constant.Constants;

public class FileTooBig extends RuntimeException {

    @Override
    public String getMessage() {
        return "Uploaded file size should not pass " + Constants.MAX_FILE_SIZE + "MB.";
    }
}
