package com.develhope.spring.deals.services;


import com.develhope.spring.deals.dtos.OrderCreatorDTO;
import com.develhope.spring.deals.dtos.OrderResponseDTO;
import com.develhope.spring.deals.models.Order;
import com.develhope.spring.deals.models.OrderMapper;
import com.develhope.spring.deals.models.exceptions.OrderCreationException;
import com.develhope.spring.deals.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;


@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderRepository orderRepository;

    public OrderResponseDTO create(OrderCreatorDTO orderCreatorDTO) {

        Order order = orderMapper.toEntityFrom(orderCreatorDTO);

        try {
            order = orderRepository.save(order);
        } catch (Exception e) {
            throw new OrderCreationException("Failed to create order");
        }
        return orderMapper.toResponseDTOFrom(order);

    }
}