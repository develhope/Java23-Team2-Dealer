package com.develhope.spring.deals.models;

import com.develhope.spring.deals.dtos.RentalCreatorDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RentalMapper {

    @Autowired
    private ModelMapper modelMapper;

//    public RentalMapper(ModelMapper modelMapper) {
//        this.modelMapper = modelMapper;
//    }

    private void addMapping() {
        modelMapper.addMappings(new PropertyMap<RentalCreatorDTO, Rental>() {
            @Override
            protected void configure() {
                map().setId(source.getVehicleId());
                map().getVehicle().setId(source.getVehicleId());
                map().setId(source.getUserId());
                map().getUser().setId(source.getUserId());
            }
        });
    }


public RentalCreatorDTO toDTO(Rental rental) {
        addMapping();
    return modelMapper.map(rental, RentalCreatorDTO.class);
}

public Rental toEntity(RentalCreatorDTO rentalCreatorDTO) {
        addMapping();
    return modelMapper.map(rentalCreatorDTO, Rental.class);
}

}
