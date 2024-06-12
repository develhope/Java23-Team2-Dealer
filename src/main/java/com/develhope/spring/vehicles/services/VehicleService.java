package com.develhope.spring.vehicles.services;

import com.develhope.spring.users.models.Roles;
import com.develhope.spring.users.models.User;
import com.develhope.spring.users.repositories.UserRepository;
import com.develhope.spring.vehicles.dtos.VehicleSavedDTO;
import com.develhope.spring.vehicles.dtos.VehicleStatusDTO;
import com.develhope.spring.vehicles.models.Vehicle;
import com.develhope.spring.vehicles.repositories.VehicleRepository;
import com.develhope.spring.vehicles.responseStatus.NotAuthorizedOperationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import com.develhope.spring.vehicles.dtos.VehicleCreatorDTO;
import com.develhope.spring.vehicles.models.VehicleMapper;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class VehicleService {

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VehicleMapper vehicleMapper;

    public VehicleSavedDTO create(VehicleCreatorDTO vehicleCreatorDTO) {
        Vehicle vehicle = vehicleMapper.toEntity(vehicleCreatorDTO);
        Vehicle savedVehicle = vehicleRepository.save(vehicle);
        return vehicleMapper.toSavedDTO(savedVehicle);
    }

    public VehicleCreatorDTO update(long userId, long vehicleId, VehicleCreatorDTO vehicleCreatorDTO) {
        checkUserAuthorizationBy(userId);
        Vehicle existingVehicle;
        existingVehicle = vehicleMapper.toEntity(vehicleCreatorDTO);
        existingVehicle.setId(vehicleId);
        Vehicle updatedVehicle = vehicleRepository.save(existingVehicle);
        return vehicleMapper.toCreatorDTO(updatedVehicle);
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
        User user = userRepository.findById(userId).orElseThrow(NoSuchElementException::new);
        if (!user.getRoles().equals(Roles.ADMIN)) {
            throw new NotAuthorizedOperationException("Permesso negato. Non autorizzato ad aggiornare i veicoli");
        }
    }

    public Vehicle findVehicleBy(long vehicleId) {
        Optional<Vehicle> optionalVehicle = vehicleRepository.findById(vehicleId);
        return optionalVehicle.orElseThrow();
    }
}