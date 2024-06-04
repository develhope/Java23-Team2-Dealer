package com.develhope.spring.deals.services;

import com.develhope.spring.deals.dtos.RentalCreatorDTO;
import com.develhope.spring.deals.dtos.RentalReturnerDTO;
import com.develhope.spring.deals.models.Rental;
import com.develhope.spring.deals.components.RentalMapper;
import com.develhope.spring.deals.repositories.RentalRepository;
import com.develhope.spring.deals.responseStatus.NotAvailableVehicleException;
import com.develhope.spring.deals.responseStatus.RentalOverlappingDatesException;
import com.develhope.spring.users.repositories.UserRepository;
import com.develhope.spring.vehicles.repositories.VehicleRepository;
import com.develhope.spring.vehicles.responseStatus.VehicleNotFoundException;
import com.develhope.spring.vehicles.vehicleEnums.MarketStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.lang.Math.min;

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
        checkValidRentalDates(rentalCreatorDTO);
        checkMarketStatus(rentalCreatorDTO);
        Rental rental = rentalMapper.toEntityFrom(rentalCreatorDTO);
        rentalRepository.save(rental);
        return rentalMapper.toReturnerDTOFrom(rental);

    }

    private static void checkValidArgument(RentalCreatorDTO rentalCreatorDTO) {
        if (rentalCreatorDTO.getStartDate().isAfter(rentalCreatorDTO.getEndDate())) {
            throw new IllegalArgumentException(
                    "Starting Date of the rental can't be later than ending date. Please select new dates"
            );
        }
    }

    private void checkValidRentalDates(RentalCreatorDTO rentalCreatorDTO) {
        checkValidArgument(rentalCreatorDTO);
        Rental optionalRental = rentalRepository.findByVehicleId(rentalCreatorDTO.getVehicleId());
        if ((Math.min(
                optionalRental.getEndDate().toEpochDay(),
                rentalCreatorDTO.getEndDate().toEpochDay() -
                        Math.max(optionalRental.getStartDate().toEpochDay(),
                                rentalCreatorDTO.getStartDate().toEpochDay()))) >= 0
        ) {
            throw new RentalOverlappingDatesException(
                    "Selected vehicle is already rented during this periods. Please select new dates"
            );
        }
    }

    private void checkMarketStatus(RentalCreatorDTO rentalCreatorDTO) {
        if (vehicleRepository.findById(rentalCreatorDTO.getVehicleId()).isPresent() &&
                vehicleRepository.findById(
                                rentalCreatorDTO.getVehicleId()).get()
                        .getMarketStatus() == MarketStatus.NOTAVAILABLE
        ) {
            throw new NotAvailableVehicleException("Vehicle is no more available");
        }
    }
}
