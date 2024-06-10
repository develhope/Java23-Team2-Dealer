package com.develhope.spring.deals.models;

import com.develhope.spring.deals.dtos.OrderCreatorDTO;
import com.develhope.spring.deals.dtos.OrderResponseDTO;
import com.develhope.spring.deals.models.Order;
import com.develhope.spring.deals.models.OrderMapper;
import com.develhope.spring.deals.models.OrderStatus;
import com.develhope.spring.users.components.UserMapper;
import com.develhope.spring.users.dtos.UserOrderReturnerDTO;
import com.develhope.spring.users.models.User;
import com.develhope.spring.vehicles.dtos.VehicleOrderReturnerDTO;
import com.develhope.spring.vehicles.models.Vehicle;
import com.develhope.spring.vehicles.models.VehicleMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderMapperTest {

    @Mock
    private VehicleMapper vehicleMapper;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private OrderMapper orderMapper;

    private static final OrderCreatorDTO DEFAULT_ORDER_CREATOR_DTO = new OrderCreatorDTO();
    private static final Vehicle DEFAULT_VEHICLE = new Vehicle();
    private static final User DEFAULT_USER = new User(1);
    private static final Order DEFAULT_ORDER = new Order(1L);
    private static final VehicleOrderReturnerDTO DEFAULT_VEHICLE_ORDER_RETURNER_DTO = new VehicleOrderReturnerDTO();
    private static final UserOrderReturnerDTO DEFAULT_USER_ORDER_RETURNER_DTO = new UserOrderReturnerDTO();

    @BeforeEach
    public void setUp() {
        DEFAULT_ORDER_CREATOR_DTO.setVehicleId(1);
        DEFAULT_ORDER_CREATOR_DTO.setUserId(1);
        DEFAULT_ORDER_CREATOR_DTO.setDownPayment(true);
        DEFAULT_ORDER_CREATOR_DTO.setOrderStatus(OrderStatus.PAID);
        DEFAULT_ORDER_CREATOR_DTO.setPaid(true);

        DEFAULT_ORDER.setVehicle(DEFAULT_VEHICLE);
        DEFAULT_ORDER.setUser(DEFAULT_USER);
        DEFAULT_ORDER.setDownPayment(true);
        DEFAULT_ORDER.setOrderStatus(OrderStatus.PAID);
        DEFAULT_ORDER.setPaid(true);

        DEFAULT_VEHICLE_ORDER_RETURNER_DTO.setId(1);
        DEFAULT_USER_ORDER_RETURNER_DTO.setId(1);
    }

    @Test
    void createOrder_successfulTest() {
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

        assertEquals(expected.getUserId(), result.getUserId());
        assertEquals(expected.getVehicle().getId(), result.getVehicle().getId());
        assertEquals(expected.isDownPayment(), result.isDownPayment());
        assertEquals(expected.getOrderStatus(), result.getOrderStatus());
        assertEquals(expected.isPaid(), result.isPaid());
    }
}
