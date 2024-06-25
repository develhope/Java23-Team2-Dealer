package com.develhope.spring.deals.responseStatus.dtos;

public class IllegalArgumentExceptionMessageDTO {

    private String message;

    public IllegalArgumentExceptionMessageDTO(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
