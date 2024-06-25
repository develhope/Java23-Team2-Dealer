package com.develhope.spring.deals.responseStatus.dtos;

public class NoSuchElementExceptionMessageDTO {

    private String message;

    public NoSuchElementExceptionMessageDTO(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
