package com.develhope.spring.deals.components;

import com.develhope.spring.deals.components.mappers.OrderMapper;
import com.develhope.spring.deals.dtos.ordersDtos.OrderCreatorDTO;
import com.develhope.spring.deals.dtos.ordersDtos.OrderResponseDTO;
import com.develhope.spring.deals.dtos.ordersDtos.OrderCreatorDTO;
import com.develhope.spring.deals.dtos.ordersDtos.OrderResponseDTO;
import com.develhope.spring.deals.models.Order;
import com.develhope.spring.deals.models.OrderStatus;
import com.develhope.spring.users.components.UserMapper;
import com.develhope.spring.users.dtos.UserOrderReturnerDTO;
import com.develhope.spring.vehicles.dtos.VehicleOrderReturnerDTO;
import com.develhope.spring.vehicles.components.VehicleMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderMapperTest {

    @Mock
    private VehicleMapper vehicleMapper;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private OrderMapper orderMapper;


    @Test
    void toEntity_SetsDownPaymentCorrectly() {
        OrderCreatorDTO orderCreatorDTO = new OrderCreatorDTO();
        orderCreatorDTO.setDownPayment(true);
        Order result = orderMapper.toEntity(orderCreatorDTO);
        assertTrue(result.isDownPayment());
    }

    @Test
    void toEntity_SetsOrderStatusCorrectly() {
        OrderCreatorDTO orderCreatorDTO = new OrderCreatorDTO();
        orderCreatorDTO.setOrderStatus(OrderStatus.PENDING);

        Order result = orderMapper.toEntity(orderCreatorDTO);

        assertEquals(OrderStatus.PENDING, result.getOrderStatus());
    }

    @Test
    void toEntity_SetsPaidCorrectly() {
        OrderCreatorDTO orderCreatorDTO = new OrderCreatorDTO();
        orderCreatorDTO.setPaid(false);
        Order result = orderMapper.toEntity(orderCreatorDTO);

        assertFalse(result.isPaid());
    }

    @Test
    void toResponseDTO_MapsOrderToDTO_notNull() {
        Order order = new Order();
        order.setDownPayment(true);
        order.setOrderStatus(OrderStatus.PENDING);
        order.setPaid(false);

        VehicleOrderReturnerDTO vehicleDTO = new VehicleOrderReturnerDTO();
        when(vehicleMapper.toOrderReturnerDTO(any())).thenReturn(vehicleDTO);

        UserOrderReturnerDTO userDTO = new UserOrderReturnerDTO();
        when(userMapper.toUserOrderReturnerDTO(any())).thenReturn(userDTO);

        OrderResponseDTO result = orderMapper.toResponseDTO(order);

        assertNotNull(result);
    }

    @Test
    void toResponseDTO_MapsOrderToDTO_downPayment() {
        Order order = new Order();
        order.setDownPayment(true);
        order.setOrderStatus(OrderStatus.PENDING);
        order.setPaid(false);

        VehicleOrderReturnerDTO vehicleDTO = new VehicleOrderReturnerDTO();
        when(vehicleMapper.toOrderReturnerDTO(any())).thenReturn(vehicleDTO);

        UserOrderReturnerDTO userDTO = new UserOrderReturnerDTO();
        when(userMapper.toUserOrderReturnerDTO(any())).thenReturn(userDTO);

        OrderResponseDTO result = orderMapper.toResponseDTO(order);

        assertEquals(true, result.isDownPayment());
    }

    @Test
    void toResponseDTO_MapsOrderToDTO_orderStatus() {
        Order order = new Order();
        order.setDownPayment(true);
        order.setOrderStatus(OrderStatus.PENDING);
        order.setPaid(false);

        VehicleOrderReturnerDTO vehicleDTO = new VehicleOrderReturnerDTO();
        when(vehicleMapper.toOrderReturnerDTO(any())).thenReturn(vehicleDTO);

        UserOrderReturnerDTO userDTO = new UserOrderReturnerDTO();
        when(userMapper.toUserOrderReturnerDTO(any())).thenReturn(userDTO);

        OrderResponseDTO result = orderMapper.toResponseDTO(order);

        assertEquals(OrderStatus.PENDING, result.getOrderStatus());
    }

    @Test
    void toResponseDTO_MapsOrderToDTO_paid() {
        Order order = new Order();
        order.setDownPayment(true);
        order.setOrderStatus(OrderStatus.PENDING);
        order.setPaid(false);

        VehicleOrderReturnerDTO vehicleDTO = new VehicleOrderReturnerDTO();
        when(vehicleMapper.toOrderReturnerDTO(any())).thenReturn(vehicleDTO);

        UserOrderReturnerDTO userDTO = new UserOrderReturnerDTO();
        when(userMapper.toUserOrderReturnerDTO(any())).thenReturn(userDTO);

        OrderResponseDTO result = orderMapper.toResponseDTO(order);

        assertEquals(false, result.isPaid());
    }

    @Test
    void toResponseDTO_SetsUserIdCorrectly() {
        Order order = new Order();
        UserOrderReturnerDTO userDTO = new UserOrderReturnerDTO();
        when(userMapper.toUserOrderReturnerDTO(any())).thenReturn(userDTO);

        OrderResponseDTO result = orderMapper.toResponseDTO(order);

        assertEquals(userDTO.getId(), result.getUserId());
    }
}

