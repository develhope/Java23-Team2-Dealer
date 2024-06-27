package com.develhope.spring.deals.components.mappers;

import com.develhope.spring.deals.dtos.ordersDtos.OrderCreatorDTO;
import com.develhope.spring.deals.dtos.ordersDtos.OrderResponseDTO;
import com.develhope.spring.deals.dtos.ordersDtos.OrderUpdatedDTO;
import com.develhope.spring.deals.models.Order;
import com.develhope.spring.users.components.UserMapper;
import com.develhope.spring.users.models.User;
import com.develhope.spring.vehicles.components.VehicleMapper;
import com.develhope.spring.vehicles.dtos.VehicleOrderReturnerDTO;
import com.develhope.spring.vehicles.models.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class OrderMapper {

    @Autowired
    private VehicleMapper vehicleMapper;
    @Autowired
    private UserMapper userMapper;

    public Order toEntity(OrderCreatorDTO orderCreatorDTO) {
        Vehicle vehicle = new Vehicle(orderCreatorDTO.getVehicleId());
        User user = new User(orderCreatorDTO.getUserId());
        User seller = Optional.ofNullable(orderCreatorDTO.getSellerId()).map(User::new).orElse(null);

        Order order = new Order();
        order.setVehicle(vehicle);
        order.setSeller(seller);
        order.setUser(user);
        order.setDownPayment(orderCreatorDTO.isDownPayment());
        order.setOrderStatus(orderCreatorDTO.getOrderStatus());
        order.setPaid(orderCreatorDTO.isPaid());

        return order;
    }

    public OrderResponseDTO toResponseDTO(Order order) {
        VehicleOrderReturnerDTO vehicleOrderReturnerDTO = vehicleMapper.toOrderReturnerDTO(order.getVehicle());
        Long sellerId =Optional.ofNullable(order.getSeller()).map(User::getId).orElse(null);
        return new OrderResponseDTO(
                order.getId(),
                order.isDownPayment(),
                vehicleOrderReturnerDTO,
                order.getUser().getId(),
                sellerId,
                order.getOrderStatus(),
                order.isPaid()
        );
    }

    public OrderCreatorDTO toCreatorDTO(Order order) {

        return new OrderCreatorDTO(
                order.isDownPayment(),
                order.getVehicle().getId(),
                order.getSeller().getId(),
                order.getUser().getId(),
                order.getOrderStatus(),
                order.isPaid()
        );
    }

    public OrderUpdatedDTO toOrderUpdatedDTO(Order order) {
        Long sellerId =Optional.ofNullable(order.getSeller()).map(User::getId).orElse(null);
        return new OrderUpdatedDTO(
                order.getId(),
                sellerId,
                order.isDownPayment(),
                order.getOrderStatus(),
                order.isPaid());
    }
}
