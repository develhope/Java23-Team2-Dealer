package com.develhope.spring.deals.responseStatus;

import com.develhope.spring.exceptions.HttpRequestHandlingException;
import org.springframework.http.HttpStatus;


public class OrderNotFoundException extends HttpRequestHandlingException {

    private String message;

    public OrderNotFoundException(String message) {
        this.message = message;
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.BAD_REQUEST;
    }

    @Override
    public String getBody() {
        return this.message;
    }
}