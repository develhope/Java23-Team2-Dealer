package com.develhope.spring.deals.services;

import com.develhope.spring.deals.dtos.OrderCreatorDTO;
import com.develhope.spring.deals.dtos.OrderResponseDTO;
import com.develhope.spring.deals.models.OrderStatus;
import com.develhope.spring.deals.repositories.OrderRepository;
import com.develhope.spring.users.models.User;
import com.develhope.spring.users.repositories.UserRepository;
import com.develhope.spring.vehicles.dtos.VehicleOrderReturnerDTO;
import com.develhope.spring.vehicles.models.Vehicle;
import com.develhope.spring.vehicles.repositories.VehicleRepository;
import com.develhope.spring.vehicles.vehicleEnums.Colors;
import com.develhope.spring.vehicles.vehicleEnums.UsedFlag;
import com.develhope.spring.vehicles.vehicleEnums.VehicleType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class OrderServiceTest {

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @MockBean
    private VehicleRepository vehicleRepository;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private OrderRepository orderRepository;

    @Autowired
    private OrderService orderService;


    private static final OrderCreatorDTO DEFAULT_ORDER_CREATOR_DTO = new OrderCreatorDTO(
            true,
            1,
            1,
            OrderStatus.PAID,
            true
    );

    private static final Vehicle DEFAULT_VEHICLE = new Vehicle(1);
    private static final VehicleOrderReturnerDTO DEFAULT_VEHICLE_ORDER_RETURNER_DTO = new VehicleOrderReturnerDTO(
            1,
            VehicleType.CAR,
            "Fiat",
            "Fiorino",
            Colors.WHITE,
            BigDecimal.valueOf(1000).setScale(2, RoundingMode.HALF_EVEN),
            UsedFlag.NEW,
            "Motore"
    );
    private static final User DEFAULT_USER = new User(1);


//    @Test
//    void createOrder_successfulTest() {
//        OrderResponseDTO expected = new OrderResponseDTO(
//                1,
//                true,
//                DEFAULT_VEHICLE_ORDER_RETURNER_DTO,
//                1,
//                OrderStatus.PAID,
//                true
//        );
//
//        when(vehicleRepository.findById(DEFAULT_ORDER_CREATOR_DTO.getVehicleId()))
//                .thenReturn(Optional.of(DEFAULT_VEHICLE));
//        when(userRepository.findById(DEFAULT_ORDER_CREATOR_DTO.getUserId()))
//                .thenReturn(Optional.of(DEFAULT_USER));
//
//        OrderResponseDTO result = orderService.create(DEFAULT_ORDER_CREATOR_DTO);
//        assertEquals(expected.getUserId(), result.getUserId());
//    }
}
