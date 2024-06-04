package com.develhope.spring.deals.components;

import com.develhope.spring.deals.dtos.RentalCreatorDTO;
import com.develhope.spring.deals.dtos.RentalReturnerDTO;
import com.develhope.spring.deals.models.Rental;
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



    public Rental toEntityFrom(RentalCreatorDTO rentalCreatorDTO) {
        Vehicle vehicle = new Vehicle();
        vehicle.setId(rentalCreatorDTO.getVehicleId());
        User user = new User();
        user.setId(rentalCreatorDTO.getUserId());
        return new Rental(
                rentalCreatorDTO.getStartDate(),
                rentalCreatorDTO.getEndDate(),
                rentalCreatorDTO.getDailyCost(),
                rentalCreatorDTO.isPaid(),
                vehicle,
                0,
                user
        );
    }

    public RentalReturnerDTO toReturnerDTOFrom(Rental rental) {

        VehicleRentalReturnerDTO vehicleRentalReturnerDTO = vehicleMapper.toRentalReturnerDTO(rental.getVehicle());
        BuyerRentalReturnerDto buyerReturnerDto = userMapper.toRentalBuyerDTO(rental.getUser());

        return new RentalReturnerDTO(
                rental.getStartDate(),
                rental.getEndDate(),
                rental.getDailyCost(),
                rental.isPaid(),
                vehicleRentalReturnerDTO,
                buyerReturnerDto
        );
    }

    public Rental toEntityFrom(RentalReturnerDTO rentalReturnerDTO) {
        Vehicle vehicle = vehicleMapper.toEntityFrom(rentalReturnerDTO.getVehicle());
        User user = userMapper.toEntityFrom(rentalReturnerDTO.getBuyer());
        return new Rental(rentalReturnerDTO.getStartDate(), rentalReturnerDTO.getEndDate(), rentalReturnerDTO.getDailyCost(), rentalReturnerDTO.isPaid(), vehicle, 0, user);
    }
}
