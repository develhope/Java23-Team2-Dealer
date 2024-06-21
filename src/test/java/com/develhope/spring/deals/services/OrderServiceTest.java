package com.develhope.spring.deals.services;

import com.develhope.spring.deals.components.OrderMapper;
import com.develhope.spring.deals.dtos.OrderCreatorDTO;
import com.develhope.spring.deals.dtos.OrderResponseDTO;
import com.develhope.spring.deals.dtos.OrderUpdatedDTO;
import com.develhope.spring.deals.models.Order;
import com.develhope.spring.deals.models.OrderStatus;
import com.develhope.spring.deals.repositories.OrderRepository;
import com.develhope.spring.deals.responseStatus.NotAvailableVehicleException;
import com.develhope.spring.deals.responseStatus.OrderNotFoundException;
import com.develhope.spring.users.components.UserMapper;
import com.develhope.spring.users.dtos.UserOrderReturnerDTO;
import com.develhope.spring.users.models.Roles;
import com.develhope.spring.users.models.User;
import com.develhope.spring.users.repositories.UserRepository;
import com.develhope.spring.vehicles.components.VehicleMapper;
import com.develhope.spring.vehicles.dtos.VehicleOrderReturnerDTO;
import com.develhope.spring.vehicles.models.Vehicle;
import com.develhope.spring.vehicles.repositories.VehicleRepository;
import com.develhope.spring.vehicles.responseStatus.NotAuthorizedOperationException;
import com.develhope.spring.vehicles.vehicleEnums.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.context.SecurityContextHolder;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
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

    @MockBean
    private SecurityContextHolder securityContextHolder;
    @Mock
    private VehicleMapper vehicleMapper;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private OrderMapper orderMapper;

    private final long DEFAULT_ID = 1;
    private final long DEFAULT_ADMIN_ID = 2;

    private final User DEFAULT_ADMIN = new User(
            2,
            "Admin",
            "Super",
            "Figo",
            "1234",
            123,
            "mail@admin.com",
            Roles.ADMIN
    );

    private static final UserOrderReturnerDTO DEFAULT_USER_ORDER_RETURNER_DTO = new UserOrderReturnerDTO(1);

    private final Vehicle DEFAULT_VEHICLE = new Vehicle(
            1,
            VehicleType.CAR,
            "Fiat",
            "Fiorino",
            2100,
            Colors.WHITE,
            3000,
            Gears.MANUAL,
            2004,
            MotorPowerSupply.GASOLINE,
            BigDecimal.valueOf(1000).setScale(2, RoundingMode.HALF_EVEN),
            UsedFlag.NEW,
            MarketStatus.AVAILABLE,
            "Motore"
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
    private final User DEFAULT_USER = new User(
            1,
            "Altro",
            "Tizio",
            "A caso",
            "1234",
            123,
            "mail@buyer.com",
            Roles.BUYER);

    private final Order DEFAULT_ORDER_ID = new Order();
    private final String ADMIN_USERNAME = "admin@example.com";
    private final String USER_USERNAME = "user@example.com";


    private final OrderCreatorDTO DEFAULT_ORDER_CREATOR_DTO = new OrderCreatorDTO(
            true,
            1,
            1,
            OrderStatus.PAID,
            true
    );
    private final Order DEFAULT_ORDER = new Order(
            1,
            true,
            OrderStatus.PAID,
            true,
            DEFAULT_VEHICLE,
            DEFAULT_USER
    );
    private final OrderResponseDTO DEFAULT_ORDER_RESPONSE_DTO = new OrderResponseDTO(
            1,
            true,
            DEFAULT_VEHICLE_ORDER_RETURNER_DTO,
            1,
            OrderStatus.PAID,
            true
    );

    @Test
    void createOrder() {
        OrderResponseDTO expected = new OrderResponseDTO(
                1,
                true,
                DEFAULT_VEHICLE_ORDER_RETURNER_DTO,
                1,
                OrderStatus.PAID,
                true
        );
        when(vehicleRepository.findById(DEFAULT_ORDER_CREATOR_DTO.getVehicleId()))
                .thenReturn(Optional.of(DEFAULT_VEHICLE));
        when(orderRepository.save(any()))
                .thenReturn(DEFAULT_ORDER);
        OrderResponseDTO result = orderService.create(DEFAULT_ORDER_CREATOR_DTO);
        assertEquals(expected.getUserId(), result.getUserId());
    }

    @Test
    void checkValidOperatorTest() {
        when(userRepository.findById(DEFAULT_ADMIN_ID))
                .thenReturn(Optional.of(DEFAULT_ADMIN));
        assertDoesNotThrow(() -> orderService.checkValidOperator(DEFAULT_ADMIN_ID));
    }

    @Test
    void checkValidOperatorTest_UserBuyer() {
        User buyer = new User(DEFAULT_ID);
        buyer.setRoles(Roles.BUYER);
        when(userRepository.findById(DEFAULT_ID))
                .thenReturn(Optional.of(buyer));
        assertThrows(NotAuthorizedOperationException.class, () -> orderService.checkValidOperator(DEFAULT_ID));
    }

    @Test
    void checkValidOperatorTest_UserRoleNull() {
User user = new User(2);
        when(userRepository.findById(2L))
                .thenReturn(Optional.of(user));
        assertThrows(NullPointerException.class, () -> orderService.checkValidOperator(2));
    }

    @Test
    void checkValidVehicleMarketStatusTest() {
        when(vehicleRepository.findById(DEFAULT_ID))
                .thenReturn(Optional.of(DEFAULT_VEHICLE));
        assertDoesNotThrow(() -> orderService.checkValidVehicleMarketStatus(DEFAULT_ORDER_CREATOR_DTO));
    }

    @Test
    void checkValidVehicleMarketStatusTest_VehicleIsNotAvailable() {
        Vehicle vehicle = new Vehicle();
        vehicle.setMarketStatus(MarketStatus.NOTAVAILABLE);
        when(vehicleRepository.findById(DEFAULT_ID))
                .thenReturn(Optional.of(vehicle));
        assertThrows(NotAvailableVehicleException.class, () -> orderService.checkValidVehicleMarketStatus(DEFAULT_ORDER_CREATOR_DTO));
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
                DEFAULT_USER
        );
        when(orderRepository.save(any()))
                .thenReturn(updatedRental);
        OrderUpdatedDTO expected = new OrderUpdatedDTO(
                1L,
                true,
                OrderStatus.PAID,
                true
        );
        OrderUpdatedDTO result = orderService.update(DEFAULT_ID, DEFAULT_ORDER_CREATOR_DTO);
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
                DEFAULT_USER
        );
        when(orderRepository.save(any()))
                .thenReturn(updatedRental);
        OrderUpdatedDTO expected = new OrderUpdatedDTO(
                1L,
                true,
                OrderStatus.PAID,
                true
        );
        OrderUpdatedDTO result = orderService.update(DEFAULT_ID, DEFAULT_ORDER_CREATOR_DTO);
        assertEquals(expected.getId(), result.getId());
    }


    @Test
    void deleteOrderByIdAndUserId_OrderNotFound() {
        when(orderRepository.findByIdAndUserId(DEFAULT_ORDER_ID.getId(), DEFAULT_USER.getId()))
                .thenReturn(Optional.empty());
        Exception exception = assertThrows(OrderNotFoundException.class, () -> orderService.deleteOrderByIdAndUserId(DEFAULT_ORDER_ID.getId(), DEFAULT_USER));
        assertEquals("Order Not Found!", exception.getMessage());
        verify(orderRepository, times(1)).findByIdAndUserId(DEFAULT_ORDER_ID.getId(), DEFAULT_USER.getId());
        verify(orderRepository, never()).deleteById(anyLong());
    }

    @Test
    void deleteOrderByIdAndUserId_OrderFound() {
        when(orderRepository.findByIdAndUserId(DEFAULT_ORDER_ID.getId(), DEFAULT_USER.getId()))
                .thenReturn(Optional.of(DEFAULT_ORDER));
        assertDoesNotThrow(() -> orderService.deleteOrderByIdAndUserId(DEFAULT_ORDER_ID.getId(), DEFAULT_USER));
        verify(orderRepository, times(1)).findByIdAndUserId(DEFAULT_ORDER_ID.getId(), DEFAULT_USER.getId());
        verify(orderRepository, times(1)).deleteById(DEFAULT_ORDER_ID.getId());
    }

    @Test
    void deleteByTest_OrderFound() {
        Order dummyOrder = new Order(DEFAULT_ORDER_ID.getId());
        when(orderRepository.findById(DEFAULT_ORDER_ID.getId()))
                .thenReturn(java.util.Optional.of(dummyOrder));
        assertDoesNotThrow(() -> orderService.deleteBy(DEFAULT_ORDER_ID.getId()));
        verify(orderRepository, times(1)).deleteById(DEFAULT_ORDER_ID.getId());
    }

    @Test
    void createOrder_successfulTest_userId() {
        OrderResponseDTO expected = new OrderResponseDTO(
                1,
                true,
                DEFAULT_VEHICLE_ORDER_RETURNER_DTO,
                1,
                OrderStatus.PAID,
                true
        );

        when(vehicleMapper.toOrderReturnerDTO(any(Vehicle.class)))
                .thenReturn(DEFAULT_VEHICLE_ORDER_RETURNER_DTO);
        when(userMapper.toUserOrderReturnerDTO(any(User.class)))
                .thenReturn(DEFAULT_USER_ORDER_RETURNER_DTO);
        when(orderRepository.save(any()))
                .thenReturn(DEFAULT_ORDER);

        OrderResponseDTO result = orderMapper.toResponseDTO(DEFAULT_ORDER);

        assertEquals(expected.getUserId(), result.getUserId());
    }

    @Test
    void createOrder_successfulTest_vehicleId() {
        OrderResponseDTO expected = new OrderResponseDTO(
                1,
                true,
                DEFAULT_VEHICLE_ORDER_RETURNER_DTO,
                1,
                OrderStatus.PAID,
                true
        );

        when(vehicleMapper.toOrderReturnerDTO(any(Vehicle.class)))
                .thenReturn(DEFAULT_VEHICLE_ORDER_RETURNER_DTO);
        when(userMapper.toUserOrderReturnerDTO(any(User.class)))
                .thenReturn(DEFAULT_USER_ORDER_RETURNER_DTO);

        OrderResponseDTO result = orderMapper.toResponseDTO(DEFAULT_ORDER);

        assertEquals(expected.getVehicle().getId(), result.getVehicle().getId());
    }

    @Test
    void createOrder_successfulTest_downPayment() {
        OrderResponseDTO expected = new OrderResponseDTO(
                1,
                true,
                DEFAULT_VEHICLE_ORDER_RETURNER_DTO,
                1,
                OrderStatus.PAID,
                true
        );

        when(vehicleMapper.toOrderReturnerDTO(any(Vehicle.class)))
                .thenReturn(DEFAULT_VEHICLE_ORDER_RETURNER_DTO);
        when(userMapper.toUserOrderReturnerDTO(any(User.class)))
                .thenReturn(DEFAULT_USER_ORDER_RETURNER_DTO);

        OrderResponseDTO result = orderMapper.toResponseDTO(DEFAULT_ORDER);

        assertEquals(expected.isDownPayment(), result.isDownPayment());
    }

    @Test
    void createOrder_successfulTest_orderStatus() {
        OrderResponseDTO expected = new OrderResponseDTO(
                1,
                true,
                DEFAULT_VEHICLE_ORDER_RETURNER_DTO,
                1,
                OrderStatus.PAID,
                true
        );

        when(vehicleMapper.toOrderReturnerDTO(any(Vehicle.class)))
                .thenReturn(DEFAULT_VEHICLE_ORDER_RETURNER_DTO);
        when(userMapper.toUserOrderReturnerDTO(any(User.class)))
                .thenReturn(DEFAULT_USER_ORDER_RETURNER_DTO);

        OrderResponseDTO result = orderMapper.toResponseDTO(DEFAULT_ORDER);

        assertEquals(expected.getOrderStatus(), result.getOrderStatus());
    }

    @Test
    void createOrder_successfulTest_paid() {
        OrderResponseDTO expected = new OrderResponseDTO(
                1,
                true,
                DEFAULT_VEHICLE_ORDER_RETURNER_DTO,
                1,
                OrderStatus.PAID,
                true
        );

        when(vehicleMapper.toOrderReturnerDTO(any(Vehicle.class)))
                .thenReturn(DEFAULT_VEHICLE_ORDER_RETURNER_DTO);
        when(userMapper.toUserOrderReturnerDTO(any(User.class)))
                .thenReturn(DEFAULT_USER_ORDER_RETURNER_DTO);

        OrderResponseDTO result = orderMapper.toResponseDTO(DEFAULT_ORDER);

        assertEquals(expected.isPaid(), result.isPaid());
    }
}