package com.develhope.spring.deals.controllers;

import com.develhope.spring.deals.models.Order;
import com.develhope.spring.deals.services.OrderService;
import com.develhope.spring.users.models.Roles;
import com.develhope.spring.users.models.User;
import com.develhope.spring.users.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/create")
    public ResponseEntity<?> createOrder(@RequestParam boolean downPayment,
                                         @RequestParam long vehicleId,
                                         @RequestParam long userId,
                                         @RequestParam(required = false) Long adminId) {
        try {
            User user = userRepository.findById(userId).orElseThrow(() -> new Exception("User not found"));

            if (adminId != null) {
                User admin = userRepository.findById(adminId).orElseThrow(() -> new Exception("Admin not found"));
                Order order = orderService.createOrderForUser(downPayment, vehicleId, admin, user);
                return ResponseEntity.ok(order);
            } else {
                Order order = orderService.createOrder(downPayment, vehicleId, user);
                return ResponseEntity.ok(order);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
