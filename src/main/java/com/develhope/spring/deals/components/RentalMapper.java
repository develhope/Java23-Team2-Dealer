package com.develhope.spring.deals.components;

import com.develhope.spring.deals.dtos.RentalCreatorDTO;
import com.develhope.spring.deals.dtos.RentalReturnerDTO;
import com.develhope.spring.deals.dtos.RentalUpdaterDTO;
import com.develhope.spring.deals.models.Rental;
import com.develhope.spring.users.components.UserMapper;
import com.develhope.spring.users.dtos.UserRentalReturnerDto;
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
        User seller = new User(rentalCreatorDTO.getSellerId());
        return new Rental(
                rentalCreatorDTO.getStartDate(),
                rentalCreatorDTO.getEndDate(),
                rentalCreatorDTO.getDailyCost(),
                rentalCreatorDTO.getTotalCost(),
                rentalCreatorDTO.isPaid(),
                vehicle,
                0,
                user,
                seller
        );
    }

    public Rental toEntity(RentalUpdaterDTO rentalUpdaterDTO) {
        Vehicle vehicle = new Vehicle(rentalUpdaterDTO.getVehicleId());
        User user = new User();
        User seller = new User();
        return new Rental(
                rentalUpdaterDTO.getStartDate(),
                rentalUpdaterDTO.getEndDate(),
                rentalUpdaterDTO.getDailyCost(),
                rentalUpdaterDTO.getTotalCost(),
                rentalUpdaterDTO.isPaid(),
                vehicle,
                0,
                user,
                seller);
    }

    public RentalReturnerDTO toReturnerDTO(Rental rental) {

        VehicleRentalReturnerDTO vehicleRentalReturnerDTO = vehicleMapper.toRentalReturnerDTO(rental.getVehicle());
        UserRentalReturnerDto buyer = userMapper.toUserBuyerDTO(rental.getUser());
        UserRentalReturnerDto seller = userMapper.toUserBuyerDTO(rental.getSeller());

        return new RentalReturnerDTO(
                rental.getId(),
                rental.getStartDate(),
                rental.getEndDate(),
                rental.getDailyCost(),
                rental.isPaid(),
                vehicleRentalReturnerDTO,
                buyer,
                seller
        );
    }

    public Rental toEntity(RentalReturnerDTO rentalReturnerDTO) {
        Vehicle vehicle = vehicleMapper.toEntity(rentalReturnerDTO.getVehicle());
        User user = userMapper.toEntity(rentalReturnerDTO.getBuyer());
        User seller = userMapper.toEntity(rentalReturnerDTO.getSeller());
        return new Rental(rentalReturnerDTO.getStartDate(), rentalReturnerDTO.getEndDate(), rentalReturnerDTO.getDailyCost(),
                rentalReturnerDTO.getTotalCost(), rentalReturnerDTO.isPaid(), vehicle, 0, user, seller);
    }
}