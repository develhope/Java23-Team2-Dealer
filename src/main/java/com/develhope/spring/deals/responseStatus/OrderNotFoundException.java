package com.develhope.spring.deals.responseStatus;

import org.springframework.http.HttpStatus;

import java.util.NoSuchElementException;

public class OrderNotFoundException extends RuntimeException {
    private HttpStatus httpStatus;

    public OrderNotFoundException(String message, NoSuchElementException ex) {
        super(message, ex);
        this.httpStatus = HttpStatus.NOT_FOUND;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
