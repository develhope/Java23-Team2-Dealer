package com.develhope.spring.vehicles.services;

import com.develhope.spring.users.models.Roles;
import com.develhope.spring.users.models.User;
import com.develhope.spring.users.repositories.UserRepository;
import com.develhope.spring.users.responseStatus.UserNotFoundException;
import com.develhope.spring.vehicles.dtos.VehicleStatusDTO;
import com.develhope.spring.vehicles.models.Vehicle;
import com.develhope.spring.vehicles.models.exception.VehicleNotFoundException;
import com.develhope.spring.vehicles.repositories.VehicleRepository;
import com.develhope.spring.vehicles.responseStatus.NotAuthorizedOperationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private UserRepository userRepository;

    public Vehicle create(long userId, Vehicle vehicle) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException("No user with this id: " + userId + " is present");
        }
        if (!(optionalUser.get().getRoles() == Roles.ADMIN)) {
            throw new NotAuthorizedOperationException("Permission denied. Not authorized to insert new vehicles");
        }
        return vehicleRepository.save(vehicle);
    }

    public Vehicle updateVehicleStatus(long userId, long vehicleId, VehicleStatusDTO vehicleStatusDTO) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException("No user with this id: " + userId + " is present");
        }
        if (optionalUser.get().getRoles() != Roles.ADMIN) {
            throw new NotAuthorizedOperationException("Permission denied. Not authorized to update vehicle status");
        }
        Optional<Vehicle> optionalVehicle = vehicleRepository.findById(vehicleId);
        if (optionalVehicle.isEmpty()) {
            throw new VehicleNotFoundException("No vehicle with this id: " + vehicleId + " is present");
        }

        Vehicle existingVehicle = optionalVehicle.get();
        existingVehicle.setMarketStatus(vehicleStatusDTO.getMarketStatus());

        return vehicleRepository.save(existingVehicle);
    }

    //asda

}
