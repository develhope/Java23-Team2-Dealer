package com.develhope.spring.controller;

import com.develhope.spring.model.Order;
import com.develhope.spring.model.OrderStatus;
import org.springframework.web.bind.annotation.*;

/**
 * OrderController is a REST controller that manages orders associated with vehicles.
 * It provides API endpoints for creating a new order and retrieving an existing order by the vehicle ID.
 */
@RestController
@RequestMapping("/orders")
public class OrderController {

    /**
     * Creates a new order based on the provided Order object.
     *
     * @param order the order details sent from the client to be created.
     * @return the created order with possibly modified or additional attributes set by server logic.
     */
    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        return order;
    }

    /**
     * Retrieves an order associated with a specific vehicle ID.
     * This is a simplistic example where an Order object is instantiated directly in the method.
     *
     * @param vehicleId the ID of the vehicle associated with the order.
     * @return an Order object representing the order details. Currently returns a new Order instance
     *         with default values and the vehicleId set as provided.
     */
    @GetMapping("/{vehicleId}")
    public Order getOrder(@PathVariable String vehicleId) {
        return new Order(true, false, OrderStatus.PENDING, vehicleId);
    }

}