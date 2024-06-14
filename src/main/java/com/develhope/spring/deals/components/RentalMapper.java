package com.develhope.spring.deals.components;

import com.develhope.spring.deals.dtos.RentalCreatorDTO;
import com.develhope.spring.deals.dtos.RentalGetterDTO;
import com.develhope.spring.deals.dtos.RentalResponseDTO;
import com.develhope.spring.deals.dtos.RentalUpdaterDTO;
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

    //ToDTO();
    public RentalResponseDTO toResponseDTO(Rental rental) {

        VehicleRentalReturnerDTO vehicleRentalResponseDTO = vehicleMapper.toRentalReturnerDTO(rental.getVehicle());
        BuyerRentalReturnerDto buyerResponseDto = userMapper.toRentalBuyerDTO(rental.getUser());

        return new RentalResponseDTO(
                rental.getId(),
                rental.getStartDate(),
                rental.getEndDate(),
                rental.getDailyCost(),
                rental.isPaid(),
                vehicleRentalResponseDTO,
                buyerResponseDto
        );
    }

    public RentalGetterDTO toGetterDTO(Rental rental){

        VehicleRentalReturnerDTO vehicleRentalResponseDTO = vehicleMapper.toRentalReturnerDTO(rental.getVehicle());
        BuyerRentalReturnerDto buyerResponseDto = userMapper.toRentalBuyerDTO(rental.getUser());

        return new RentalGetterDTO(
                rental.getId(),
                rental.getStartDate(),
                rental.getEndDate(),
                rental.getDailyCost(),
                rental.isPaid(),
                vehicleRentalResponseDTO,
                buyerResponseDto,
                rental.getSellers()
        );
    }

    //ToEntity();
    public Rental toEntity(RentalCreatorDTO rentalCreatorDTO) {
        Vehicle vehicle = new Vehicle(rentalCreatorDTO.getVehicleId());
        User user = new User(rentalCreatorDTO.getUserId());
        Rental rental = new Rental();
        rental.setStartDate(rentalCreatorDTO.getStartDate());
        rental.setEndDate(rentalCreatorDTO.getEndDate());
        rental.setDailyCost(rentalCreatorDTO.getDailyCost());
        rental.setTotalCost(rentalCreatorDTO.getTotalCost());
        rental.setPaid(rentalCreatorDTO.isPaid());
        rental.setVehicle(vehicle);
        rental.setUser(user);
        rental.setSellers(rentalCreatorDTO.getSellers());

        return rental;
    }

    public Rental toEntity(RentalUpdaterDTO rentalUpdaterDTO) {
        Vehicle vehicle = new Vehicle(rentalUpdaterDTO.getVehicleId());
        User user = new User(0);
        Rental rental = new Rental();
        rental.setStartDate(rentalUpdaterDTO.getStartDate());
        rental.setEndDate(rentalUpdaterDTO.getEndDate());
        rental.setDailyCost(rentalUpdaterDTO.getDailyCost());
        rental.setTotalCost(rentalUpdaterDTO.getTotalCost());
        rental.setPaid(rentalUpdaterDTO.isPaid());
        rental.setVehicle(vehicle);
        rental.setUser(user);
        return rental;
    }
}