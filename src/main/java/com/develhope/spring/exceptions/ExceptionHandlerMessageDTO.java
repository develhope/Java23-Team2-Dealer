package com.develhope.spring.exceptions;

import org.springframework.http.HttpStatus;

public class ExceptionHandlerMessageDTO {

    private String message;

    private HttpStatus status;

    public ExceptionHandlerMessageDTO(String message, HttpStatus status) {
        this.status = status;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
