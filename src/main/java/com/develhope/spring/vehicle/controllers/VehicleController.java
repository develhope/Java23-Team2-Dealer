package com.develhope.spring.vehicle.controllers;

import com.develhope.spring.users.models.User;
import com.develhope.spring.users.responseStatus.UserNotFoundException;
import com.develhope.spring.vehicle.models.Vehicle;
import com.develhope.spring.vehicle.repositories.VehicleRepository;
import com.develhope.spring.vehicle.responseStatus.NotAuthorizedOperationException;
import com.develhope.spring.vehicle.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> getException(UserNotFoundException e) {
        return ResponseEntity.status(e.getHttpStatus()).body(e.getMessage());
    }

    @ExceptionHandler(NotAuthorizedOperationException.class)
    public ResponseEntity<String> getException(NotAuthorizedOperationException e) {
        return ResponseEntity.status(e.getHttpStatus()).body(e.getMessage());
    }

    @PostMapping("/{userId}")
    public ResponseEntity<Vehicle> create(@PathVariable long userId, @RequestBody Vehicle vehicle) {
        return ResponseEntity.status(HttpStatus.CREATED).body(vehicleService.create(userId, vehicle));
    }
}
