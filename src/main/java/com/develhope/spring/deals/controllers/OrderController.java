package com.develhope.spring.deals.controllers;

import com.develhope.spring.deals.dtos.OrderCreatorDTO;
import com.develhope.spring.deals.dtos.OrderSavedDTO;
import com.develhope.spring.deals.dtos.OrderReworkedDTO;
import com.develhope.spring.deals.dtos.OrderUpdaterDTO;
import com.develhope.spring.deals.services.OrderService;
import com.develhope.spring.users.models.Roles;
import com.develhope.spring.users.models.User;
import com.develhope.spring.vehicles.responseStatus.NotAuthorizedOperationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderSavedDTO createOrder(@RequestBody OrderCreatorDTO orderCreatorDTO) {
        return orderService.create(orderCreatorDTO);
    }

    @Secured({"ADMIN", "SALESPERSON"})
    @PatchMapping("{orderId}")
    @ResponseStatus(HttpStatus.OK)
    public OrderReworkedDTO updateOrder (@PathVariable long orderId,
                                         @RequestBody OrderUpdaterDTO orderUpdaterDTO){
        return orderService.update(orderId, orderUpdaterDTO);
    }

    @Secured({"ADMIN", "SALESPERSON", "BUYER"})
    @DeleteMapping("/{orderId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteOrderByAdmin(@PathVariable Long orderId, @AuthenticationPrincipal User userDetails) {
        boolean isAdmin = userDetails.getRoles().equals(Roles.ADMIN);
        if (isAdmin) {
            orderService.deleteBy(orderId);
        } else {
            if (orderService.checkOrderId(orderId, userDetails)) {
                orderService.deleteOrderByIdAndUserId(orderId, userDetails);
            } else {
                throw new NotAuthorizedOperationException("You are not authorized to cancel this order.");
            }
        }
    }
}