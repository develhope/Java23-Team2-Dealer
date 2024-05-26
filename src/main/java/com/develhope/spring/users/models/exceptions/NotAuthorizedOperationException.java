package com.develhope.spring.users.models.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class NotAuthorizedOperationException extends RuntimeException {
    public NotAuthorizedOperationException(String message) {
        super(message);
    }

    public HttpStatus getHttpStatus() {
        return HttpStatus.FORBIDDEN;
    }
}
