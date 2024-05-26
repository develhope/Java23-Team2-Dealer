package com.develhope.spring.vehicle.service;

import com.develhope.spring.users.models.Roles;
import com.develhope.spring.users.models.User;
import com.develhope.spring.users.models.exceptions.NotAuthorizedOperationException;
import com.develhope.spring.users.models.exceptions.UserNotFoundException;
import com.develhope.spring.users.repositories.UserRepository;
import com.develhope.spring.vehicle.models.Vehicle;
import com.develhope.spring.vehicle.models.exceptions.VehicleNotFoundException;
import com.develhope.spring.vehicle.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private UserRepository userRepository;


    public void deleteVehicle(long userId, long vehicleId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException("No user with this id: " + userId + " is present");
        }
        if (optionalUser.get().getRoles() != Roles.ADMIN) {
            throw new NotAuthorizedOperationException("Permission denied. Not authorized to delete vehicles");
        }
        Optional<Vehicle> optionalVehicle = vehicleRepository.findById(vehicleId);
        if (optionalVehicle.isEmpty()) {
            throw new VehicleNotFoundException("No vehicle with this id: " + vehicleId + " is present");
        }
        vehicleRepository.deleteById(vehicleId);
    }

}
