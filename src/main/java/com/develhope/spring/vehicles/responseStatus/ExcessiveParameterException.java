package com.develhope.spring.vehicles.responseStatus;


public class ExcessiveParameterException extends RuntimeException{

    public ExcessiveParameterException(String message) {
        super(message);
    }
}
