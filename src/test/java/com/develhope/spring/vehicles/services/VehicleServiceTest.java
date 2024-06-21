package com.develhope.spring.vehicles.services;


import com.develhope.spring.deals.models.Order;
import com.develhope.spring.deals.models.Rental;
import com.develhope.spring.users.dtos.UserRegistrationDTO;
import com.develhope.spring.users.models.Roles;
import com.develhope.spring.users.models.User;
import com.develhope.spring.users.repositories.UserRepository;
import com.develhope.spring.vehicles.dtos.VehicleCreatorDTO;
import com.develhope.spring.vehicles.dtos.VehicleSavedDTO;
import com.develhope.spring.vehicles.models.Vehicle;
import com.develhope.spring.vehicles.repositories.VehicleRepository;
import com.develhope.spring.vehicles.vehicleEnums.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class VehicleServiceTest {

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Autowired
    private VehicleService vehicleService;

    @MockBean
    private VehicleRepository vehicleRepository;

    @MockBean
    private UserRepository userRepository;

    private final List<User> DEFAULT_SELLERS_LIST = new ArrayList<>();
    private final User DEFAULT_USER = new User(1);
    private final Rental DEFAULT_RENTAL = new Rental(1);
    private final Order DEFAULT_ORDER = new Order(1);
    private final UserRegistrationDTO DEFAULT_USER_USER_CREATOR_DTO = new UserRegistrationDTO(
            "Gabriel",
            "Dello",
            "paneNutella",
            "12345",
            "12345",
            3467789L,
            "hey@itsadmin.com",
            Roles.ADMIN
    );

    private final VehicleCreatorDTO DEFAULT_VEHICLE_CREATOR_DTO = new VehicleCreatorDTO(
            VehicleType.CAR,
            "Ferrari",
            "Enzo",
            2100,
            Colors.RED,
            3000,
            Gears.MANUAL,
            2004,
            MotorPowerSupply.GASOLINE,
            BigDecimal.valueOf(800000).setScale(2, RoundingMode.HALF_EVEN),
            UsedFlag.USED,
            MarketStatus.AVAILABLE,
            "V8"
    );

    private final Vehicle DEFAULT_VEHICLE = new Vehicle(
            1,
            VehicleType.CAR,
            "Ferrari",
            "Enzo",
            2100,
            Colors.RED,
            3000,
            Gears.MANUAL,
            2004,
            MotorPowerSupply.GASOLINE,
            BigDecimal.valueOf(800000).setScale(2, RoundingMode.HALF_EVEN),
            UsedFlag.USED,
            MarketStatus.AVAILABLE,
            "V8"
    );


    @Test
    void createVehicle_successfulCreationTest() {
        Vehicle vehicle = new Vehicle();
        User admin = new User(1L, "Gabriel", "Dello", "panenutella", "1234",
                3467789, "hey@itsadmin.com", Roles.ADMIN, DEFAULT_RENTAL, DEFAULT_ORDER);
        when(userRepository.findById(admin.getId()))
                .thenReturn(Optional.of(admin));
        when(vehicleRepository.save(any()))
                .thenReturn(DEFAULT_VEHICLE);
        VehicleSavedDTO result = vehicleService.create(DEFAULT_VEHICLE_CREATOR_DTO);
        VehicleSavedDTO expected = new VehicleSavedDTO(
                DEFAULT_VEHICLE.getId(),
                DEFAULT_VEHICLE.getVehicleType(),
                DEFAULT_VEHICLE.getBrand(),
                DEFAULT_VEHICLE.getModel(),
                DEFAULT_VEHICLE.getColor(),
                DEFAULT_VEHICLE.getPrice(),
                DEFAULT_VEHICLE.getUsedFlag(),
                DEFAULT_VEHICLE.getMarketStatus()
        );
        assertEquals(expected.getBrand(), result.getBrand());
    }
}
