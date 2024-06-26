package com.develhope.spring.vehicles.components;

import com.develhope.spring.vehicles.models.Vehicle;
import com.develhope.spring.vehicles.dtos.*;
import org.springframework.stereotype.Component;

/**
 * La mappatura è necessaria perché VehicleDTO è una rappresentazione dei dati trasferita tra il client e il server,
 * mentre Vehicle è un'entità persistente nel database.
 */
@Component
public class VehicleMapper {

    public VehicleCreatorDTO toCreatorDTO(Vehicle vehicle) {
        return new VehicleCreatorDTO(
                vehicle.getVehicleType(),
                vehicle.getBrand(),
                vehicle.getModel(),
                vehicle.getDisplacement(),
                vehicle.getColor(),
                vehicle.getPower(),
                vehicle.getGear(),
                vehicle.getRegistrationYear(),
                vehicle.getPowerSupply(),
                vehicle.getPrice(),
                vehicle.getDailyCost(),
                vehicle.getUsedFlag(),
                vehicle.getMarketStatus(),
                vehicle.getEngine()
        );
    }

    public Vehicle toEntity(VehicleCreatorDTO vehicleCreatorDTO) {
        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleType(vehicleCreatorDTO.getVehicleType());
        vehicle.setBrand(vehicleCreatorDTO.getBrand());
        vehicle.setModel(vehicleCreatorDTO.getModel());
        vehicle.setDisplacement(vehicleCreatorDTO.getDisplacement());
        vehicle.setColor(vehicleCreatorDTO.getColor());
        vehicle.setPower(vehicleCreatorDTO.getPower());
        vehicle.setGear(vehicleCreatorDTO.getGear());
        vehicle.setRegistrationYear(vehicleCreatorDTO.getRegistrationYear());
        vehicle.setPowerSupply(vehicleCreatorDTO.getPowerSupply());
        vehicle.setPrice(vehicleCreatorDTO.getPrice());
        vehicle.setDailyCost(vehicleCreatorDTO.getDailyCost());
        vehicle.setUsedFlag(vehicleCreatorDTO.getUsedFlag());
        vehicle.setMarketStatus(vehicleCreatorDTO.getMarketStatus());
        vehicle.setEngine(vehicleCreatorDTO.getEngine());
        return vehicle;
    }

    public VehicleStatusDTO toStatusDTO(Vehicle vehicle) {
        VehicleStatusDTO statusDTO = new VehicleStatusDTO();
        statusDTO.setMarketStatus(vehicle.getMarketStatus());
        return statusDTO;
    }

    public void updateVehicleMarketStatusFrom(Vehicle vehicle, VehicleStatusDTO statusDTO) {
        vehicle.setMarketStatus(statusDTO.getMarketStatus());
    }

    public VehicleResponseDTO toResponseDTO(Vehicle vehicle){
        return new VehicleResponseDTO(vehicle.getId(), vehicle.getVehicleType(), vehicle.getBrand(), vehicle.getModel(),
                vehicle.getRegistrationYear(), vehicle.getPowerSupply(),vehicle.getPrice(),vehicle.getUsedFlag(),
                vehicle.getEngine());
    }

    public VehicleOrderReturnerDTO toOrderReturnerDTO(Vehicle vehicle) {
        return new VehicleOrderReturnerDTO(
                vehicle.getId(),
                vehicle.getVehicleType(),
                vehicle.getBrand(),
                vehicle.getModel(),
                vehicle.getColor(),
                vehicle.getPrice(),
                vehicle.getUsedFlag(),
                vehicle.getEngine()
        );
    }


    public VehicleRentalReturnerDTO toRentalReturnerDTO(Vehicle vehicle) {
        return new VehicleRentalReturnerDTO(
                vehicle.getId(),
                vehicle.getVehicleType(),
                vehicle.getBrand(),
                vehicle.getModel(),
                vehicle.getColor(),
                vehicle.getGear(),
                vehicle.getPowerSupply(),
                vehicle.getDailyCost()
        );
    }

    public Vehicle toEntity(VehicleRentalReturnerDTO vehicleRentalReturnerDTO) {
        Vehicle vehicle = new Vehicle();
        vehicle.setId(vehicleRentalReturnerDTO.getId());
        vehicle.setVehicleType(vehicleRentalReturnerDTO.getVehicleType());
        vehicle.setBrand(vehicleRentalReturnerDTO.getBrand());
        vehicle.setModel(vehicleRentalReturnerDTO.getModel());
        vehicle.setColor(vehicleRentalReturnerDTO.getColor());
        vehicle.setGear(vehicleRentalReturnerDTO.getGear());
        vehicle.setPowerSupply(vehicleRentalReturnerDTO.getMotorPowerSupply());
        return vehicle;
    }

    public VehicleSavedDTO toSavedDTO(Vehicle vehicle) {
        return new VehicleSavedDTO(
                vehicle.getId(),
                vehicle.getVehicleType(),
                vehicle.getBrand(),
                vehicle.getModel(),
                vehicle.getColor(),
                vehicle.getPrice(),
                vehicle.getDailyCost(),
                vehicle.getUsedFlag(),
                vehicle.getMarketStatus()
        );
    }

    public VehicleReworkedDTO toReworkedDTO(Vehicle vehicle){
        return new VehicleReworkedDTO(
                vehicle.getId(),
                vehicle.getVehicleType(),
                vehicle.getBrand(),
                vehicle.getModel(),
                vehicle.getDisplacement(),
                vehicle.getColor(),
                vehicle.getPower(),
                vehicle.getGear(),
                vehicle.getRegistrationYear(),
                vehicle.getPowerSupply(),
                vehicle.getPrice(),
                vehicle.getDailyCost(),
                vehicle.getUsedFlag(),
                vehicle.getMarketStatus(),
                vehicle.getEngine()
        );
    }
}
