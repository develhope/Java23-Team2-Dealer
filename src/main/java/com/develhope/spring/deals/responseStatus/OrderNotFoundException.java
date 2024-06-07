package com.develhope.spring.deals.responseStatus;

import org.springframework.http.HttpStatus;

public class OrderNotFoundException extends RuntimeException {
    private HttpStatus httpStatus;

    public OrderNotFoundException() {
    }

    public OrderNotFoundException(String message) {
        super(message);
        this.httpStatus = HttpStatus.CONFLICT;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
