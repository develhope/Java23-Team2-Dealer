package com.develhope.spring.deals.services;

import com.develhope.spring.deals.dtos.RentalCreatorDTO;
import com.develhope.spring.deals.dtos.RentalReturnerDTO;
import com.develhope.spring.deals.dtos.RentalUpdaterDTO;
import com.develhope.spring.deals.models.Rental;
import com.develhope.spring.deals.repositories.RentalRepository;
import com.develhope.spring.deals.responseStatus.NotAvailableVehicleException;
import com.develhope.spring.deals.responseStatus.RentalOverlappingDatesException;
import com.develhope.spring.users.dtos.BuyerRentalReturnerDto;
import com.develhope.spring.users.models.Roles;
import com.develhope.spring.users.models.User;
import com.develhope.spring.users.repositories.UserRepository;
import com.develhope.spring.vehicles.dtos.VehicleRentalReturnerDTO;
import com.develhope.spring.vehicles.models.Vehicle;
import com.develhope.spring.vehicles.repositories.VehicleRepository;
import com.develhope.spring.vehicles.vehicleEnums.MarketStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class RentalServiceTest {

//    @BeforeEach
//    public void setup() {
//        MockitoAnnotations.openMocks(this);
//    }

    @MockBean
    private VehicleRepository vehicleRepository;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private RentalRepository rentalRepository;

    @Autowired
    private RentalService rentalService;

    private final LocalDate DEFAULT_RENTAL_START_DATE = LocalDate.of(2024, 6, 3);
    private final LocalDate DEFAULT_RENTAL_END_DATE = LocalDate.of(2024, 6, 6);
    private final BigDecimal DEFAULT_PRICE = BigDecimal.valueOf(40).setScale(2, RoundingMode.HALF_EVEN);

    private final RentalCreatorDTO DEFAULT_RENTAL_CREATOR_DTO = new RentalCreatorDTO(
            DEFAULT_RENTAL_START_DATE,
            DEFAULT_RENTAL_END_DATE,
            DEFAULT_PRICE,
            true,
            1,
            1);

    private final Vehicle DEFAULT_VEHICLE = new Vehicle(1);
    private final User DEFAULT_USER = new User(2);

    private final Rental DEFAULT_RENTAL = new Rental();

    private final VehicleRentalReturnerDTO DEFAULT_VEHICLE_RENTAL_RETURNER_DTO = new VehicleRentalReturnerDTO(1);
    private final BuyerRentalReturnerDto DEFAULT_BUYER_RENTAL_RETURNER_DTO = new BuyerRentalReturnerDto(2);
    private final Rental DEFAULT_EXISTING_RENTAL = new Rental(
            LocalDate.of(2024, 6, 7),
            LocalDate.of(2024, 6, 10),
            DEFAULT_PRICE,
            DEFAULT_RENTAL_CREATOR_DTO.getTotalCost(),
            true,
            DEFAULT_VEHICLE,
            1,
            DEFAULT_USER
    );

    private final Rental DEFAULT_EXISTING_RENTAL2 = new Rental(
            LocalDate.of(2024, 6, 11),
            LocalDate.of(2024, 6, 14),
            DEFAULT_PRICE,
            DEFAULT_RENTAL_CREATOR_DTO.getTotalCost(),
            true,
            DEFAULT_VEHICLE,
            2,
            DEFAULT_USER
    );

    private final Collection<Rental> DEFAULT_EXISTING_RENTALS = new ArrayList<>(List.of(DEFAULT_EXISTING_RENTAL, DEFAULT_EXISTING_RENTAL2));

    @Test
    void createRental_successfulCreationTest() {

        RentalReturnerDTO expected = new RentalReturnerDTO(
                1,
                DEFAULT_RENTAL_START_DATE,
                DEFAULT_RENTAL_END_DATE,
                DEFAULT_PRICE,
                true,
                DEFAULT_VEHICLE_RENTAL_RETURNER_DTO,
                DEFAULT_BUYER_RENTAL_RETURNER_DTO
        );

        Rental rental = new Rental(
                DEFAULT_RENTAL_START_DATE,
                DEFAULT_RENTAL_END_DATE,
                DEFAULT_PRICE,
                DEFAULT_RENTAL_CREATOR_DTO.getTotalCost(),
                true,
                DEFAULT_VEHICLE,
                1,
                DEFAULT_USER
        );

        when(rentalRepository.findByVehicleId(DEFAULT_RENTAL_CREATOR_DTO.getVehicleId()))
                .thenReturn(DEFAULT_EXISTING_RENTALS);
        when(vehicleRepository.findById(DEFAULT_RENTAL_CREATOR_DTO.getVehicleId()))
                .thenReturn(Optional.of(DEFAULT_VEHICLE));
        when(userRepository.findById(DEFAULT_RENTAL_CREATOR_DTO.getUserId()))
                .thenReturn(Optional.of(DEFAULT_USER));
        when(rentalRepository.save(any()))
                .thenReturn(rental);

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
                LocalDate.of(2024, 6, 5),
                LocalDate.of(2024, 6, 8),
                DEFAULT_PRICE,
                DEFAULT_RENTAL_CREATOR_DTO.getTotalCost(),
                true,
                DEFAULT_VEHICLE,
                4,
                DEFAULT_USER
        );

        Rental existingRental2 = new Rental(
                LocalDate.of(2024, 5, 31),
                LocalDate.of(2024, 6, 2),
                DEFAULT_PRICE,
                DEFAULT_RENTAL_CREATOR_DTO.getTotalCost(),
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

    @Test
    void createRental_returnIDTest() {

        RentalReturnerDTO expected = new RentalReturnerDTO(
                1,
                DEFAULT_RENTAL_START_DATE,
                DEFAULT_RENTAL_END_DATE,
                DEFAULT_PRICE,
                true,
                DEFAULT_VEHICLE_RENTAL_RETURNER_DTO,
                DEFAULT_BUYER_RENTAL_RETURNER_DTO
        );

        Rental rental = new Rental(
                DEFAULT_RENTAL_START_DATE,
                DEFAULT_RENTAL_END_DATE,
                DEFAULT_PRICE,
                DEFAULT_RENTAL_CREATOR_DTO.getTotalCost(),
                true,
                DEFAULT_VEHICLE,
                1,
                DEFAULT_USER
        );

        when(rentalRepository.findByVehicleId(DEFAULT_RENTAL_CREATOR_DTO.getVehicleId()))
                .thenReturn(DEFAULT_EXISTING_RENTALS);
        when(vehicleRepository.findById(DEFAULT_RENTAL_CREATOR_DTO.getVehicleId()))
                .thenReturn(Optional.of(DEFAULT_VEHICLE));
        when(userRepository.findById(DEFAULT_RENTAL_CREATOR_DTO.getUserId()))
                .thenReturn(Optional.of(DEFAULT_USER));
        when(rentalRepository.save(any()))
                .thenReturn(rental);

        RentalReturnerDTO result = rentalService.create(DEFAULT_RENTAL_CREATOR_DTO);
        assertEquals(expected.getId(), result.getId());
    }

    @Test
    void updateRental_successfulUpdateTest() {
        User admin = new User(
                1,
                "Gabriel",
                "Dello",
                346777,
                "hey@itsadmin.it",
                Roles.ADMIN
        );

        when(userRepository.findById(1L))
                .thenReturn(Optional.of(admin));

        RentalUpdaterDTO rentalUpdaterDTO = new RentalUpdaterDTO(
                LocalDate.of(2024, 6, 15),
                LocalDate.of(2024, 6, 18),
                DEFAULT_PRICE,
                false,
                1);

        when(rentalRepository.findByVehicleId(rentalUpdaterDTO.getVehicleId()))
                .thenReturn(DEFAULT_EXISTING_RENTALS);
        when(vehicleRepository.findById(rentalUpdaterDTO.getVehicleId()))
                .thenReturn(Optional.of(DEFAULT_VEHICLE));
        when(rentalRepository.findById(1L))
                .thenReturn(Optional.of(DEFAULT_EXISTING_RENTAL));
        Rental updatedRental = new Rental(rentalUpdaterDTO.getStartDate(), rentalUpdaterDTO.getEndDate(),
                rentalUpdaterDTO.getDailyCost(), rentalUpdaterDTO.getTotalCost(), rentalUpdaterDTO.isPaid(),
                DEFAULT_VEHICLE, DEFAULT_EXISTING_RENTAL.getId(), DEFAULT_EXISTING_RENTAL.getUser());
        when(rentalRepository.save(any()))
                .thenReturn(updatedRental);
        RentalReturnerDTO result = rentalService.update(1, 1, rentalUpdaterDTO);
        RentalReturnerDTO expected = new RentalReturnerDTO(updatedRental.getId(), updatedRental.getStartDate(),
                updatedRental.getEndDate(), updatedRental.getDailyCost(), updatedRental.isPaid(),
                DEFAULT_VEHICLE_RENTAL_RETURNER_DTO, DEFAULT_BUYER_RENTAL_RETURNER_DTO);
        assertEquals(expected.getTotalCost(), result.getTotalCost());
    }

    @Test
    void updateRental_returnIDTest() {
        User admin = new User(
                1,
                "Gabriel",
                "Dello",
                346777,
                "hey@itsadmin.it",
                Roles.ADMIN
        );

        when(userRepository.findById(1L))
                .thenReturn(Optional.of(admin));

        RentalUpdaterDTO rentalUpdaterDTO = new RentalUpdaterDTO(
                LocalDate.of(2024, 6, 15),
                LocalDate.of(2024, 6, 18),
                DEFAULT_PRICE,
                false,
                1);

        when(rentalRepository.findByVehicleId(rentalUpdaterDTO.getVehicleId()))
                .thenReturn(DEFAULT_EXISTING_RENTALS);
        when(vehicleRepository.findById(rentalUpdaterDTO.getVehicleId()))
                .thenReturn(Optional.of(DEFAULT_VEHICLE));
        when(rentalRepository.findById(1L))
                .thenReturn(Optional.of(DEFAULT_EXISTING_RENTAL));
        Rental updatedRental = new Rental(rentalUpdaterDTO.getStartDate(), rentalUpdaterDTO.getEndDate(),
                rentalUpdaterDTO.getDailyCost(), rentalUpdaterDTO.getTotalCost(), rentalUpdaterDTO.isPaid(),
                DEFAULT_VEHICLE, DEFAULT_EXISTING_RENTAL.getId(), DEFAULT_EXISTING_RENTAL.getUser());
        when(rentalRepository.save(any()))
                .thenReturn(updatedRental);
        RentalReturnerDTO result = rentalService.update(1, 1, rentalUpdaterDTO);
        RentalReturnerDTO expected = new RentalReturnerDTO(updatedRental.getId(), updatedRental.getStartDate(),
                updatedRental.getEndDate(), updatedRental.getDailyCost(), updatedRental.isPaid(),
                DEFAULT_VEHICLE_RENTAL_RETURNER_DTO, DEFAULT_BUYER_RENTAL_RETURNER_DTO);
        assertEquals(expected.getId(), result.getId());
    }
}
