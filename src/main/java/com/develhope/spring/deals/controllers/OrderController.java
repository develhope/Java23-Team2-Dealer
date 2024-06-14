package com.develhope.spring.deals.controllers;

import com.develhope.spring.deals.dtos.OrderCreatorDTO;
import com.develhope.spring.deals.dtos.OrderResponseDTO;
import com.develhope.spring.deals.dtos.OrderReworkedDTO;
import com.develhope.spring.deals.dtos.OrderUpdaterDTO;
import com.develhope.spring.deals.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
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
    public OrderReworkedDTO updateOrder (@PathVariable long orderId,
                                         @RequestBody OrderUpdaterDTO orderUpdaterDTO){
        return orderService.update(orderId, orderUpdaterDTO);
    }

}