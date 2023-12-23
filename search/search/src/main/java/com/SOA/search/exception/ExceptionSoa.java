package com.SOA.search.exception;

public class ExceptionSoa extends RuntimeException {

    private String message = "";

    public ExceptionSoa(String message){
        super(message);
        this.message = message;
    }

    public String getExceptionMessage() {
        return message;
    }
}
