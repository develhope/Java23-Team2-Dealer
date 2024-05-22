package com.develhope.spring.users.services;

import com.develhope.spring.vehicle.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private VehicleRepository vehicleRepository;
}
