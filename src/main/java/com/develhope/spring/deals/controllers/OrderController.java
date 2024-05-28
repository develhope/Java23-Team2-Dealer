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

    @PostMapping("/buyer/{vehicleId}")
    public ResponseEntity<Order> createOrderForBuyer(@RequestParam long userId, @PathVariable long vehicleId) {
        try {
            User user = userRepository.findById(userId).orElseThrow(() -> new Exception("User not found"));
            Order order = orderService.createOrderForBuyer(user, vehicleId);
            return ResponseEntity.ok(order);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/admin")
    public ResponseEntity<Order> createOrderForAdmin(@RequestParam long adminId, @RequestParam long vehicleId, @RequestParam long userId) {
        try {
            User admin = userRepository.findById(adminId).orElseThrow(() -> new Exception("Admin not found"));
            if (admin.getRoles() != Roles.ADMIN) {
                throw new Exception("User is not authorized to create an order as an admin");
            }
            Order order = orderService.createOrderForAdmin(userId, vehicleId);
            return ResponseEntity.ok(order);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/salesperson/{vehicleId}")
    public ResponseEntity<Order> createOrderForSalesperson(@RequestParam long userId, @PathVariable long vehicleId) {
        try {
            User user = userRepository.findById(userId).orElseThrow(() -> new Exception("User not found"));
            Order order = orderService.createOrderForSalesperson(user, vehicleId);
            return ResponseEntity.ok(order);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
