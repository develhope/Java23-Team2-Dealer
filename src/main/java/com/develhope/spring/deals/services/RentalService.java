package com.develhope.spring.deals.services;

import com.develhope.spring.deals.dtos.RentalCreatorDTO;
import com.develhope.spring.deals.models.Rental;
import com.develhope.spring.deals.models.RentalMapper;
import com.develhope.spring.deals.repositories.RentalRepository;
import com.develhope.spring.deals.responseStatus.NotAvailableVehicleException;
import com.develhope.spring.users.repositories.UserRepository;
import com.develhope.spring.vehicles.repositories.VehicleRepository;
import com.develhope.spring.vehicles.responseStatus.VehicleNotFoundException;
import com.develhope.spring.vehicles.vehicleEnums.MarketStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RentalService {

    private RentalRepository rentalRepository;

    private VehicleRepository vehicleRepository;

    private UserRepository userRepository;

    private RentalMapper rentalMapper;

    public RentalService(RentalRepository rentalRepository, VehicleRepository vehicleRepository, UserRepository userRepository, RentalMapper rentalMapper) {
        this.rentalRepository = rentalRepository;
        this.vehicleRepository = vehicleRepository;
        this.userRepository = userRepository;
        this.rentalMapper = rentalMapper;
    }

    public RentalCreatorDTO create(RentalCreatorDTO rentalCreatorDTO) {
//        checkExistingVehicle(rentalCreatorDTO);
//        checkMarketStatus(rentalCreatorDTO);
        Rental rental = rentalMapper.toEntity(rentalCreatorDTO);
        return rentalMapper.toDTO(rentalRepository.save(rental));
    }

    private void checkExistingVehicle(RentalCreatorDTO rentalCreatorDTO) {
        if (vehicleRepository.findById(rentalCreatorDTO.getVehicleId()).isEmpty()) {
            throw new VehicleNotFoundException("Not existing vehicle");
        }
    }

    private void checkMarketStatus(RentalCreatorDTO rentalCreatorDTO) {
        if (vehicleRepository.findById(rentalCreatorDTO.getVehicleId()).get().getMarketStatus() == MarketStatus.NOTAVAILABLE) {
            throw new NotAvailableVehicleException("Vehicle is no more available");
        }
    }
}
