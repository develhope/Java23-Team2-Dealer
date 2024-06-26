package com.develhope.spring.deals.components.mappers;

import com.develhope.spring.deals.dtos.ordersDtos.OrderCreatorDTO;
import com.develhope.spring.deals.dtos.ordersDtos.OrderResponseDTO;
import com.develhope.spring.deals.dtos.ordersDtos.OrderUpdatedDTO;
import com.develhope.spring.deals.models.Order;
import com.develhope.spring.users.components.UserMapper;
import com.develhope.spring.users.dtos.UserOrderReturnerDTO;
import com.develhope.spring.users.models.User;
import com.develhope.spring.vehicles.dtos.VehicleOrderReturnerDTO;
import com.develhope.spring.vehicles.models.Vehicle;
import com.develhope.spring.vehicles.components.VehicleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    @Autowired
    private VehicleMapper vehicleMapper;
    @Autowired
    private UserMapper userMapper;

    public Order toEntity(OrderCreatorDTO orderCreatorDTO) {
        Vehicle vehicle = new Vehicle(orderCreatorDTO.getVehicleId());
        User user = new User(orderCreatorDTO.getUserId());
        User seller = new User(orderCreatorDTO.getSellerId());

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
        UserOrderReturnerDTO userOrderReturnerDTO = userMapper.toUserOrderReturnerDTO(order.getUser());
        UserOrderReturnerDTO sellerOrderReturnerDTO = userMapper.toUserOrderReturnerDTO(order.getSeller());
        return new OrderResponseDTO(
                order.getId(),
                order.isDownPayment(),
                vehicleOrderReturnerDTO,
                userOrderReturnerDTO.getId(),
                sellerOrderReturnerDTO.getId(),
                order.getOrderStatus(),
                order.isPaid()
        );
    }
    public OrderCreatorDTO toCreatorDTO(Order order){

        return new OrderCreatorDTO(
                order.isDownPayment(),
                order.getVehicle().getId(),
                order.getSeller().getId(),
                order.getUser().getId(),
                order.getOrderStatus(),
                order.isPaid()
        );
    }

    public OrderUpdatedDTO toOrderUpdatedDTO(Order order){
        return new OrderUpdatedDTO(
                order.getId(),
                order.isDownPayment(),
                order.getOrderStatus(),
                order.isPaid());
    }
}
