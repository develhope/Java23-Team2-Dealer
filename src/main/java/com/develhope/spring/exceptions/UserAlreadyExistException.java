package com.develhope.spring.exceptions;

import org.springframework.http.HttpStatus;

public class UserAlreadyExistException extends RuntimeException {

    private HttpStatus httpStatus;

    public UserAlreadyExistException (String message) {
        super(message);
        this.httpStatus = HttpStatus.CONFLICT;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
