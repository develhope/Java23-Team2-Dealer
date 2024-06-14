package com.develhope.spring.deals.services;

import com.develhope.spring.deals.dtos.OrderCreatorDTO;
import com.develhope.spring.deals.dtos.OrderReworkedDTO;
import com.develhope.spring.deals.dtos.OrderUpdaterDTO;
import com.develhope.spring.deals.models.Order;
import com.develhope.spring.deals.models.OrderStatus;
import com.develhope.spring.deals.models.Rental;
import com.develhope.spring.deals.repositories.OrderRepository;
import com.develhope.spring.deals.responseStatus.NotAvailableVehicleException;
import com.develhope.spring.users.models.Roles;
import com.develhope.spring.users.models.User;
import com.develhope.spring.users.repositories.UserRepository;
import com.develhope.spring.vehicles.dtos.VehicleOrderReturnerDTO;
import com.develhope.spring.vehicles.models.Vehicle;
import com.develhope.spring.vehicles.repositories.VehicleRepository;
import com.develhope.spring.vehicles.responseStatus.NotAuthorizedOperationException;
import com.develhope.spring.vehicles.vehicleEnums.Colors;
import com.develhope.spring.vehicles.vehicleEnums.MarketStatus;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

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


    private final long DEFAULT_ID = 1;
    private final long DEFAULT_ADMIN_ID = 2;
    private final List<User> DEFAULT_SELLERS_LIST = new ArrayList<>();
    private final Order DEFAULT_ORDER = new Order(1);
    private final Rental DEFAULT_RENTAL = new Rental(1);
    private final Vehicle DEFAULT_VEHICLE = new Vehicle(1);

    private final User DEFAULT_ADMIN = new User(
            2,
            "",
            "",
            "",
            "1234",
            123,
            "",
            Roles.ADMIN,
            DEFAULT_RENTAL,
            DEFAULT_ORDER
    );

    private final OrderCreatorDTO DEFAULT_ORDER_CREATOR_DTO = new OrderCreatorDTO(
            true,
            1,
            1,
            OrderStatus.PAID,
            true,
            DEFAULT_SELLERS_LIST
    );

    private final OrderUpdaterDTO DEFAULT_ORDER_UPDATER_DTO = new OrderUpdaterDTO(
            false,
            OrderStatus.PENDING,
            false
    );

    private final VehicleOrderReturnerDTO DEFAULT_VEHICLE_ORDER_RETURNER_DTO = new VehicleOrderReturnerDTO(
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

    @Test
    void checkValidVehicleMarketStatusTest(){
        when(vehicleRepository.findById(DEFAULT_ID))
                .thenReturn(Optional.of(DEFAULT_VEHICLE));
        assertDoesNotThrow(()->orderService.checkValidVehicleMarketStatus(DEFAULT_ORDER_CREATOR_DTO));
    }

    @Test
    void checkValidVehicleMarketStatusTest_VehicleIsNotAvailable(){
        Vehicle vehicle = new Vehicle();
        vehicle.setMarketStatus(MarketStatus.NOTAVAILABLE);
        when(vehicleRepository.findById(DEFAULT_ID))
                .thenReturn(Optional.of(vehicle));
        assertThrows(NotAvailableVehicleException.class, ()->orderService.checkValidVehicleMarketStatus(DEFAULT_ORDER_CREATOR_DTO));
    }

    @Test
    void updateOrderTest() {
        when(orderRepository.findById(DEFAULT_ID))
                .thenReturn(Optional.of(DEFAULT_ORDER));
        when(vehicleRepository.findById(DEFAULT_ID))
                .thenReturn(Optional.of(DEFAULT_VEHICLE));
        Order updatedRental = new Order(
                DEFAULT_ORDER.getId(),
                DEFAULT_ORDER_CREATOR_DTO.isDownPayment(),
                DEFAULT_ORDER_CREATOR_DTO.getOrderStatus(),
                DEFAULT_ORDER_CREATOR_DTO.isPaid(),
                DEFAULT_VEHICLE,
                DEFAULT_USER,
                DEFAULT_SELLERS_LIST
                );
        when(orderRepository.save(any()))
                .thenReturn(updatedRental);
        OrderReworkedDTO expected = new OrderReworkedDTO(
                1L,
                true,
                OrderStatus.PAID,
                true
        );
        OrderReworkedDTO result = orderService.update(DEFAULT_ID,DEFAULT_ORDER_UPDATER_DTO);
        assertEquals(expected.isDownPayment(), result.isDownPayment());
    }

    @Test
    void updateOrderTest_checkIfIdIsUnchanged() {
        when(orderRepository.findById(DEFAULT_ID))
                .thenReturn(Optional.of(DEFAULT_ORDER));
        when(vehicleRepository.findById(DEFAULT_ID))
                .thenReturn(Optional.of(DEFAULT_VEHICLE));
        Order updatedRental = new Order(
                DEFAULT_ORDER.getId(),
                DEFAULT_ORDER_CREATOR_DTO.isDownPayment(),
                DEFAULT_ORDER_CREATOR_DTO.getOrderStatus(),
                DEFAULT_ORDER_CREATOR_DTO.isPaid(),
                DEFAULT_VEHICLE,
                DEFAULT_USER,
                DEFAULT_SELLERS_LIST
        );
        when(orderRepository.save(any()))
                .thenReturn(updatedRental);
        OrderReworkedDTO expected = new OrderReworkedDTO(
                1L,
                true,
                OrderStatus.PAID,
                true
        );
        OrderReworkedDTO result = orderService.update(DEFAULT_ID, DEFAULT_ORDER_UPDATER_DTO);
        assertEquals(expected.getId(), result.getId());
    }
}
