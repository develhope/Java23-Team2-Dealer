package com.develhope.spring.vehicles.models;

import com.develhope.spring.vehicles.dtos.VehicleCreatorDTO;
import com.develhope.spring.vehicles.dtos.VehicleOrderReturnerDTO;
import com.develhope.spring.vehicles.dtos.VehicleRentalReturnerDTO;
import com.develhope.spring.vehicles.dtos.VehicleStatusDTO;
import org.springframework.stereotype.Component;

/**
 * La mappatura è necessaria perché VehicleDTO è una rappresentazione dei dati trasferita tra il client e il server,
 * mentre Vehicle è un'entità persistente nel database.
 */
@Component
public class VehicleMapper {

    public VehicleCreatorDTO toVehicleCreatorDTOFrom(Vehicle vehicle) {
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
                vehicle.getOriginalPrice(),
                vehicle.getDiscountedPrice(),
                vehicle.getUsedFlag(),
                vehicle.getMarketStatus(),
                vehicle.isDiscountFlag(),
                vehicle.getEngine()
        );
    }

    public Vehicle toEntityFrom(VehicleCreatorDTO vehicleCreatorDTO) {
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
        vehicle.setOriginalPrice(vehicleCreatorDTO.getOriginalPrice());
        vehicle.setDiscountedPrice(vehicleCreatorDTO.getDiscountedPrice());
        vehicle.setUsedFlag(vehicleCreatorDTO.getUsedFlag());
        vehicle.setMarketStatus(vehicleCreatorDTO.getMarketStatus());
        vehicle.setDiscountFlag(vehicleCreatorDTO.isDiscountFlag());
        vehicle.setEngine(vehicleCreatorDTO.getEngine());
        return vehicle;
    }

    public VehicleStatusDTO toVehicleStatusDTOFrom(Vehicle vehicle) {
        VehicleStatusDTO statusDTO = new VehicleStatusDTO();
        statusDTO.setMarketStatus(vehicle.getMarketStatus());
        return statusDTO;
    }

    public void updateVehicleMarketStatusFrom(Vehicle vehicle, VehicleStatusDTO statusDTO) {
        vehicle.setMarketStatus(statusDTO.getMarketStatus());
    }


    public VehicleOrderReturnerDTO toOrderReturnerDTOFrom(Vehicle vehicle) {
        return new VehicleOrderReturnerDTO(
                vehicle.getId(),
                vehicle.getVehicleType(),
                vehicle.getBrand(),
                vehicle.getModel(),
                vehicle.getColor(),
                vehicle.getOriginalPrice(),
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
                vehicle.getPowerSupply()
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
}
