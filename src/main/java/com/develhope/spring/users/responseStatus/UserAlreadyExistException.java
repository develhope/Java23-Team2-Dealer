package com.develhope.spring.users.responseStatus;

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
