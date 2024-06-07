package com.develhope.spring.deals.responseStatus;

import org.springframework.http.HttpStatus;

public class NotAvailableVehicleException extends RuntimeException {

    private HttpStatus httpStatus;

    public NotAvailableVehicleException() {
    }

    public NotAvailableVehicleException(String message) {
        super(message);
        this.httpStatus = HttpStatus.CONFLICT;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}