package com.develhope.spring.vehicles.controllers;

import com.develhope.spring.users.models.User;
import com.develhope.spring.vehicles.dtos.VehicleCreatorDTO;
import com.develhope.spring.vehicles.dtos.VehicleSavedDTO;
import com.develhope.spring.vehicles.dtos.VehicleStatusDTO;
import com.develhope.spring.vehicles.models.Vehicle;
import com.develhope.spring.vehicles.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public List<Vehicle> search(@RequestParam(required = false, value = "filter1") String filter1,
                                @RequestParam(required = false, value = "filter2") String filter2,
                                @RequestParam(required = false, value = "filter3") String filter3,
                                @RequestParam(required = false, value = "filter4") String filter4,
                                @RequestParam(required = false, value = "filter5") String filter5,
                                @RequestParam(required = false, value = "filter6") String filter6,
                                @RequestParam(required = false, value = "filter7") String filter7,
                                @RequestParam(required = false, value = "filter8") String filter8,
                                @RequestParam(required = false, value = "filter9") String filter9,
                                @RequestParam(required = false, value = "filter10") String filter10,
                                @RequestParam(required = false, value = "filter11") String filter11) {
        StringBuilder searchString = new StringBuilder();
        if (filter1 != null) searchString.append(filter1).append(",");
        if (filter2 != null) searchString.append(filter2).append(",");
        if (filter3 != null) searchString.append(filter3).append(",");
        if (filter4 != null) searchString.append(filter4).append(",");
        if (filter5 != null) searchString.append(filter5).append(",");
        if (filter6 != null) searchString.append(filter6).append(",");
        if (filter7 != null) searchString.append(filter7).append(",");
        if (filter8 != null) searchString.append(filter8).append(",");
        if (filter9 != null) searchString.append(filter9).append(",");
        if (filter10 != null) searchString.append(filter10).append(",");
        if (filter11 != null) searchString.append(filter11).append(",");

        if (!searchString.isEmpty() && searchString.charAt(searchString.length() - 1) == ',') {
            searchString.setLength(searchString.length() - 1);
        }
        return vehicleService.search(searchString.toString());
    }

    //TODO Convertire autorizzazione
    @PutMapping("/{userId}/{vehicleId}")
    @ResponseStatus(HttpStatus.OK)
    public VehicleCreatorDTO update(@PathVariable long userId,
                                    @PathVariable long vehicleId,
                                    @RequestBody VehicleCreatorDTO vehicleCreatorDTO) {
        return vehicleService.update(userId, vehicleId, vehicleCreatorDTO);
    }

    //TODO Convertire autorizzazione
    @PatchMapping("/{userId}/{vehicleId}/status")
    @ResponseStatus(HttpStatus.OK)
    public Vehicle updateStatus(@PathVariable long userId, @PathVariable long vehicleId,
                                @RequestBody VehicleStatusDTO vehicleStatusDTO) {
        return vehicleService.updateStatus(userId, vehicleId, vehicleStatusDTO);
    }

    //TODO Convertire autorizzazione
    @DeleteMapping("/{userId}/{vehicleId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void delete(@PathVariable long userId, @PathVariable long vehicleId) {
        vehicleService.delete(userId, vehicleId);
    }
}
