package com.develhope.spring.exceptions;

import com.develhope.spring.deals.responseStatus.NotAvailableVehicleException;
import com.develhope.spring.deals.responseStatus.OrderNotFoundException;
import com.develhope.spring.deals.responseStatus.RentalOverlappingDatesException;
import com.develhope.spring.deals.responseStatus.dtos.IllegalArgumentExceptionMessageDTO;
import com.develhope.spring.deals.responseStatus.dtos.NoSuchElementExceptionMessageDTO;
import com.develhope.spring.deals.responseStatus.dtos.NotAvailableVehicleExceptionMessageDTO;
import com.develhope.spring.users.responseStatus.UserAlreadyExistException;
import com.develhope.spring.vehicles.responseStatus.ExcessiveParameterException;
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
    public ResponseEntity<String> handleNotAuthorizedOperationException(NotAuthorizedOperationException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(ExcessiveParameterException.class)
    public ResponseEntity<String> handleExcessiveParameterException(ExcessiveParameterException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<IllegalArgumentExceptionMessageDTO> getException(IllegalArgumentException e, IllegalArgumentExceptionMessageDTO messageDTO) {
        messageDTO.setMessage(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(messageDTO);
    }

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<String> handleGlobalException(Exception ex, WebRequest request) {
//        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<NoSuchElementExceptionMessageDTO> getException(NoSuchElementException e, NoSuchElementExceptionMessageDTO messageDTO) {
        messageDTO.setMessage("No object correspond to the given ID");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(messageDTO);
    }

    @ExceptionHandler(NotAvailableVehicleException.class)
    public ResponseEntity<NotAvailableVehicleExceptionMessageDTO> getNotAvailableVehicleException(NotAvailableVehicleException e, NotAvailableVehicleExceptionMessageDTO messageDTO){
        messageDTO.setMessage(e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(messageDTO);
    }

    @ExceptionHandler(RentalOverlappingDatesException.class)
    public ResponseEntity<RentalOverlappingDatesException> getException(RentalOverlappingDatesException e) {
        return ResponseEntity.status(e.getHttpStatus()).body(e);
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

