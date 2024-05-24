package com.develhope.spring.users.responseStatus;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends RuntimeException{

    private HttpStatus httpStatus;

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public UserNotFoundException (String message) {
        super(message);
        this.httpStatus = HttpStatus.NOT_FOUND;
    }
}
