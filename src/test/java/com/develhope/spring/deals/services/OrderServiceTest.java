package com.develhope.spring.deals.services;

import com.develhope.spring.deals.components.OrderMapper;
import com.develhope.spring.deals.dtos.OrderCreatorDTO;
import com.develhope.spring.deals.dtos.OrderResponseDTO;
import com.develhope.spring.deals.models.Order;
import com.develhope.spring.deals.models.OrderStatus;
import com.develhope.spring.deals.repositories.OrderRepository;
import com.develhope.spring.deals.responseStatus.OrderNotFoundException;
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
<<<<<<< HEAD
import java.util.NoSuchElementException;
=======
>>>>>>> 74f1e08457cd1eebc3a63a811009dd75e596466f
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

    @MockBean
    private OrderMapper orderMapper;

    @Autowired
    private OrderService orderService;

    private static final OrderCreatorDTO DEFAULT_ORDER_CREATOR_DTO = new OrderCreatorDTO(
            true,
            1L,
            1L,
            OrderStatus.PAID,
            true
    );

    private static final Vehicle DEFAULT_VEHICLE = new Vehicle(1L);
    private static final User DEFAULT_USER = new User(1L);

    private static final VehicleOrderReturnerDTO DEFAULT_VEHICLE_ORDER_RETURNER_DTO = new VehicleOrderReturnerDTO(
            1L,
            VehicleType.CAR,
            "Fiat",
            "Fiorino",
            Colors.WHITE,
<<<<<<< HEAD
            BigDecimal.valueOf(1000).setScale(1, RoundingMode.HALF_UP),
=======
            BigDecimal.valueOf(1000).setScale(2, RoundingMode.HALF_EVEN),
>>>>>>> 74f1e08457cd1eebc3a63a811009dd75e596466f
            UsedFlag.NEW,
            "Motore"
    );

    private static final Order DEFAULT_ORDER = new Order(
            1L, 1L, true, OrderStatus.PAID, true, DEFAULT_VEHICLE, DEFAULT_USER
    );

    private static final OrderResponseDTO DEFAULT_ORDER_RESPONSE_DTO = new OrderResponseDTO(
            true,
            DEFAULT_VEHICLE_ORDER_RETURNER_DTO,
            1L,
            OrderStatus.PAID,
            true
    );

    @Test
    void createOrder_successfulTest() {
<<<<<<< HEAD
=======
        OrderResponseDTO expected = new OrderResponseDTO(
                1,
                true,
                DEFAULT_VEHICLE_ORDER_RETURNER_DTO,
                1,
                OrderStatus.PAID,
                true
        );

>>>>>>> 74f1e08457cd1eebc3a63a811009dd75e596466f
        when(vehicleRepository.findById(DEFAULT_ORDER_CREATOR_DTO.getVehicleId()))
                .thenReturn(Optional.of(DEFAULT_VEHICLE));
        when(userRepository.findById(DEFAULT_ORDER_CREATOR_DTO.getUserId()))
                .thenReturn(Optional.of(DEFAULT_USER));
        when(orderMapper.toEntity(DEFAULT_ORDER_CREATOR_DTO))
                .thenReturn(DEFAULT_ORDER);
        when(orderRepository.save(DEFAULT_ORDER))
                .thenReturn(DEFAULT_ORDER);
        when(orderMapper.toResponseDTO(DEFAULT_ORDER))
                .thenReturn(DEFAULT_ORDER_RESPONSE_DTO);

        OrderResponseDTO result = orderService.create(DEFAULT_ORDER_CREATOR_DTO);

        assertNotNull(result);
        assertEquals(DEFAULT_ORDER_RESPONSE_DTO.getUserId(), result.getUserId());
        assertEquals(DEFAULT_ORDER_RESPONSE_DTO.getVehicle().getId(), result.getVehicle().getId());
        assertEquals(DEFAULT_ORDER_RESPONSE_DTO.getOrderStatus(), result.getOrderStatus());
        assertEquals(DEFAULT_ORDER_RESPONSE_DTO.isDownPayment(), result.isDownPayment());
        assertEquals(DEFAULT_ORDER_RESPONSE_DTO.isPaid(), result.isPaid());
    }

    @Test
    void deleteOrder_successfulTest() {
        when(vehicleRepository.findById(DEFAULT_ORDER_CREATOR_DTO.getVehicleId()))
                .thenReturn(Optional.of(DEFAULT_VEHICLE));
        when(userRepository.findById(DEFAULT_ORDER_CREATOR_DTO.getUserId()))
                .thenReturn(Optional.of(DEFAULT_USER));
        when(orderMapper.toEntity(DEFAULT_ORDER_CREATOR_DTO))
                .thenReturn(DEFAULT_ORDER);
        when(orderRepository.save(DEFAULT_ORDER))
                .thenReturn(DEFAULT_ORDER);
        when(orderMapper.toResponseDTO(DEFAULT_ORDER))
                .thenReturn(DEFAULT_ORDER_RESPONSE_DTO);

        OrderResponseDTO createdOrder = orderService.create(DEFAULT_ORDER_CREATOR_DTO);
        assertNotNull(createdOrder);

        doNothing().when(orderRepository).deleteById(DEFAULT_ORDER.getId());

        assertDoesNotThrow(() -> orderService.delete(DEFAULT_ORDER.getId()));

        verify(orderRepository, times(1)).deleteById(DEFAULT_ORDER.getId());
    }

    @Test
    void deleteOrder_orderNotFoundTest() {
        when(vehicleRepository.findById(DEFAULT_ORDER_CREATOR_DTO.getVehicleId()))
                .thenReturn(Optional.of(DEFAULT_VEHICLE));
        when(userRepository.findById(DEFAULT_ORDER_CREATOR_DTO.getUserId()))
                .thenReturn(Optional.of(DEFAULT_USER));
        when(orderMapper.toEntity(DEFAULT_ORDER_CREATOR_DTO))
                .thenReturn(DEFAULT_ORDER);
        when(orderRepository.save(DEFAULT_ORDER))
                .thenReturn(DEFAULT_ORDER);
        when(orderMapper.toResponseDTO(DEFAULT_ORDER))
                .thenReturn(DEFAULT_ORDER_RESPONSE_DTO);

        OrderResponseDTO createdOrder = orderService.create(DEFAULT_ORDER_CREATOR_DTO);
        assertNotNull(createdOrder);

        doThrow(new NoSuchElementException()).when(orderRepository).deleteById(99L);

        OrderNotFoundException exception = assertThrows(OrderNotFoundException.class, () -> {
            orderService.delete(99L);
        });

        assertEquals("Order with ID 99 not found", exception.getMessage());
    }
}