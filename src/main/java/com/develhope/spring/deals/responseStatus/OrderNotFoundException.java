package com.develhope.spring.deals.responseStatus;

import org.springframework.http.HttpStatus;


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