package com.develhope.spring.vehicles.services;


import com.develhope.spring.users.models.Roles;
import com.develhope.spring.users.models.User;
import com.develhope.spring.users.repositories.UserRepository;
import com.develhope.spring.vehicles.dtos.VehicleSavedDTO;
import com.develhope.spring.vehicles.models.Vehicle;
import com.develhope.spring.vehicles.repositories.VehicleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

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
