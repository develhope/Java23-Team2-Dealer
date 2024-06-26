package com.develhope.spring.exceptions;

import com.develhope.spring.deals.responseStatus.NotAvailableVehicleException;
import com.develhope.spring.deals.responseStatus.OrderNotFoundException;
import com.develhope.spring.deals.responseStatus.RentalOverlappingDatesException;
import com.develhope.spring.deals.responseStatus.dtos.NotAvailableVehicleExceptionMessageDTO;
import com.develhope.spring.deals.responseStatus.dtos.RentalOverlappingDatesExceptionMessageDTO;
import com.develhope.spring.vehicles.responseStatus.NotAuthorizedOperationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotAuthorizedOperationException.class)
    public ResponseEntity<String> handleNotAuthorizedOperationException(NotAuthorizedOperationException ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(ExcessiveParameterException.class)
    public ResponseEntity<String> handleExcessiveParameterException(ExcessiveParameterException ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<String> handleGlobalException(Exception ex, WebRequest request) {
//        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<NoSuchElementException> getException(NoSuchElementException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e);
    }

    @ExceptionHandler(NotAvailableVehicleException.class)
    public ResponseEntity<NotAvailableVehicleExceptionMessageDTO> getNotAvailableVehicleException(NotAvailableVehicleException e){
        NotAvailableVehicleExceptionMessageDTO notAvailableVehicleExceptionMessageDTO = new NotAvailableVehicleExceptionMessageDTO(e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(notAvailableVehicleExceptionMessageDTO);
    }

    @ExceptionHandler(RentalOverlappingDatesException.class)
    public ResponseEntity<RentalOverlappingDatesExceptionMessageDTO> getException(RentalOverlappingDatesException e) {
        RentalOverlappingDatesExceptionMessageDTO rentalOverlappingDatesExceptionMessageDTO = new RentalOverlappingDatesExceptionMessageDTO(e.getMessage());
        return ResponseEntity.status(e.getHttpStatus()).body(rentalOverlappingDatesExceptionMessageDTO);
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<UserAlreadyExistException> getException(UserAlreadyExistException e) {
        return ResponseEntity.status(e.getHttpStatus()).body(e);
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<OrderNotFoundException> handleOrderNotFoundException(OrderNotFoundException e) {
        return ResponseEntity.status(e.getHttpStatus()).body(e);
    }

}

