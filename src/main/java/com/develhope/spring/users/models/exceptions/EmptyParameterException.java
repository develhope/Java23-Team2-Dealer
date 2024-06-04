package com.develhope.spring.users.models.exceptions;

public class EmptyParameterException extends RuntimeException {
    public EmptyParameterException(String message) {
        super(message);
    }
}
