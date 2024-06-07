package com.develhope.spring.deals.responsestatus;

import org.springframework.http.HttpStatus;

public class NotAvilableOrderException extends RuntimeException{
    private HttpStatus httpStatus;

    private String message;

    public NotAvilableOrderException() {
    }

    public NotAvilableOrderException(String message) {
        super(message);
        this.httpStatus = HttpStatus.CONFLICT;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
