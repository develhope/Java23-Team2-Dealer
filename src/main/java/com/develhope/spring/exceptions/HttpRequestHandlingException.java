package com.develhope.spring.exceptions;

import org.springframework.http.HttpStatus;

public abstract class HttpRequestHandlingException extends RuntimeException{

    public abstract HttpStatus getStatus();

    public abstract String getBody();
}
