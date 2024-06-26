package com.develhope.spring.deals.services;

import com.develhope.spring.deals.dtos.rentalsDtos.RentalCreatorDTO;
import com.develhope.spring.deals.dtos.rentalsDtos.RentalReturnerDTO;
import com.develhope.spring.deals.dtos.rentalsDtos.RentalUpdaterDTO;
import com.develhope.spring.deals.models.Rental;
import com.develhope.spring.deals.components.mappers.RentalMapper;
import com.develhope.spring.deals.repositories.RentalRepository;
import com.develhope.spring.deals.responseStatus.NotAvailableVehicleException;
import com.develhope.spring.deals.responseStatus.RentalOverlappingDatesException;
import com.develhope.spring.users.models.User;
import com.develhope.spring.users.repositories.UserRepository;
import com.develhope.spring.vehicles.models.Vehicle;
import com.develhope.spring.vehicles.repositories.VehicleRepository;
import com.develhope.spring.vehicles.vehicleEnums.MarketStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;
import java.util.NoSuchElementException;

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
        BigDecimal vehicleDailyCost = getDailyCost(rentalCreatorDTO.getVehicleId());
        checkValidRentalDates(rentalCreatorDTO.getVehicleId(), rentalCreatorDTO.getStartDate(), rentalCreatorDTO.getEndDate());
        checkMarketStatus(rentalCreatorDTO.getVehicleId());
        Rental rental = rentalMapper.toEntity(rentalCreatorDTO);
        setRentalTotalCost(rental, vehicleDailyCost, rentalCreatorDTO.getStartDate(), rentalCreatorDTO.getEndDate());
        Rental savedRental = rentalRepository.save(rental);
        RentalReturnerDTO rentalReturnerDTO = rentalMapper.toReturnerDTO(savedRental);
        rentalReturnerDTO.setDailyCost(vehicleDailyCost);
        return rentalReturnerDTO;
    }

    public RentalReturnerDTO update(long rentalId, RentalUpdaterDTO rentalUpdaterDTO) {
        BigDecimal vehicleDailyCost = getDailyCost(rentalUpdaterDTO.getVehicleId());
        checkValidRentalDates(rentalUpdaterDTO.getVehicleId(), rentalUpdaterDTO.getStartDate(), rentalUpdaterDTO.getEndDate());
        checkMarketStatus(rentalUpdaterDTO.getVehicleId());
        Rental savedRental = rentalRepository.findById(rentalId).orElseThrow(NoSuchElementException::new);
        Vehicle vehicle = getVehicle(rentalUpdaterDTO.getVehicleId());
        savedRental.setStartDate(rentalUpdaterDTO.getStartDate());
        savedRental.setEndDate(rentalUpdaterDTO.getEndDate());
        savedRental.setPaid(rentalUpdaterDTO.isPaid());
        savedRental.setVehicle(vehicle);
        setRentalTotalCost(savedRental, vehicleDailyCost, rentalUpdaterDTO.getStartDate(), rentalUpdaterDTO.getEndDate());
        Rental updatedRental = rentalRepository.save(savedRental);
        RentalReturnerDTO rentalReturnerDTO = rentalMapper.toReturnerDTO(updatedRental);
        rentalReturnerDTO.setDailyCost(vehicleDailyCost);
        return rentalReturnerDTO;
    }

    private Vehicle getVehicle(long vehicleId) {
        return vehicleRepository.findById(vehicleId).orElseThrow(NoSuchElementException::new);
    }

    public Page<RentalReturnerDTO> getByUserId(User userDetails, int page, int size) {
        if (!userRepository.existsById(userDetails.getId())) {
            throw new NoSuchElementException("User not registered");
        }
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Rental> foundRentals = rentalRepository.findByUserId(userDetails.getId(), pageable);
        return foundRentals.map(rentalMapper::toReturnerDTO);
    }

    private BigDecimal getDailyCost(long vehicleId) {
        return vehicleRepository.findById(vehicleId).orElseThrow(NoSuchElementException::new).getDailyCost();
    }

    private void setRentalTotalCost(Rental rental, BigDecimal vehicleDailyCost, LocalDate startDate, LocalDate endDate) {
        rental.setTotalCost(calculateTotalCost(vehicleDailyCost, startDate, endDate));
    }

    private void checkValidRentalDates(long vehicleId, LocalDate startDate, LocalDate endDate) {
        checkValidArgument(startDate, endDate);
        Collection<Rental> rentals = rentalRepository.findByVehicleId(vehicleId);
        if (rentals.isEmpty()) {
            return;
        }
        for (Rental rental : rentals) {
            long endDate1 = rental.getEndDate().toEpochDay();
            long endDate2 = endDate.toEpochDay();
            long startDate1 = rental.getStartDate().toEpochDay();
            long startDate2 = startDate.toEpochDay();
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

    private void checkMarketStatus(long vehicleId) {
        Vehicle vehicle = getVehicle(vehicleId);
        if (vehicle.getMarketStatus() == MarketStatus.NOTAVAILABLE) {
            throw new NotAvailableVehicleException("Vehicle is no more available");
        }
    }

    private static void checkValidArgument(LocalDate startDate, LocalDate endDate) {
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException(
                    "Starting Date of the rental can't be later than ending date. Please select new dates"
            );
        }
    }

    private BigDecimal calculateTotalCost(BigDecimal dailyCost, LocalDate startDate, LocalDate endDate) {
        long rentalDays = startDate.until(endDate).getDays();
        return dailyCost.multiply(BigDecimal.valueOf(rentalDays));
    }
}