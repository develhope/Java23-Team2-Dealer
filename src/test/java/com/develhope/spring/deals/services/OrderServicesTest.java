package com.develhope.spring.deals.services;

import com.develhope.spring.deals.dtos.OrderCreatorDTO;
import com.develhope.spring.deals.models.Order;
import com.develhope.spring.deals.models.OrderMapper;
import com.develhope.spring.deals.models.exceptions.OrderCreationException;
import com.develhope.spring.deals.repositories.OrderRepository;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderServicesTest {

    @Autowired
    private OrderService orderService;

    @MockBean
    private OrderRepository orderRepository;

    @MockBean
    private OrderMapper orderMapper;

    @Test
    public void testCreatesOrder_Success() {
        OrderCreatorDTO orderCreatorDTO = new OrderCreatorDTO();
        orderCreatorDTO.setUserId(1L);
        orderCreatorDTO.setVehicleId(1L);
        Order order = new Order();

        when(orderMapper.toEntityFrom(orderCreatorDTO)).thenReturn(order);

        when(orderRepository.save(order)).thenReturn(order);

        orderService.create(orderCreatorDTO);

        verify(orderRepository).save(order);
    }

    @Test
    public void testCreatesOrder_SuccessWithAsserThrow() {
        OrderCreatorDTO orderCreatorDTO = new OrderCreatorDTO();
        orderCreatorDTO.setUserId(1L);
        orderCreatorDTO.setVehicleId(1L);
        Order order = new Order();

        when(orderMapper.toEntityFrom(orderCreatorDTO)).thenReturn(order);

        when(orderRepository.save(order)).thenThrow(new RuntimeException("Failed to save order"));

        assertThrows(OrderCreationException.class, () -> {
            orderService.create(orderCreatorDTO);
        });

        verify(orderRepository, times(1)).save(order);
    }
}
