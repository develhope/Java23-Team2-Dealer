package com.develhope.spring.users.responseStatus;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends RuntimeException{

    private String message;

    private final HttpStatus httpStatus;

    public UserNotFoundException (String message) {
        super(message);
        this.httpStatus = HttpStatus.NOT_FOUND;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }



}
