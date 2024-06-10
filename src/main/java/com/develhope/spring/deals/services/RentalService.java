package com.develhope.spring.deals.services;

import com.develhope.spring.deals.dtos.RentalCreatorDTO;
import com.develhope.spring.deals.dtos.RentalReturnerDTO;
import com.develhope.spring.deals.dtos.RentalUpdaterDTO;
import com.develhope.spring.deals.models.Rental;
import com.develhope.spring.deals.models.RentalMapper;
import com.develhope.spring.deals.repositories.RentalRepository;
import com.develhope.spring.deals.responseStatus.NotAvailableVehicleException;
import com.develhope.spring.deals.responseStatus.RentalOverlappingDatesException;
import com.develhope.spring.users.models.Roles;
import com.develhope.spring.users.models.User;
import com.develhope.spring.users.repositories.UserRepository;
import com.develhope.spring.vehicles.models.Vehicle;
import com.develhope.spring.vehicles.repositories.VehicleRepository;
import com.develhope.spring.vehicles.responseStatus.NotAuthorizedOperationException;
import com.develhope.spring.vehicles.vehicleEnums.MarketStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

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
        Rental rental = rentalMapper.toEntity(rentalCreatorDTO);
        Rental savedRental = rentalRepository.save(rental);
        return rentalMapper.toReturnerDTO(savedRental);
    }

    public RentalReturnerDTO update(long adminId, long rentalId, RentalUpdaterDTO rentalUpdaterDTO) {
        checkUserAuthorizationBy(adminId);
        checkValidRentalDates(rentalUpdaterDTO);
        checkMarketStatus(rentalUpdaterDTO);
        Rental savedRental = rentalRepository.findById(rentalId).orElseThrow();
        Vehicle vehicle = vehicleRepository.findById(rentalUpdaterDTO.getVehicleId()).orElseThrow();
        savedRental.setStartDate(rentalUpdaterDTO.getStartDate());
        savedRental.setEndDate(rentalUpdaterDTO.getEndDate());
        savedRental.setPaid(rentalUpdaterDTO.isPaid());
        savedRental.setVehicle(vehicle);
        savedRental.setTotalCost(rentalUpdaterDTO.getTotalCost());
        Rental updatedRental = rentalRepository.save(savedRental);
        return rentalMapper.toReturnerDTO(updatedRental);
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
        Collection<Rental> rentals = rentalRepository.findByVehicleId(rentalCreatorDTO.getVehicleId());
        if (rentals.isEmpty()) {
            return;
        }
        for (Rental rental : rentals) {
            long endDate1 = rental.getEndDate().toEpochDay();
            long endDate2 = rentalCreatorDTO.getEndDate().toEpochDay();
            long startDate1 = rental.getStartDate().toEpochDay();
            long startDate2 = rentalCreatorDTO.getStartDate().toEpochDay();
            if ((Math.min(
                    endDate1,
                    endDate2) -
                    Math.max(
                            startDate1,
                            startDate2)) >= 0
            ) {
                throw new RentalOverlappingDatesException(
                        "Selected vehicle is already rented during this periods. Please select new dates"
                );
            }
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

    private void checkValidRentalDates(RentalUpdaterDTO rentalUpdaterDTO) {
        checkValidArgument(rentalUpdaterDTO);
        Collection<Rental> rentals = rentalRepository.findByVehicleId(rentalUpdaterDTO.getVehicleId());
        if (rentals.isEmpty()) {
            return;
        }
        for (Rental rental : rentals) {
            long endDate1 = rental.getEndDate().toEpochDay();
            long endDate2 = rentalUpdaterDTO.getEndDate().toEpochDay();
            long startDate1 = rental.getStartDate().toEpochDay();
            long startDate2 = rentalUpdaterDTO.getStartDate().toEpochDay();
            if ((Math.min(
                    endDate1,
                    endDate2) -
                    Math.max(
                            startDate1,
                            startDate2)) >= 0
            ) {
                throw new RentalOverlappingDatesException(
                        "Selected vehicle is already rented during this periods. Please select new dates"
                );
            }
        }
    }

    private void checkMarketStatus(RentalUpdaterDTO rentalUpdaterDTO) {
        if (vehicleRepository.findById(rentalUpdaterDTO.getVehicleId()).isPresent() &&
                vehicleRepository.findById(
                                rentalUpdaterDTO.getVehicleId()).get()
                        .getMarketStatus() == MarketStatus.NOTAVAILABLE
        ) {
            throw new NotAvailableVehicleException("Vehicle is no more available");
        }
    }

    private static void checkValidArgument(RentalUpdaterDTO rentalUpdaterDTO) {
        if (rentalUpdaterDTO.getStartDate().isAfter(rentalUpdaterDTO.getEndDate())) {
            throw new IllegalArgumentException(
                    "Starting Date of the rental can't be later than ending date. Please select new dates"
            );
        }
    }

    private void checkUserAuthorizationBy(long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty() || !optionalUser.get().getRoles().equals(Roles.ADMIN)) {
            throw new NotAuthorizedOperationException("Permesso negato. Non autorizzato ad aggiornare i veicoli");
        }
    }
}
