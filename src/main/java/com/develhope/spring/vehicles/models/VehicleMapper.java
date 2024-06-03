package com.develhope.spring.vehicles.models;

import com.develhope.spring.vehicles.dtos.VehicleCreatorDTO;
import com.develhope.spring.vehicles.dtos.VehicleOrderReturnerDTO;
import com.develhope.spring.vehicles.dtos.VehicleStatusDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VehicleMapper {

    @Autowired
    private ModelMapper modelMapper;

    public VehicleCreatorDTO toDTO(Vehicle vehicle) {
        return modelMapper.map(vehicle, VehicleCreatorDTO.class);
    }

    public Vehicle toEntity(VehicleCreatorDTO vehicleCreatorDTO) {
        return modelMapper.map(vehicleCreatorDTO, Vehicle.class);
    }

    public VehicleStatusDTO statusToDo(Vehicle vehicle) {
        return modelMapper.map(vehicle, VehicleStatusDTO.class);
    }

    public Vehicle statusToEntity(VehicleStatusDTO vehicleStatusDTO) {
        return modelMapper.map(vehicleStatusDTO, Vehicle.class);
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
