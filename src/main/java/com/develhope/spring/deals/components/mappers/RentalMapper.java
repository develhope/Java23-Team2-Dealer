package com.develhope.spring.deals.components.mappers;

import com.develhope.spring.deals.dtos.rentalsDtos.RentalCreatorDTO;
import com.develhope.spring.deals.dtos.rentalsDtos.RentalReturnerDTO;
import com.develhope.spring.deals.dtos.rentalsDtos.RentalUpdaterDTO;
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
        Rental rental = new Rental();
        rental.setStartDate(rentalCreatorDTO.getStartDate());
        rental.setEndDate(rentalCreatorDTO.getEndDate());
        rental.setPaid(rentalCreatorDTO.isPaid());
        rental.setVehicle(vehicle);
        rental.setUser(user);
        rental.setSeller(seller);
        return rental;
    }

    public Rental toEntity(RentalUpdaterDTO rentalUpdaterDTO) {
        Vehicle vehicle = new Vehicle(rentalUpdaterDTO.getVehicleId());
        User user = new User(0);
        User seller = new User(0);
        Rental rental = new Rental();
        rental.setStartDate(rentalUpdaterDTO.getStartDate());
        rental.setEndDate(rentalUpdaterDTO.getEndDate());
        rental.setPaid(rentalUpdaterDTO.isPaid());
        rental.setUser(user);
        rental.setSeller(seller);
        rental.setVehicle(vehicle);
        return rental;
    }

    public RentalReturnerDTO toReturnerDTO(Rental rental) {
        VehicleRentalReturnerDTO vehicleRentalReturnerDTO = vehicleMapper.toRentalReturnerDTO(rental.getVehicle());
        UserRentalReturnerDto buyerReturnerDto = userMapper.toUserRentalDTO(rental.getUser());
        UserRentalReturnerDto sellerReturnerDTO = userMapper.toUserRentalDTO(rental.getSeller());
        RentalReturnerDTO rentalReturnerDTO = new RentalReturnerDTO();
        rentalReturnerDTO.setId(rental.getId());
        rentalReturnerDTO.setStartDate(rental.getStartDate());
        rentalReturnerDTO.setEndDate(rental.getEndDate());
        rentalReturnerDTO.setTotalCost(rental.getTotalCost());
        rentalReturnerDTO.setPaid(rental.isPaid());
        rentalReturnerDTO.setVehicle(vehicleRentalReturnerDTO);
        rentalReturnerDTO.setBuyer(buyerReturnerDto);
        rentalReturnerDTO.setSeller(sellerReturnerDTO);
        return rentalReturnerDTO;
    }

    public Rental toEntity(RentalReturnerDTO rentalReturnerDTO) {
        Vehicle vehicle = vehicleMapper.toEntity(rentalReturnerDTO.getVehicle());
        User user = userMapper.toEntity(rentalReturnerDTO.getBuyer());
        User seller = userMapper.toEntity(rentalReturnerDTO.getSeller());
        return new Rental(rentalReturnerDTO.getStartDate(), rentalReturnerDTO.getEndDate(), rentalReturnerDTO.getDailyCost(), rentalReturnerDTO.isPaid(), vehicle, 0, user, seller);
    }
}