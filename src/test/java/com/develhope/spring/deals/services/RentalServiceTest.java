package com.develhope.spring.deals.services;

import com.develhope.spring.deals.dtos.RentalCreatorDTO;
import com.develhope.spring.deals.dtos.RentalReturnerDTO;
import com.develhope.spring.deals.models.Rental;
import com.develhope.spring.deals.repositories.RentalRepository;
import com.develhope.spring.deals.responseStatus.NotAvailableVehicleException;
import com.develhope.spring.deals.responseStatus.RentalOverlappingDatesException;
import com.develhope.spring.users.dtos.BuyerRentalReturnerDto;
import com.develhope.spring.users.models.User;
import com.develhope.spring.users.repositories.UserRepository;
import com.develhope.spring.vehicles.dtos.VehicleRentalReturnerDTO;
import com.develhope.spring.vehicles.models.Vehicle;
import com.develhope.spring.vehicles.repositories.VehicleRepository;
import com.develhope.spring.vehicles.vehicleEnums.MarketStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class RentalServiceTest {

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @MockBean
    private VehicleRepository vehicleRepository;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private RentalRepository rentalRepository;

    @Autowired
    private RentalService rentalService;

    private static final LocalDate DEFAULT_RENTAL_START_DATE = LocalDate.of(2024, 6, 3);
    private static final LocalDate DEFAULT_RENTAL_END_DATE = LocalDate.of(2024, 6, 5);
    private static final BigDecimal DEFAULT_PRICE = BigDecimal.valueOf(40).setScale(2, RoundingMode.HALF_EVEN);

    private static final RentalCreatorDTO DEFAULT_RENTAL_CREATOR_DTO = new RentalCreatorDTO(
            DEFAULT_RENTAL_START_DATE,
            DEFAULT_RENTAL_END_DATE,
            DEFAULT_PRICE,
            true,
            1,
            1);

    private static final Vehicle DEFAULT_VEHICLE = new Vehicle(1);
    private static final User DEFAULT_USER = new User(1);

    private static final VehicleRentalReturnerDTO DEFAULT_VEHICLE_RENTAL_RETURNER_DTO = new VehicleRentalReturnerDTO(1);
    private static final BuyerRentalReturnerDto DEFAULT_BUYER_RENTAL_RETURNER_DTO = new BuyerRentalReturnerDto(1);
    private static final Rental DEFAULT_EXISTING_RENTAL = new Rental(
            LocalDate.of(2024, 6, 6),
            LocalDate.of(2024, 6, 8),
            DEFAULT_PRICE,
            true,
            DEFAULT_VEHICLE,
            4,
            DEFAULT_USER
    );

    private static final Rental DEFAULT_EXISTING_RENTAL2 = new Rental(
            LocalDate.of(2024, 6, 10),
            LocalDate.of(2024, 6, 15),
            DEFAULT_PRICE,
            true,
            DEFAULT_VEHICLE,
            4,
            DEFAULT_USER
    );

    private static final Collection<Rental> DEFAULT_EXISTING_RENTALS = new ArrayList<>(List.of(DEFAULT_EXISTING_RENTAL, DEFAULT_EXISTING_RENTAL2));

    @Test
    void createRental_successfulTest() {

        RentalReturnerDTO expected = new RentalReturnerDTO(
                DEFAULT_RENTAL_START_DATE,
                DEFAULT_RENTAL_END_DATE,
                DEFAULT_PRICE,
                true,
                DEFAULT_VEHICLE_RENTAL_RETURNER_DTO,
                DEFAULT_BUYER_RENTAL_RETURNER_DTO
        );

        when(rentalRepository.findByVehicleId(DEFAULT_RENTAL_CREATOR_DTO.getVehicleId()))
                .thenReturn(DEFAULT_EXISTING_RENTALS);
        when(vehicleRepository.findById(DEFAULT_RENTAL_CREATOR_DTO.getVehicleId()))
                .thenReturn(Optional.of(DEFAULT_VEHICLE));
        when(userRepository.findById(DEFAULT_RENTAL_CREATOR_DTO.getUserId()))
                .thenReturn(Optional.of(DEFAULT_USER));

        RentalReturnerDTO result = rentalService.create(DEFAULT_RENTAL_CREATOR_DTO);
        assertEquals(expected.getBuyer().getId(), result.getBuyer().getId());
    }

    @Test
    void createRental_vehicleNotFoundIsThrown() {

        when(rentalRepository.findByVehicleId(DEFAULT_RENTAL_CREATOR_DTO.getVehicleId()))
                .thenReturn(DEFAULT_EXISTING_RENTALS);
        when(vehicleRepository.findById(DEFAULT_RENTAL_CREATOR_DTO.getVehicleId())).thenThrow(NoSuchElementException.class);

        assertThrows(NoSuchElementException.class, () -> rentalService.create(DEFAULT_RENTAL_CREATOR_DTO));

    }

    @Test
    void createRental_chosenVehicleIsNotAvailableTest() {
        Vehicle vehicle = new Vehicle(1);
        vehicle.setMarketStatus(MarketStatus.NOTAVAILABLE);
        when(rentalRepository.findByVehicleId(DEFAULT_RENTAL_CREATOR_DTO.getVehicleId()))
                .thenReturn(DEFAULT_EXISTING_RENTALS);
        when(vehicleRepository.findById(DEFAULT_RENTAL_CREATOR_DTO.getVehicleId())).thenReturn(Optional.of(vehicle));

        assertThrows(NotAvailableVehicleException.class, () -> rentalService.create(DEFAULT_RENTAL_CREATOR_DTO));
    }

    @Test
    void createRental_overlappingDatesWithExistingRentalTest() {
        Rental existingRental = new Rental(
                LocalDate.of(2024, 6, 4),
                LocalDate.of(2024, 6, 8),
                DEFAULT_PRICE,
                true,
                DEFAULT_VEHICLE,
                4,
                DEFAULT_USER
        );

        Rental existingRental2 = new Rental(
                LocalDate.of(2024, 6, 1),
                LocalDate.of(2024, 6, 2),
                DEFAULT_PRICE,
                true,
                DEFAULT_VEHICLE,
                4,
                DEFAULT_USER
        );

        Collection<Rental> rentals = new ArrayList<>(List.of(existingRental, existingRental2));

        when(rentalRepository.findByVehicleId(DEFAULT_RENTAL_CREATOR_DTO.getVehicleId()))
                .thenReturn(rentals);

        assertThrows(RentalOverlappingDatesException.class, () -> rentalService.create(DEFAULT_RENTAL_CREATOR_DTO));
    }
}
