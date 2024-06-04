package com.develhope.spring.deals.models;

import com.develhope.spring.deals.dtos.OrderCreatorDTO;
import com.develhope.spring.deals.dtos.OrderResponseDTO;
import com.develhope.spring.users.components.UserMapper;
import com.develhope.spring.users.dtos.UserOrderReturnerDTO;
import com.develhope.spring.users.models.User;
import com.develhope.spring.vehicles.dtos.VehicleOrderReturnerDTO;
import com.develhope.spring.vehicles.models.Vehicle;
import com.develhope.spring.vehicles.models.VehicleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    @Autowired
    private VehicleMapper vehicleMapper;
    @Autowired
    private UserMapper userMapper;

    public Order toEntityFrom(OrderCreatorDTO orderCreatorDTO) {
        Vehicle vehicle = new Vehicle();
        vehicle.setId(orderCreatorDTO.getVehicleId());
        User user = new User();
        user.setId(orderCreatorDTO.getUserId());
        return new Order(
                0,
                orderCreatorDTO.getVehicleId(),
                orderCreatorDTO.isDownPayment(),
                orderCreatorDTO.getOrderStatus(),
                orderCreatorDTO.isPaid(),
                vehicle,
                user
        );
    }

    public OrderResponseDTO toResponseDTOFrom(Order order) {
        VehicleOrderReturnerDTO vehicleOrderReturnerDTO = vehicleMapper.toOrderReturnerDTOFrom(order.getVehicle());
        UserOrderReturnerDTO userOrderReturnerDTO = userMapper.toUserOrderReturnerDTOFrom(order.getUser());
        return new OrderResponseDTO(
                order.isDownPayment(),
                vehicleOrderReturnerDTO,
                userOrderReturnerDTO.getId(),
                order.getOrderStatus(),
                order.isPaid()
        );
    }

    /*public Order toEntityFrom(OrderResponseDTO orderResponseDTO) {
        Vehicle vehicle = vehicleMapper.toEntityFrom(orderResponseDTO.getVehicle());
        User user = userMapper.toEntityFrom(orderResponseDTO.getBuyer());
        return new Order(orderResponseDTO.getStartDate(),
                orderResponseDTO.getEndDate(),
                orderResponseDTO.getDailyCost(),
                orderResponseDTO.isPaid(),
                vehicle,
                0,
                user);
    }*/
}
