package com.develhope.spring.vehicles.services;

import com.develhope.spring.users.models.Roles;
import com.develhope.spring.users.models.User;
import com.develhope.spring.users.repositories.UserRepository;
import com.develhope.spring.vehicles.dtos.VehicleReworkedDTO;
import com.develhope.spring.vehicles.dtos.VehicleSavedDTO;
import com.develhope.spring.vehicles.dtos.VehicleStatusDTO;
import com.develhope.spring.vehicles.models.Vehicle;
import com.develhope.spring.vehicles.repositories.VehicleRepository;
import com.develhope.spring.vehicles.responseStatus.NotAuthorizedOperationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.develhope.spring.vehicles.dtos.VehicleCreatorDTO;
import com.develhope.spring.vehicles.components.VehicleMapper;

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

    public VehicleReworkedDTO update(long vehicleId, VehicleCreatorDTO vehicleCreatorDTO) {
        Vehicle vehicleToUpdate = findVehicleBy(vehicleId);
        vehicleToUpdate.setVehicleType(vehicleCreatorDTO.getVehicleType());
        vehicleToUpdate.setBrand(vehicleCreatorDTO.getBrand());
        vehicleToUpdate.setModel(vehicleCreatorDTO.getModel());
        vehicleToUpdate.setDisplacement(vehicleCreatorDTO.getDisplacement());
        vehicleToUpdate.setColor(vehicleCreatorDTO.getColor());
        vehicleToUpdate.setPower(vehicleCreatorDTO.getPower());
        vehicleToUpdate.setGear(vehicleCreatorDTO.getGear());
        vehicleToUpdate.setRegistrationYear(vehicleCreatorDTO.getRegistrationYear());
        vehicleToUpdate.setPowerSupply(vehicleCreatorDTO.getPowerSupply());
        vehicleToUpdate.setPrice(vehicleCreatorDTO.getPrice());
        vehicleToUpdate.setUsedFlag(vehicleCreatorDTO.getUsedFlag());
        vehicleToUpdate.setMarketStatus(vehicleCreatorDTO.getMarketStatus());
        vehicleToUpdate.setEngine(vehicleCreatorDTO.getEngine());

        Vehicle updatedVehicle = vehicleRepository.save(vehicleToUpdate);
        return vehicleMapper.toReworkedDTO(updatedVehicle);
    }

    public VehicleReworkedDTO updateStatus(long vehicleId, VehicleStatusDTO vehicleStatusDTO) {
        Vehicle vehicleToUpdate = findVehicleBy(vehicleId);
        vehicleToUpdate.setMarketStatus(vehicleStatusDTO.getMarketStatus());
        Vehicle updatedVehicle = vehicleRepository.save(vehicleToUpdate);
        return vehicleMapper.toReworkedDTO(updatedVehicle);
    }

    //TODO Convertire autorizzazione
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