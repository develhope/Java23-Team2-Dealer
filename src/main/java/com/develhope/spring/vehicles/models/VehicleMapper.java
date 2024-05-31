package com.develhope.spring.vehicles.models;

import com.develhope.spring.vehicles.dtos.VehicleCreateDTO;
import com.develhope.spring.vehicles.dtos.VehicleDTO;
import com.develhope.spring.vehicles.dtos.VehicleUpdateDTO;
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
    public VehicleDTO toDTO(Vehicle vehicle) {
        return modelMapper.map(vehicle, VehicleDTO.class);
    }

    /**
     * Converte un VehicleCreateDTO in un Vehicle.
     *
     * @param vehicleCreateDTO il VehicleCreateDTO da convertire
     * @return l'entità Vehicle convertita
     */
    public Vehicle toEntity(VehicleCreateDTO vehicleCreateDTO) {
        return modelMapper.map(vehicleCreateDTO, Vehicle.class);
    }

    /**
     * Converte un VehicleUpdateDTO in un Vehicle.
     *
     * @param vehicleUpdateDTO il VehicleUpdateDTO da convertire
     * @return l'entità Vehicle convertita
     */
    public Vehicle toEntity(VehicleUpdateDTO vehicleUpdateDTO) {
        return modelMapper.map(vehicleUpdateDTO, Vehicle.class);
    }

}
