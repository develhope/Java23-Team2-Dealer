package com.develhope.spring.users.services;

import com.develhope.spring.vehicle.models.Vehicle;
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

    public Vehicle updateVehicle(long vehicleId, Vehicle updatedVehicle) throws VehicleNotFoundException {
        return vehicleRepository.findById(vehicleId)
                .map(vehicle -> {
                    vehicle.setType(updatedVehicle.getType());
                    vehicle.setBrand(updatedVehicle.getBrand());
                    vehicle.setModel(updatedVehicle.getModel());
                    vehicle.setDisplacement(updatedVehicle.getDisplacement());
                    vehicle.setColor(updatedVehicle.getColor());
                    vehicle.setPower(updatedVehicle.getPower());
                    vehicle.setGear(updatedVehicle.getGear());
                    vehicle.setRegistrationYear(updatedVehicle.getRegistrationYear());
                    vehicle.setPowerSupply(updatedVehicle.getPowerSupply());
                    vehicle.setOriginalPrice(updatedVehicle.getOriginalPrice());
                    vehicle.setDiscountedPrice(updatedVehicle.getDiscountedPrice());
                    vehicle.setUsedFlag(updatedVehicle.getUsedFlag());
                    vehicle.setMarketStatus(updatedVehicle.getMarketStatus());
                    vehicle.setDiscountFlag(updatedVehicle.getDiscountFlag());
                    vehicle.setEngine(updatedVehicle.getEngine());
                    return vehicleRepository.save(vehicle);
                })
                .orElseThrow(() -> new VehicleNotFoundException("Vehicle with id " + vehicleId + " not found"));
    }
}
