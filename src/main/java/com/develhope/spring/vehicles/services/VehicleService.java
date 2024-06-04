package com.develhope.spring.vehicles.services;

import com.develhope.spring.users.models.Roles;
import com.develhope.spring.users.models.User;
import com.develhope.spring.users.repositories.UserRepository;
import com.develhope.spring.users.responseStatus.UserNotFoundException;
import com.develhope.spring.vehicles.dtos.VehicleResponseDTO;
import com.develhope.spring.vehicles.dtos.VehicleStatusDTO;
import com.develhope.spring.vehicles.models.Vehicle;
import com.develhope.spring.vehicles.responseStatus.VehicleNotFoundException;
import com.develhope.spring.vehicles.repositories.VehicleRepository;
import com.develhope.spring.vehicles.responseStatus.NotAuthorizedOperationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.develhope.spring.vehicles.dtos.VehicleCreatorDTO;
import com.develhope.spring.vehicles.models.VehicleMapper;

import java.util.Optional;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VehicleMapper vehicleMapper;

    public VehicleResponseDTO create(long userId, VehicleCreatorDTO vehicleCreatorDTO) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException("Nessun utente con questo ID: " + userId + " è presente");
        }
        if (!optionalUser.get().getRoles().equals(Roles.ADMIN)) {
            throw new NotAuthorizedOperationException("Permesso negato. Non autorizzato a inserire nuovi veicoli");
        }

        Vehicle vehicle = vehicleMapper.toEntityFromCreator(vehicleCreatorDTO);
        return vehicleMapper.toResponseFrom(vehicleRepository.save(vehicle));
    }

    public VehicleCreatorDTO update(long userId, long vehicleId, VehicleCreatorDTO vehicleCreatorDTO) {
        checkUserAuthorizationBy(userId);
        Vehicle existingVehicle = findVehicleBy(vehicleId);


        existingVehicle = vehicleMapper.toEntityFromCreator(vehicleCreatorDTO);
        existingVehicle.setId(vehicleId);

        return vehicleMapper.toCreatorDTOFrom(vehicleRepository.save(existingVehicle));
    }

    public Vehicle updateStatus(long userId, long vehicleId, VehicleStatusDTO vehicleStatusDTO) {
        checkUserAuthorizationBy(userId);
        Vehicle existingVehicle = findVehicleBy(vehicleId);


        existingVehicle.setMarketStatus(vehicleStatusDTO.getMarketStatus());

        vehicleRepository.save(existingVehicle);
        return existingVehicle;
    }

    public void delete(long userId, long vehicleId) {
        checkUserAuthorizationBy(userId);
        vehicleRepository.deleteById(vehicleId);
    }

    private void checkUserAuthorizationBy(long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty() || !optionalUser.get().getRoles().equals(Roles.ADMIN)) {
            throw new NotAuthorizedOperationException("Permesso negato. Non autorizzato ad aggiornare i veicoli");
        }
    }

    private Vehicle findVehicleBy(long vehicleId) {
        Optional<Vehicle> optionalVehicle = vehicleRepository.findById(vehicleId);
        if (optionalVehicle.isEmpty()) {
            throw new VehicleNotFoundException("Nessun veicolo con questo ID: " + vehicleId + " è presente");
        }
        return optionalVehicle.get();
    }
}