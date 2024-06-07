package com.develhope.spring.deals.models;

import com.develhope.spring.deals.dtos.RentalCreatorDTO;
import com.develhope.spring.deals.dtos.RentalReturnerDTO;
import com.develhope.spring.users.components.UserMapper;
import com.develhope.spring.users.dtos.BuyerRentalReturnerDto;
import com.develhope.spring.users.models.User;
import com.develhope.spring.vehicles.dtos.VehicleRentalReturnerDTO;
import com.develhope.spring.vehicles.models.Vehicle;
import com.develhope.spring.vehicles.models.VehicleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RentalMapper {

    @Autowired
    private VehicleMapper vehicleMapper;

    @Autowired
    private UserMapper userMapper;



    public Rental toEntity(RentalCreatorDTO rentalCreatorDTO) {
        Vehicle vehicle = new Vehicle(1);
        User user = new User(1);
        return new Rental(
                rentalCreatorDTO.getStartDate(),
                rentalCreatorDTO.getEndDate(),
                rentalCreatorDTO.getDailyCost(),
                rentalCreatorDTO.getTotalCost(),
                rentalCreatorDTO.isPaid(),
                vehicle,
                0,
                user
        );
    }

    public RentalReturnerDTO toReturnerDTO(Rental rental) {

        VehicleRentalReturnerDTO vehicleRentalReturnerDTO = vehicleMapper.toRentalReturnerDTO(rental.getVehicle());
        BuyerRentalReturnerDto buyerReturnerDto = userMapper.toRentalBuyerDTO(rental.getUser());

        return new RentalReturnerDTO(
                rental.getId(),
                rental.getStartDate(),
                rental.getEndDate(),
                rental.getDailyCost(),
                rental.isPaid(),
                vehicleRentalReturnerDTO,
                buyerReturnerDto
        );
    }

    public Rental toEntity(RentalReturnerDTO rentalReturnerDTO) {
        Vehicle vehicle = vehicleMapper.toEntity(rentalReturnerDTO.getVehicle());
        User user = userMapper.toEntity(rentalReturnerDTO.getBuyer());
        return new Rental(rentalReturnerDTO.getStartDate(), rentalReturnerDTO.getEndDate(), rentalReturnerDTO.getDailyCost(), rentalReturnerDTO.getTotalCost(), rentalReturnerDTO.isPaid(), vehicle, 0, user);
    }
}
