package com.develhope.spring.vehicles.controllers;

import com.develhope.spring.vehicles.dtos.VehicleCreatorDTO;
import com.develhope.spring.vehicles.dtos.VehicleStatusDTO;
import com.develhope.spring.vehicles.models.Vehicle;
import com.develhope.spring.vehicles.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @Secured("ADMIN")
    @PostMapping("/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    public VehicleCreatorDTO create(@PathVariable long userId,
                                    @RequestBody VehicleCreatorDTO vehicleCreatorDTO) {
        return vehicleService.create(userId, vehicleCreatorDTO);
    }

    @PutMapping("/{userId}/{vehicleId}")
    @ResponseStatus(HttpStatus.OK)
    public VehicleCreatorDTO update(@PathVariable long userId,
                                    @PathVariable long vehicleId,
                                    @RequestBody VehicleCreatorDTO vehicleCreatorDTO) {
        return vehicleService.update(userId, vehicleId, vehicleCreatorDTO);
    }

    @PatchMapping("/{userId}/{vehicleId}/status")
    @ResponseStatus(HttpStatus.OK)
    public Vehicle updateStatus(@PathVariable long userId, @PathVariable long vehicleId,
                                @RequestBody VehicleStatusDTO vehicleStatusDTO) {
        return vehicleService.updateStatus(userId, vehicleId, vehicleStatusDTO);
    }

    @DeleteMapping("/{userId}/{vehicleId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void delete(@PathVariable long userId, @PathVariable long vehicleId) {
        vehicleService.delete(userId, vehicleId);
    }
}
