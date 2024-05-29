package com.develhope.spring.vehicles.controllers;

import com.develhope.spring.users.responseStatus.UserNotFoundException;
import com.develhope.spring.vehicles.dtos.VehicleStatusDTO;
import com.develhope.spring.vehicles.models.Vehicle;

import com.develhope.spring.vehicles.models.exception.VehicleNotFoundException;

import com.develhope.spring.vehicles.models.exceptions.VehicleNotFoundException;

import com.develhope.spring.vehicles.responseStatus.NotAuthorizedOperationException;
import com.develhope.spring.vehicles.services.VehicleService;
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

    @ExceptionHandler(VehicleNotFoundException.class)
    public ResponseEntity<String> handleVehicleNotFoundException(VehicleNotFoundException e) {
        return ResponseEntity.status(e.getHttpStatus()).body(e.getMessage());
    }

    @PostMapping("/{userId}")
    public ResponseEntity<Vehicle> create(@PathVariable long userId, @RequestBody Vehicle vehicle) {
        return ResponseEntity.status(HttpStatus.CREATED).body(vehicleService.create(userId, vehicle));
    }

    @PatchMapping("/{userId}/{vehicleId}/status")
    @ResponseStatus(HttpStatus.OK)
    public Vehicle updateStatus(@PathVariable long userId, @PathVariable long vehicleId, @RequestBody VehicleStatusDTO vehicleStatusDTO) {
        return vehicleService.updateVehicleStatus(userId, vehicleId, vehicleStatusDTO);

    @DeleteMapping("/{userId}/{vehicleId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void delete(@PathVariable long userId, @PathVariable long vehicleId) {
        vehicleService.deleteVehicle(userId, vehicleId);

    }
}
