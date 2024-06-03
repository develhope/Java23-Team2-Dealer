package com.develhope.spring.deals.controllers;

import com.develhope.spring.deals.dtos.OrderCreatorDTO;
import com.develhope.spring.deals.models.Order;
import com.develhope.spring.deals.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/admin")
    @ResponseStatus(HttpStatus.OK)
    public Order createOrderForUser(@RequestBody OrderCreatorDTO orderDTO) {
        Order order = orderService.createOrderForUser(orderDTO);
        return order;
    }

    @PostMapping("/salesperson")
    @ResponseStatus(HttpStatus.OK)
    public Order createOrderForVehicle(@RequestBody OrderCreatorDTO orderDTO) {
        Order order = orderService.createOrderForVehicle(orderDTO);
        return order;
    }

    @PostMapping("/buyer")
    @ResponseStatus(HttpStatus.OK)
    public Order purchaseVehicle(@RequestBody OrderCreatorDTO orderDTO) {
        Order order = orderService.purchaseVehicle(orderDTO);
        return order;
    }
}