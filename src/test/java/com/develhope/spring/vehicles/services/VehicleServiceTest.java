package com.develhope.spring.vehicles.services;


import com.develhope.spring.users.dtos.UserRegistrationDTO;
import com.develhope.spring.users.models.Roles;
import com.develhope.spring.users.models.User;
import com.develhope.spring.users.repositories.UserRepository;
import com.develhope.spring.vehicles.dtos.VehicleCreatorDTO;
import com.develhope.spring.vehicles.dtos.VehicleResponseDTO;
import com.develhope.spring.vehicles.dtos.VehicleSavedDTO;
import com.develhope.spring.vehicles.models.Vehicle;
import com.develhope.spring.vehicles.repositories.VehicleRepository;
import com.develhope.spring.vehicles.vehicleEnums.*;
import com.develhope.spring.vehicles.responseStatus.NotAuthorizedOperationException;
import com.develhope.spring.vehicles.vehicleEnums.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.NoSuchElementException;
import java.math.BigDecimal;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.develhope.spring.configurations.VehicleUnitTestConfiguration.*;
import static org.junit.jupiter.api.Assertions.*;
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

    @MockBean
    private Pattern pattern;

    private static final VehicleCreatorDTO DEFAULT_VEHICLE_CREATOR_DTO = new VehicleCreatorDTO(
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
            BigDecimal.valueOf(40).setScale(2, RoundingMode.HALF_EVEN),
            UsedFlag.USED,
            MarketStatus.AVAILABLE,
            "V8"
    );

    private static Vehicle DEFAULT_VEHICLE () {
        Vehicle vehicle = new Vehicle(1);
        vehicle.setVehicleType(DEFAULT_VEHICLE_CREATOR_DTO.getVehicleType());
        vehicle.setBrand(DEFAULT_VEHICLE_CREATOR_DTO.getBrand());
        vehicle.setModel(DEFAULT_VEHICLE_CREATOR_DTO.getModel());
        vehicle.setDisplacement(DEFAULT_VEHICLE_CREATOR_DTO.getDisplacement());
        vehicle.setColor(DEFAULT_VEHICLE_CREATOR_DTO.getColor());
        vehicle.setPower(DEFAULT_VEHICLE_CREATOR_DTO.getPower());
        vehicle.setGear(DEFAULT_VEHICLE_CREATOR_DTO.getGear());
        vehicle.setRegistrationYear(DEFAULT_VEHICLE_CREATOR_DTO.getRegistrationYear());
        vehicle.setPowerSupply(DEFAULT_VEHICLE_CREATOR_DTO.getPowerSupply());
        vehicle.setPrice(DEFAULT_VEHICLE_CREATOR_DTO.getPrice());
        vehicle.setUsedFlag(DEFAULT_VEHICLE_CREATOR_DTO.getUsedFlag());
        vehicle.setMarketStatus(DEFAULT_VEHICLE_CREATOR_DTO.getMarketStatus());
        vehicle.setEngine(DEFAULT_VEHICLE_CREATOR_DTO.getEngine());
        return vehicle;
    }
    @MockBean
    private Matcher matcher;

    @Test
    void createVehicle_successfulCreationTest() {
        Vehicle vehicle = new Vehicle();
        User admin = new User(1L, "Gabriel", "Dello", "panenutella", "1234", 3467789, "hey@itsadmin.com", Roles.ADMIN);
        when(userRepository.findById(admin.getId()))
                .thenReturn(Optional.of(admin));
        when(vehicleRepository.save(any()))
                .thenReturn(DEFAULT_VEHICLE());
        VehicleSavedDTO result = vehicleService.create(DEFAULT_VEHICLE_CREATOR_DTO);
        VehicleSavedDTO expected = new VehicleSavedDTO(
                DEFAULT_VEHICLE().getId(),
                DEFAULT_VEHICLE().getVehicleType(),
                DEFAULT_VEHICLE().getBrand(),
                DEFAULT_VEHICLE().getModel(),
                DEFAULT_VEHICLE().getColor(),
                DEFAULT_VEHICLE().getPrice(),
                DEFAULT_VEHICLE().getDailyCost(),
                DEFAULT_VEHICLE().getUsedFlag(),
                DEFAULT_VEHICLE().getMarketStatus()
        );
        assertEquals(expected.getBrand(), result.getBrand());
    }

//    @Test
//    void search_returnCorrectVehicleListLength_whenASingleFilterEqualIsPassed() {
//        List<Vehicle> expected = new ArrayList<>();
//        expected.add(DEFAULT_VEHICLE());
//        Pattern pattern = Pattern.compile("(\\p{Punct}?)(\\w+?)(:|<|>)(\\p{Punct}?)(\\w+?)(\\p{Punct}?),");
//        VehicleFilterDTO vehicleFilterDTO = new VehicleFilterDTO(null, "CAR", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
//        VehicleSpecificationsBuilder vehicleSpecificationsBuilder = new VehicleSpecificationsBuilder();
//        Matcher matcher = pattern.matcher(vehicleFilterDTO.DTOToString() + ",");
//        while (matcher.find()) {
//            vehicleSpecificationsBuilder.with(matcher.group(1), matcher.group(2), matcher.group(3), matcher.group(5), matcher.group(4), matcher.group(6));
//        }
//        Specification<Vehicle> spec = vehicleSpecificationsBuilder.build();
//        when(vehicleRepository.findAll(spec))
//                .thenReturn(expected);
//        List<Vehicle> result = vehicleService.search(vehicleFilterDTO);
//        assertEquals(expected.size(), result.size());
//    }
}
