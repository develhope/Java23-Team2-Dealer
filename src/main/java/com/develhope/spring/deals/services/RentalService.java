package com.develhope.spring.deals.services;

import com.develhope.spring.deals.dtos.RentalCreatorDTO;
import com.develhope.spring.deals.dtos.RentalReturnerDTO;
import com.develhope.spring.deals.models.Rental;
import com.develhope.spring.deals.components.RentalMapper;
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

    @Autowired
    private RentalRepository rentalRepository;
    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RentalMapper rentalMapper;

    public RentalReturnerDTO create(RentalCreatorDTO rentalCreatorDTO) {
//        checkExistingVehicle(rentalCreatorDTO);
//        checkMarketStatus(rentalCreatorDTO);
        Rental rental = rentalMapper.toEntityFrom(rentalCreatorDTO);
        return rentalMapper.toReturnerDTOFrom(rentalRepository.save(rental));

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
