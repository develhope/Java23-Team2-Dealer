package com.develhope.spring.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpRequestHandlingException.class)
    public ResponseEntity<ExceptionHandlerMessageDTO> getException(HttpRequestHandlingException e) {
        ExceptionHandlerMessageDTO exceptionHandlerMessageDTO = new ExceptionHandlerMessageDTO(e.getBody(), e.getStatus());
        return ResponseEntity.status(e.getStatus()).body(exceptionHandlerMessageDTO);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ExceptionHandlerMessageDTO> getException(NoSuchElementException e) {
        ExceptionHandlerMessageDTO exceptionHandlerMessageDTO = new ExceptionHandlerMessageDTO(e.getMessage(), HttpStatus.BAD_REQUEST);
        return ResponseEntity.status(exceptionHandlerMessageDTO.getStatus()).body(exceptionHandlerMessageDTO);
    }

}

