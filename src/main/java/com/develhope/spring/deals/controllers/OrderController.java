package com.develhope.spring.deals.controllers;

import com.develhope.spring.deals.dtos.OrderCreatorDTO;
import com.develhope.spring.deals.models.Order;
import com.develhope.spring.deals.services.OrderService;
import com.develhope.spring.users.models.exceptions.UserNotFoundException;
import com.develhope.spring.users.models.exceptions.VehicleNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /*@PostMapping
    public ResponseEntity<?> createOrder(@RequestBody OrderCreatorDTO orderCreatorDTO) {
        try {
            Order order = orderService.create(orderCreatorDTO);
            return ResponseEntity.ok(order);
        } catch (VehicleNotFoundException | UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }*/
}
