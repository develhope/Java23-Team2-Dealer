package com.develhope.spring.deals.responseStatus;

import org.springframework.http.HttpStatus;

import java.util.NoSuchElementException;

public class OrderNotFoundException extends RuntimeException {

    private HttpStatus httpStatus;

    public OrderNotFoundException(String message) {
        super(message);
        this.httpStatus = HttpStatus.BAD_REQUEST;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}