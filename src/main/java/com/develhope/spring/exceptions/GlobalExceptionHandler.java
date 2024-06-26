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

//    @ExceptionHandler(NotAuthorizedOperationException.class)
//    public ResponseEntity<String> handleNotAuthorizedOperationException(NotAuthorizedOperationException ex, WebRequest request) {
//        return new ResponseEntity<>(ex.getMessage(), HttpStatus.FORBIDDEN);
//    }
//
//    @ExceptionHandler(ExcessiveParameterException.class)
//    public ResponseEntity<String> handleExcessiveParameterException(ExcessiveParameterException ex, WebRequest request) {
//        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
//    }

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<String> handleGlobalException(Exception ex, WebRequest request) {
//        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<NoSuchElementException> getException(NoSuchElementException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
    }
//
//    @ExceptionHandler(NotAvailableVehicleException.class)
//    public ResponseEntity<ExceptionHandlerMessageDTO> getNotAvailableVehicleException(NotAvailableVehicleException e){
//        ExceptionHandlerMessageDTO exceptionHandlerMessageDTO = new ExceptionHandlerMessageDTO(e.getMessage());
//        return ResponseEntity.status(HttpStatus.CONFLICT).body(exceptionHandlerMessageDTO);
//    }
//
//    @ExceptionHandler(RentalOverlappingDatesException.class)
//    public ResponseEntity<RentalOverlappingDatesExceptionMessageDTO> getException(RentalOverlappingDatesException e) {
//        RentalOverlappingDatesExceptionMessageDTO rentalOverlappingDatesExceptionMessageDTO = new RentalOverlappingDatesExceptionMessageDTO(e.getMessage());
//        return ResponseEntity.status(e.getHttpStatus()).body(rentalOverlappingDatesExceptionMessageDTO);
//    }
//
//    @ExceptionHandler(UserAlreadyExistException.class)
//    public ResponseEntity<UserAlreadyExistException> getException(UserAlreadyExistException e) {
//        return ResponseEntity.status(e.getHttpStatus()).body(e);
//    }
//
//    @ExceptionHandler(OrderNotFoundException.class)
//    public ResponseEntity<OrderNotFoundException> handleOrderNotFoundException(OrderNotFoundException e) {
//        return ResponseEntity.status(e.getHttpStatus()).body(e);
//    }

}

