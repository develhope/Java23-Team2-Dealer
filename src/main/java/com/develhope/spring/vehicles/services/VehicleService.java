package com.develhope.spring.vehicles.services;

import com.develhope.spring.users.models.Roles;
import com.develhope.spring.users.models.User;
import com.develhope.spring.users.repositories.UserRepository;
import com.develhope.spring.users.responseStatus.UserNotFoundException;
import com.develhope.spring.vehicles.dtos.VehicleDTO;
import com.develhope.spring.vehicles.dtos.VehicleUpdateDTO;
import com.develhope.spring.vehicles.dtos.VehicleStatusDTO;
import com.develhope.spring.vehicles.models.Vehicle;
import com.develhope.spring.vehicles.models.exceptions.VehicleNotFoundException;
import com.develhope.spring.vehicles.repositories.VehicleRepository;
import com.develhope.spring.vehicles.responseStatus.NotAuthorizedOperationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.develhope.spring.vehicles.dtos.VehicleCreateDTO;

import java.util.Optional;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Vehicle.VehicleMapper vehicleMapper;

    public VehicleDTO create(long userId, VehicleCreateDTO vehicleCreateDTO) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException("Nessun utente con questo ID: " + userId + " è presente");
        }
        if (!optionalUser.get().getRoles().equals(Roles.ADMIN)) {
            throw new NotAuthorizedOperationException("Permesso negato. Non autorizzato a inserire nuovi veicoli");
        }

        Vehicle vehicle = vehicleMapper.toEntity(vehicleCreateDTO);
        return vehicleMapper.toDTO(vehicleRepository.save(vehicle));
    }

    public VehicleDTO updateVehicle(long userId, long vehicleId, VehicleUpdateDTO updatedVehicleDTO) {
        checkUserAuthorization(userId);
        Vehicle existingVehicle = findVehicleById(vehicleId);


        existingVehicle = vehicleMapper.toEntity(updatedVehicleDTO);
        existingVehicle.setId(vehicleId);

        return vehicleMapper.toDTO(vehicleRepository.save(existingVehicle));
    }

    public Vehicle updateVehicleStatus(long userId, long vehicleId, VehicleStatusDTO vehicleStatusDTO) {
        checkUserAuthorization(userId);
        Vehicle existingVehicle = findVehicleById(vehicleId);


        existingVehicle.setMarketStatus(vehicleStatusDTO.getMarketStatus());

        vehicleRepository.save(existingVehicle);
        return existingVehicle;
    }

    public void deleteVehicle(long userId, long vehicleId) {
        checkUserAuthorization(userId);
        vehicleRepository.deleteById(vehicleId);
    }

    private void checkUserAuthorization(long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty() || !optionalUser.get().getRoles().equals(Roles.ADMIN)) {
            throw new NotAuthorizedOperationException("Permesso negato. Non autorizzato ad aggiornare i veicoli");
        }
    }

    private Vehicle findVehicleById(long vehicleId) {
        Optional<Vehicle> optionalVehicle = vehicleRepository.findById(vehicleId);
        if (optionalVehicle.isEmpty()) {
            throw new VehicleNotFoundException("Nessun veicolo con questo ID: " + vehicleId + " è presente");
        }
        return optionalVehicle.get();
    }
}