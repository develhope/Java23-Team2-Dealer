package com.develhope.spring.deals.responseStatus.dtos;

public class NotAvailableVehicleExceptionMessageDTO {

    private String message;

    public NotAvailableVehicleExceptionMessageDTO(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
