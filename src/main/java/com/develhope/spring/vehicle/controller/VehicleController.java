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


    @DeleteMapping("/{userId}/{vehicleId}")
    public ResponseEntity<Void> delete(@PathVariable long userId, @PathVariable long vehicleId) {
        vehicleService.deleteVehicle(userId, vehicleId);
        return ResponseEntity.noContent().build();
    }
}
