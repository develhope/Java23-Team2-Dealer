package com.develhope.spring.deals.components;

import com.develhope.spring.deals.dtos.rentals.RentalCreatorDTO;
import com.develhope.spring.deals.dtos.rentals.RentalReturnerDTO;
import com.develhope.spring.deals.dtos.rentals.RentalUpdaterDTO;
import com.develhope.spring.deals.models.Rental;
import com.develhope.spring.users.components.UserMapper;
import com.develhope.spring.users.dtos.BuyerRentalReturnerDto;
import com.develhope.spring.users.models.User;
import com.develhope.spring.vehicles.dtos.VehicleRentalReturnerDTO;
import com.develhope.spring.vehicles.models.Vehicle;
import com.develhope.spring.vehicles.components.VehicleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RentalMapper {

    @Autowired
    private VehicleMapper vehicleMapper;

    @Autowired
    private UserMapper userMapper;


    public Rental toEntity(RentalCreatorDTO rentalCreatorDTO) {
        Vehicle vehicle = new Vehicle(rentalCreatorDTO.getVehicleId());
        User user = new User(rentalCreatorDTO.getUserId());
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

    public Rental toEntity(RentalUpdaterDTO rentalUpdaterDTO) {
        Vehicle vehicle = new Vehicle(rentalUpdaterDTO.getVehicleId());
        User user = new User(0);
        return new Rental(
                rentalUpdaterDTO.getStartDate(),
                rentalUpdaterDTO.getEndDate(),
                rentalUpdaterDTO.getDailyCost(),
                rentalUpdaterDTO.getTotalCost(),
                rentalUpdaterDTO.isPaid(),
                vehicle,
                0,
                user);
    }

    public RentalReturnerDTO toReturnerDTO(Rental rental) {

          VehicleRentalReturnerDTO vehicleRentalReturnerDTO = vehicleMapper.toRentalReturnerDTO(rental.getVehicle());
        BuyerRentalReturnerDto buyerReturnerDto = userMapper.toRentalBuyerDTO(rental.getUser());

        return new RentalReturnerDTO(
                rental.getId(),
                rental.getStartDate(),
                rental.getEndDate(),
                rental.getDailyCost(),
                rental.getTotalCost(),
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