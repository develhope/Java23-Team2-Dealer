package com.develhope.spring.vehicles.services;

import com.develhope.spring.users.models.Roles;
import com.develhope.spring.users.models.User;
import com.develhope.spring.users.repositories.UserRepository;
import com.develhope.spring.users.responseStatus.UserNotFoundException;
import com.develhope.spring.vehicles.models.Vehicle;
import com.develhope.spring.vehicles.models.exceptions.VehicleNotFoundException;
import com.develhope.spring.vehicles.repositories.VehicleRepository;
import com.develhope.spring.vehicles.responseStatus.NotAuthorizedOperationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.develhope.spring.vehicles.dto.VehicleDTO;

import java.util.Optional;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private UserRepository userRepository;

    public VehicleDTO create(long userId, Vehicle vehicleDTO) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException("No user with this id: " + userId + " is present");
        }
        if (!(optionalUser.get().getRoles() == Roles.ADMIN)) {
            throw new NotAuthorizedOperationException("Permission denied. Not authorized to insert new vehicles");
        }

        Vehicle vehicle = new Vehicle();
        mapDTOtoVehicle(vehicleDTO, vehicle);

        return mapToDTO(vehicleRepository.save(vehicle));
    }

    public VehicleDTO updateVehicle(long userId, long vehicleId, Vehicle updatedVehicleDTO) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException("No user with this id: " + userId + " is present");
        }
        if (optionalUser.get().getRoles() != Roles.ADMIN) {
            throw new NotAuthorizedOperationException("Permission denied. Not authorized to update vehicles");
        }

        Optional<Vehicle> optionalVehicle = vehicleRepository.findById(vehicleId);
        if (optionalVehicle.isEmpty()) {
            throw new VehicleNotFoundException("No vehicle with this id: " + vehicleId + " is present");
        }

        Vehicle vehicle = optionalVehicle.get();
        mapDTOtoVehicle(updatedVehicleDTO, vehicle);

        return mapToDTO(vehicleRepository.save(vehicle));
    }

    /**
     * La mappatura è necessaria perché VehicleDTO è una rappresentazione dei dati trasferita tra il client e il server,
     * mentre Vehicle è un'entità persistente nel database.
     */

    private void mapDTOtoVehicle(Vehicle dto, Vehicle vehicle) {
        vehicle.setType(dto.getType());
        vehicle.setBrand(dto.getBrand());
        vehicle.setModel(dto.getModel());
        vehicle.setDisplacement(dto.getDisplacement());
        vehicle.setColor(dto.getColor());
        vehicle.setPower(dto.getPower());
        vehicle.setGear(dto.getGear());
        vehicle.setRegistrationYear(dto.getRegistrationYear());
        vehicle.setPowerSupply(dto.getPowerSupply());
        vehicle.setOriginalPrice(dto.getOriginalPrice());
        vehicle.setDiscountedPrice(dto.getDiscountedPrice());
        vehicle.setUsedFlag(dto.getUsedFlag());
        vehicle.setMarketStatus(dto.getMarketStatus());
        vehicle.setDiscountFlag(dto.isDiscountFlag());
        vehicle.setEngine(dto.getEngine());
    }

    private VehicleDTO mapToDTO(Vehicle vehicle) {
        VehicleDTO dto = new VehicleDTO();
        dto.setId(vehicle.getId());
        dto.setType(vehicle.getType());
        dto.setBrand(vehicle.getBrand());
        dto.setModel(vehicle.getModel());
        dto.setDisplacement(vehicle.getDisplacement());
        dto.setColor(vehicle.getColor());
        dto.setPower(vehicle.getPower());
        dto.setGear(vehicle.getGear());
        dto.setRegistrationYear(vehicle.getRegistrationYear());
        dto.setPowerSupply(vehicle.getPowerSupply());
        dto.setOriginalPrice(vehicle.getOriginalPrice());
        dto.setDiscountedPrice(vehicle.getDiscountedPrice());
        dto.setUsedFlag(vehicle.getUsedFlag());
        dto.setMarketStatus(vehicle.getMarketStatus());
        dto.setDiscountFlag(vehicle.isDiscountFlag());
        dto.setEngine(vehicle.getEngine());
        return dto;
    }
}
