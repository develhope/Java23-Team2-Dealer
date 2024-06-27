package com.develhope.spring.exceptions;

import org.springframework.http.HttpStatus;

public class NotAuthorizedOperationException extends HttpRequestHandlingException {

    private final String message;

    public NotAuthorizedOperationException(String message) {
        this.message = message;
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.FORBIDDEN;
    }

    @Override
    public String getBody() {
        return this.message;
    }
}
