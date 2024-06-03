package com.develhope.spring.vehicles.models;

import com.develhope.spring.vehicles.dtos.VehicleCreatorDTO;
import com.develhope.spring.vehicles.dtos.VehicleRentalReturnerDTO;
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

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Converte un Vehicle in un VehicleDTO.
     *
     * @param vehicle l'entità Vehicle da convertire
     * @return il VehicleDTO convertito
     */
    public VehicleCreatorDTO toCreatorDTO(Vehicle vehicle) {
        return modelMapper.map(vehicle, VehicleCreatorDTO.class);
    }

    /**
     * Converte un VehicleCreateDTO in un Vehicle.
     *
     * @param vehicleCreatorDTO il VehicleCreateDTO da convertire
     * @return l'entità Vehicle convertita
     */
    public Vehicle toEntity(VehicleCreatorDTO vehicleCreatorDTO) {
        return modelMapper.map(vehicleCreatorDTO, Vehicle.class);
    }


    public VehicleStatusDTO statusToDo(Vehicle vehicle){
        return modelMapper.map(vehicle, VehicleStatusDTO.class);
    }

    public Vehicle statusToEntity(VehicleStatusDTO vehicleStatusDTO){
        return modelMapper.map(vehicleStatusDTO, Vehicle.class);
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

    public Vehicle toEntityFrom(VehicleRentalReturnerDTO vehicleRentalReturnerDTO) {
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
