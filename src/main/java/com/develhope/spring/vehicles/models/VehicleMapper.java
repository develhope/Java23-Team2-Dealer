package com.develhope.spring.vehicles.models;

import com.develhope.spring.vehicles.dtos.VehicleCreatorDTO;
import com.develhope.spring.vehicles.dtos.VehicleOrderReturnerDTO;
import com.develhope.spring.vehicles.dtos.VehicleStatusDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * La mappatura è necessaria perché VehicleDTO è una rappresentazione dei dati trasferita tra il client e il server,
 * mentre Vehicle è un'entità persistente nel database.
 */
@Component
public class VehicleMapper {

    public static VehicleCreatorDTO toVehicleCreatorDTOFrom(Vehicle vehicle) {
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

    public static Vehicle toVehicleFrom(VehicleCreatorDTO dto) {
        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleType(dto.getVehicleType());
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
        return vehicle;
    }

    public static VehicleStatusDTO toVehicleStatusDTOFrom(Vehicle vehicle) {
        VehicleStatusDTO statusDTO = new VehicleStatusDTO();
        statusDTO.setMarketStatus(vehicle.getMarketStatus());
        return statusDTO;
    }

    public static void updateVehicleMarketStatusFrom(Vehicle vehicle, VehicleStatusDTO statusDTO) {
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

}
