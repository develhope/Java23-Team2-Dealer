package com.develhope.spring.exceptions;

import com.develhope.spring.deals.models.exceptions.OrderCreationException;
import com.develhope.spring.deals.responsestatus.NotAvailableVehicleException;
import com.develhope.spring.users.models.exceptions.EmptyParameterException;
import com.develhope.spring.users.models.exceptions.WrongEmailFormatException;
import com.develhope.spring.users.responseStatus.UserNotFoundException;
import com.develhope.spring.vehicles.models.exceptions.ExcessiveParameterException;
import com.develhope.spring.vehicles.responseStatus.NotAuthorizedOperationException;
import com.develhope.spring.vehicles.responseStatus.VehicleNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(VehicleNotFoundException.class)
    public ResponseEntity<String> handleVehicleNotFoundException(VehicleNotFoundException ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(OrderCreationException.class)
    public ResponseEntity<String> handleOrderCreationException(OrderCreationException ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NotAuthorizedOperationException.class)
    public ResponseEntity<String> handleNotAuthorizedOperationException(NotAuthorizedOperationException ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(ExcessiveParameterException.class)
    public ResponseEntity<String> handleExcessiveParameterException(ExcessiveParameterException ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmptyParameterException.class)
    public ResponseEntity<String> handleEmptyParameterException(EmptyParameterException ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(WrongEmailFormatException.class)
    public ResponseEntity<String> handleWrongEmailFormatException(WrongEmailFormatException ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGlobalException(Exception ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<NoSuchElementException> getException(NoSuchElementException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e);
    }

    @ExceptionHandler(NotAvailableVehicleException.class)
    public ResponseEntity<NotAvailableVehicleException> getNotAvailavleVehicleExceptiion(NotAvailableVehicleException e){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e);
    }
}

