package com.develhope.spring.model.user;

public class WrongEmailFormatException extends Exception{

    public WrongEmailFormatException(String message) {
        super(message);
    }
}
