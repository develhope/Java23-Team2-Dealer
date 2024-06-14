package com.develhope.spring.deals.responseStatus;

import org.springframework.http.HttpStatus;

import java.util.NoSuchElementException;

public class OrderNotFoundException extends RuntimeException{

    private HttpStatus httpStatus;

    public OrderNotFoundException(NoSuchElementException e) {
        this.httpStatus = HttpStatus.BAD_REQUEST;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}