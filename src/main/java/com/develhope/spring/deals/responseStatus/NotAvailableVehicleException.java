package com.develhope.spring.deals.responseStatus;

import com.develhope.spring.exceptions.HttpRequestHandlingException;
import org.springframework.http.HttpStatus;

public class NotAvailableVehicleException extends HttpRequestHandlingException {

    private String message;

    public NotAvailableVehicleException() {
    }

    public NotAvailableVehicleException(String message) {
        HttpStatus httpStatus = HttpStatus.CONFLICT;
        this.message = message;
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
