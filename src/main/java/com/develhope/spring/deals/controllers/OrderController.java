package com.develhope.spring.deals.controllers;

import com.develhope.spring.deals.dtos.OrderCreatorDTO;
import com.develhope.spring.deals.dtos.OrderResponseDTO;
import com.develhope.spring.deals.dtos.OrderUpdatedDTO;
import com.develhope.spring.deals.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResponseDTO createOrder(@RequestBody OrderCreatorDTO orderCreatorDTO) {
        return orderService.create(orderCreatorDTO);
    }

    @Secured({"ADMIN", "SALESPERSON"})
    @PatchMapping("{orderId}")
    @ResponseStatus(HttpStatus.OK)
    public OrderUpdatedDTO updateOrder(@PathVariable long orderId,
                                       @RequestBody OrderCreatorDTO orderCreatorDTO) {
        return orderService.update(orderId, orderCreatorDTO);
    }

    @Secured({"ADMIN"})
    @DeleteMapping("/admin/{orderId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteOrderByAdmin(@PathVariable Long orderId) {
        orderService.deleteByAdmin(orderId);
    }

    @Secured({"ADMIN", "SALESPERSON", "BUYER"})
    @DeleteMapping("/{orderId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteOrder(@PathVariable Long orderId) {
        orderService.delete(orderId);
    }

}