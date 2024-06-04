package com.develhope.spring.deals.responseStatus;

import org.springframework.http.HttpStatus;

public class RentalOverlappingDatesException extends RuntimeException {

    private HttpStatus httpStatus;

    private String message;

    public RentalOverlappingDatesException(String message) {
        super(message);
        this.httpStatus = HttpStatus.CONFLICT;
    }

    public RentalOverlappingDatesException() {
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
