package com.develhope.spring.vehicles.responseStatus;

import org.springframework.http.HttpStatus;



public class VehicleNotFoundException extends RuntimeException {

    private HttpStatus httpStatus;

    private String message;

    public VehicleNotFoundException() {
    }

    public VehicleNotFoundException(String message) {
        super(message);
        this.httpStatus = HttpStatus.NOT_FOUND;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
