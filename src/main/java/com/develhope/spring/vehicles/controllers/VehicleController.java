package com.develhope.spring.vehicles.controllers;

import com.develhope.spring.vehicles.dtos.*;
import com.develhope.spring.vehicles.models.Vehicle;
import com.develhope.spring.vehicles.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @Secured("ADMIN")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VehicleSavedDTO create(@RequestBody VehicleCreatorDTO vehicleCreatorDTO) {
        return vehicleService.create(vehicleCreatorDTO);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public Page<Vehicle> search(VehicleFilterDTO vehicleFilterDTO, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "25") int size) {
        return vehicleService.search(vehicleFilterDTO, page, size);
    }

    @Secured("ADMIN")
    @PutMapping("/{vehicleId}")
    @ResponseStatus(HttpStatus.OK)
    public VehicleReworkedDTO update(@PathVariable long vehicleId,
                                     @RequestBody VehicleCreatorDTO vehicleCreatorDTO) {
        return vehicleService.update(vehicleId, vehicleCreatorDTO);
    }

    @Secured({"ADMIN"})
    @PatchMapping("/{vehicleId}/status")
    @ResponseStatus(HttpStatus.OK)
    public VehicleReworkedDTO updateStatus( @PathVariable long vehicleId,
                                @RequestBody VehicleStatusDTO vehicleStatusDTO) {
        return vehicleService.updateStatus(vehicleId, vehicleStatusDTO);
    }

    @Secured({"ADMIN"})
    @PatchMapping("/{vehicleId}/discount-activate")
    @ResponseStatus(HttpStatus.OK)
    public VehicleDiscountedDTO setDiscount(@PathVariable long vehicleId,
                                            @RequestParam long discountPercentage) {
        return vehicleService.discountPrice(discountPercentage, vehicleId);
    }

    @Secured({"ADMIN"})
    @PatchMapping("/{vehicleId}/discount-remove")
    @ResponseStatus(HttpStatus.OK)
    public VehicleDiscountedDTO removeDiscount (@PathVariable long vehicleId) {
        return vehicleService.removeDiscountPrice(vehicleId);
    }

    //TODO Convertire autorizzazione
    @DeleteMapping("/{userId}/{vehicleId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void delete(@PathVariable long userId, @PathVariable long vehicleId) {
        vehicleService.delete(userId, vehicleId);
    }
}
