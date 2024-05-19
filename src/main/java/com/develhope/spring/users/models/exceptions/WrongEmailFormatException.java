package com.develhope.spring.users.models.exceptions;

public class WrongEmailFormatException extends Exception{

    public WrongEmailFormatException(String message) {
        super(message);
    }
}
