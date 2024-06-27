package com.develhope.spring.deals.responseStatus;

import com.develhope.spring.exceptions.HttpRequestHandlingException;
import org.springframework.http.HttpStatus;

public class RentalOverlappingDatesException extends HttpRequestHandlingException {

    private String message;

    public RentalOverlappingDatesException(String message) {
        this.message = message;
    }

    public RentalOverlappingDatesException() {
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.CONFLICT;
    }

    @Override
    public String getBody() {
        return this.message;
    }
}
