package com.develhope.spring.deals.controllers;

import com.develhope.spring.deals.dtos.RentalCreatorDTO;
import com.develhope.spring.deals.dtos.RentalReturnerDTO;
import com.develhope.spring.deals.repositories.RentalRepository;
import com.develhope.spring.deals.responseStatus.NotAvailableVehicleException;
import com.develhope.spring.deals.responseStatus.RentalOverlappingDatesException;
import com.develhope.spring.deals.services.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/v1/rentals")
public class RentalController {

    @Autowired
    private RentalService rentalService;

    @Autowired
    private RentalRepository rentalRepository;

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<NoSuchElementException> getException(NoSuchElementException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e);
    }

    @ExceptionHandler(NotAvailableVehicleException.class)
    public ResponseEntity<NotAvailableVehicleException> getException(NotAvailableVehicleException e) {
        return ResponseEntity.status(e.getHttpStatus()).body(e);
    }

    @ExceptionHandler(RentalOverlappingDatesException.class)
    public ResponseEntity<RentalOverlappingDatesException> getException(RentalOverlappingDatesException e) {
        return ResponseEntity.status(e.getHttpStatus()).body(e);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public RentalReturnerDTO create (@RequestBody RentalCreatorDTO rentalCreatorDTO) {
        return rentalService.create(rentalCreatorDTO);
    }
}
