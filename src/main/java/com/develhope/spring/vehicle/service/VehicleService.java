package com.develhope.spring.vehicle.service;

import com.develhope.spring.vehicle.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;




    public void deleteVehicle(long id) {
        vehicleRepository.deleteById(id);
    }
}
