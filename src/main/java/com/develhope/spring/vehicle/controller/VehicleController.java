package com.develhope.spring.vehicle.controller;


import com.develhope.spring.users.models.exceptions.NotAuthorizedOperationException;
import com.develhope.spring.users.models.exceptions.UserNotFoundException;
import com.develhope.spring.vehicle.models.exceptions.VehicleNotFoundException;
import com.develhope.spring.vehicle.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;






    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException e) {
        return ResponseEntity.status(e.getHttpStatus()).body(e.getMessage());
    }

    @ExceptionHandler(NotAuthorizedOperationException.class)
    public ResponseEntity<String> handleNotAuthorizedOperationException(NotAuthorizedOperationException e) {
        return ResponseEntity.status(e.getHttpStatus()).body(e.getMessage());
    }

    @ExceptionHandler(VehicleNotFoundException.class)
    public ResponseEntity<String> handleVehicleNotFoundException(VehicleNotFoundException e) {
        return ResponseEntity.status(e.getHttpStatus()).body(e.getMessage());
    }

    @DeleteMapping("/{userId}/{vehicleId}")
    public ResponseEntity<Void> delete(@PathVariable long userId, @PathVariable long vehicleId) {
        vehicleService.deleteVehicle(userId, vehicleId);
        return ResponseEntity.noContent().build();
    }
}
