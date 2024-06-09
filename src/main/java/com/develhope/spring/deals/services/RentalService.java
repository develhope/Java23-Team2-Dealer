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
import com.develhope.spring.vehicles.vehicleEnums.MarketStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collection;

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

    public Page<Rental> getByUserId(long userId, int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(page, size, sort);
        return rentalRepository.findByUserId(userId,pageable);
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
}
