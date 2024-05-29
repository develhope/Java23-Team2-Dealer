package com.develhope.spring.deals.controllers;

import com.develhope.spring.deals.dto.OrderDTO;
import com.develhope.spring.deals.models.Order;
import com.develhope.spring.deals.services.OrderService;
import com.develhope.spring.users.models.Roles;
import com.develhope.spring.users.models.User;
import com.develhope.spring.users.models.exceptions.UserNotFoundException;
import com.develhope.spring.users.models.exceptions.VehicleNotFoundException;
import com.develhope.spring.users.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<?> createOrder(@RequestBody OrderDTO orderDTO) {
        try {
            Order order = orderService.createOrder(orderDTO);
            return ResponseEntity.ok(order);
        } catch (VehicleNotFoundException | UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
