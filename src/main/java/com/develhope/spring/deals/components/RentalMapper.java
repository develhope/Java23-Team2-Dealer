package com.develhope.spring.deals.components;

import com.develhope.spring.deals.dtos.RentalCreatorDTO;
import com.develhope.spring.deals.dtos.RentalReturnerDTO;
import com.develhope.spring.deals.dtos.RentalUpdaterDTO;
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
        Rental rental = new Rental();
        rental.setStartDate(rentalCreatorDTO.getStartDate());
        rental.setEndDate(rentalCreatorDTO.getEndDate());
        rental.setPaid(rentalCreatorDTO.isPaid());
        rental.setVehicle(vehicle);
        rental.setUser(user);
        return rental;
    }

    public Rental toEntity(RentalUpdaterDTO rentalUpdaterDTO) {
        Vehicle vehicle = new Vehicle(rentalUpdaterDTO.getVehicleId());
        User user = new User(0);
        Rental rental = new Rental();
        rental.setStartDate(rentalUpdaterDTO.getStartDate());
        rental.setEndDate(rentalUpdaterDTO.getEndDate());
        rental.setPaid(rentalUpdaterDTO.isPaid());
        rental.setUser(user);
        rental.setVehicle(vehicle);
        return rental;
    }

    public RentalReturnerDTO toReturnerDTO(Rental rental) {
        VehicleRentalReturnerDTO vehicleRentalReturnerDTO = vehicleMapper.toRentalReturnerDTO(rental.getVehicle());
        BuyerRentalReturnerDto buyerReturnerDto = userMapper.toRentalBuyerDTO(rental.getUser());
        RentalReturnerDTO rentalReturnerDTO = new RentalReturnerDTO();
        rentalReturnerDTO.setId(rental.getId());
        rentalReturnerDTO.setStartDate(rental.getStartDate());
        rentalReturnerDTO.setEndDate(rental.getEndDate());
        rentalReturnerDTO.setTotalCost(rental.getTotalCost());
        rentalReturnerDTO.setPaid(rental.isPaid());
        rentalReturnerDTO.setVehicle(vehicleRentalReturnerDTO);
        rentalReturnerDTO.setBuyer(buyerReturnerDto);
        return rentalReturnerDTO;
    }

    public Rental toEntity(RentalReturnerDTO rentalReturnerDTO) {
        Vehicle vehicle = vehicleMapper.toEntity(rentalReturnerDTO.getVehicle());
        User user = userMapper.toEntity(rentalReturnerDTO.getBuyer());
        return new Rental(rentalReturnerDTO.getStartDate(), rentalReturnerDTO.getEndDate(), rentalReturnerDTO.getDailyCost(), rentalReturnerDTO.isPaid(), vehicle, 0, user);
    }
}