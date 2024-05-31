package com.develhope.spring.deals.models;

import com.develhope.spring.deals.dtos.OrderCreatorDTO;
import com.develhope.spring.users.models.User;
import com.develhope.spring.vehicles.models.Vehicle;

public class OrderMapper {

    public static Order toEntity(OrderCreatorDTO orderCreatorDTO, User user, Vehicle vehicle) {
        Order order = new Order(
                orderCreatorDTO.isDownPayment(),
                OrderStatus.PENDING,
                vehicle
        );
        order.setPaid(false);
        return order;
    }

    public static OrderCreatorDTO toDTO(Order order) {
        return new OrderCreatorDTO(
                order.isDownPayment(),
                order.getVehicle().getId(),
                order.getUser().getId(),
                null
        );
    }
}
