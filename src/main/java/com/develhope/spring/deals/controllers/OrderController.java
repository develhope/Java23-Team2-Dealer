package com.develhope.spring.deals.controllers;

import com.develhope.spring.deals.dtos.DeleteOrderResponseDTO;
import com.develhope.spring.deals.dtos.OrderCreatorDTO;
import com.develhope.spring.deals.dtos.OrderResponseDTO;
import com.develhope.spring.deals.dtos.OrderUpdatedDTO;
import com.develhope.spring.deals.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @DeleteMapping("/{orderId}")
    @ResponseStatus(HttpStatus.OK)
    public DeleteOrderResponseDTO deleteOrder(@PathVariable Long orderId) {
        orderService.delete(orderId);
        return new DeleteOrderResponseDTO("Order deleted successfully");


    }

    @PatchMapping("/{operatorId}/{orderId}")
    @ResponseStatus(HttpStatus.OK)
    public OrderUpdatedDTO updateOrder(@PathVariable long operatorId,
                                       @PathVariable long orderId,
                                       @RequestBody OrderCreatorDTO orderCreatorDTO) {
        return orderService.update(operatorId, orderId, orderCreatorDTO);

    }
}