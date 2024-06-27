package com.develhope.spring.users.responseStatus;

import com.develhope.spring.exceptions.HttpRequestHandlingException;
import org.springframework.http.HttpStatus;

public class UserAlreadyExistException extends HttpRequestHandlingException {

    private String message;

    public UserAlreadyExistException (String message) {
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
