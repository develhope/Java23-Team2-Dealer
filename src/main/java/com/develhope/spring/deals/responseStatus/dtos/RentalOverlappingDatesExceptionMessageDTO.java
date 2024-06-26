package com.develhope.spring.deals.responseStatus.dtos;

public class RentalOverlappingDatesExceptionMessageDTO {

    private String message;

    public RentalOverlappingDatesExceptionMessageDTO(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
