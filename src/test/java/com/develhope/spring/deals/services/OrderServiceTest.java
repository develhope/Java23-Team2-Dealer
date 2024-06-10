package com.develhope.spring.deals.services;

import com.develhope.spring.deals.models.Order;
import com.develhope.spring.deals.repositories.OrderRepository;
import com.develhope.spring.deals.responseStatus.OrderNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.util.NoSuchElementException;
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
    private OrderRepository orderRepository;


    @Autowired
    private OrderService orderService;

    @Test
    void deleteOrder_ShouldDeleteOrder_WhenOrderExists() {
        Long orderId = 1L;

        Order order = new Order();
        order.setId(orderId);
        when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));

        orderService.delete(orderId);

        verify(orderRepository).deleteById(orderId);
    }

    @Test
    void deleteOrder_ShouldThrowOrderNotFoundException_WhenOrderDoesNotExist() {
        Long orderId = 1L;

        doThrow(new OrderNotFoundException("Order with ID 1 not found", new NoSuchElementException())).when(orderRepository).deleteById(orderId);

        assertThrows(OrderNotFoundException.class, () -> orderService.delete(orderId));
    }
}