package com.SOA.search.exception;

import java.io.IOException;

public class BadRequestException extends ExceptionSoa{
    public BadRequestException(String message, IOException e) {
        super(message);
    }
}
