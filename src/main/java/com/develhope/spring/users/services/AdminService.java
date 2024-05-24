package com.develhope.spring.users.services;

import com.develhope.spring.vehicle.models.exceptions.VehicleNotFoundException;
import com.develhope.spring.vehicle.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private VehicleRepository vehicleRepository;

    public void deleteVehicle(long vehicleId) throws VehicleNotFoundException {
        if (vehicleRepository.existsById(vehicleId)) {
            vehicleRepository.deleteById(vehicleId);
        } else {
            throw new VehicleNotFoundException("Vehicle with id " + vehicleId + " not found");
        }
    }
}
