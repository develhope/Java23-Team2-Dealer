package com.develhope.spring.vehicle.responseStatus;

import org.springframework.http.HttpStatus;

public class NotAuthorizedOperationException extends RuntimeException{

    private final HttpStatus httpStatus;

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

   public NotAuthorizedOperationException(String message) {
        super(message);
        this.httpStatus = HttpStatus.FORBIDDEN;;
   }
}
