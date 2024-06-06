package com.develhope.spring.vehicles.services;


import com.develhope.spring.users.dtos.UserCreatorDTO;
import com.develhope.spring.users.models.Roles;
import com.develhope.spring.users.models.User;
import com.develhope.spring.users.repositories.UserRepository;
import com.develhope.spring.vehicles.dtos.VehicleCreatorDTO;
import com.develhope.spring.vehicles.models.Vehicle;
import com.develhope.spring.vehicles.repositories.VehicleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
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

    private static final Vehicle DEFAULT_VEHICLE = new Vehicle();
    private static final User DEFAULT_USER = new User(1);
    private static final UserCreatorDTO DEFAULT_USER_USER_CREATOR_DTO = new UserCreatorDTO(
            "Gabriel",
            "Dello",
            3467789L,
            "hey@itsadmin.com",
            Roles.ADMIN
    );

    private static VehicleCreatorDTO DEFAULT_VEHICLE_CREATOR_DTO() {
        VehicleCreatorDTO vehicleCreatorDTO = new VehicleCreatorDTO();
        vehicleCreatorDTO.setBrand("Gigi");
        return vehicleCreatorDTO;
    }

//    @Test
//    void createVehicle_successfulTest() {
//        User admin = new User(1, "Gabriel", "Dello", 3467789, "hey@itsadmin.com", Roles.ADMIN);
//        VehicleCreatorDTO vehicleCreatorDTO = DEFAULT_VEHICLE_CREATOR_DTO();
//        Vehicle expected = new Vehicle(1);
//        expected.setBrand("Gigi");
//        when(userRepository.findById(DEFAULT_USER.getId()))
//                .thenReturn(Optional.of(admin));
//        when(vehicleRepository.save(DEFAULT_VEHICLE))
//                .thenReturn(expected);
//        VehicleCreatorDTO result = vehicleService.create(1L, vehicleCreatorDTO);
//        assertEquals(expected.getBrand(), result.getBrand());
//    }
}
