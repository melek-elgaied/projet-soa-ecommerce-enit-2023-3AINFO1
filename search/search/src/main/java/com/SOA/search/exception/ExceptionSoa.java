package com.SOA.search.exception;


import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ExceptionSoa extends  RuntimeException  {

    private String message;
public String getExceptionMessage(){
    return message;
}
}
