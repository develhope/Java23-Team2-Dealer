package com.develhope.spring.vehicles.responseStatus;


import com.develhope.spring.exceptions.HttpRequestHandlingException;
import org.springframework.http.HttpStatus;

public class ExcessiveParameterException extends HttpRequestHandlingException {

    private String message;

    public ExcessiveParameterException(String message) {
      this.message = message;
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.BAD_REQUEST;
    }

    @Override
    public String getBody() {
        return this.message;
    }
}
