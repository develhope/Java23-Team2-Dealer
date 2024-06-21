package com.develhope.spring.deals.components;

import com.develhope.spring.deals.dtos.OrderCreatorDTO;
import com.develhope.spring.deals.dtos.OrderGetterDTO;
import com.develhope.spring.deals.dtos.OrderSavedDTO;
import com.develhope.spring.deals.dtos.OrderReworkedDTO;
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



    public OrderSavedDTO toResponseDTO(Order order) {
        VehicleOrderReturnerDTO vehicleOrderReturnerDTO = vehicleMapper.toOrderReturnerDTO(order.getVehicle());
        UserOrderReturnerDTO userOrderReturnerDTO = userMapper.toUserOrderReturnerDTO(order.getUser());
        return new OrderSavedDTO(
                order.getId(),
                order.isDownPayment(),
                vehicleOrderReturnerDTO,
                userOrderReturnerDTO.getId(),
                order.getOrderStatus(),
                order.isPaid()
        );
    }

    public OrderGetterDTO toGetterDTO(Order order) {
        VehicleOrderReturnerDTO vehicleOrderReturnerDTO = vehicleMapper.toOrderReturnerDTO(order.getVehicle());
        UserOrderReturnerDTO userOrderReturnerDTO = userMapper.toUserOrderReturnerDTO(order.getUser());
        return new OrderGetterDTO(
                order.getId(),
                order.isDownPayment(),
                vehicleOrderReturnerDTO,
                userOrderReturnerDTO.getId(),
                order.getOrderStatus(),
                order.isPaid(),
                order.getSeller()
        );
    }

    public OrderReworkedDTO toReworkedDTO(Order order){
        return new OrderReworkedDTO(
                order.getId(),
                order.isDownPayment(),
                order.getOrderStatus(),
                order.isPaid());
    }

    //toEntity();
    public Order toEntity(OrderCreatorDTO orderCreatorDTO) {
        Vehicle vehicle = new Vehicle(orderCreatorDTO.getVehicleId());
        User user = new User(orderCreatorDTO.getUserId());

        Order order = new Order();
        order.setDownPayment(orderCreatorDTO.isDownPayment());
        order.setOrderStatus(orderCreatorDTO.getOrderStatus());
        order.setPaid(orderCreatorDTO.isPaid());
        order.setVehicle(vehicle);
        order.setUser(user);
        order.setSeller(orderCreatorDTO.getSeller());

        return order;
    }
}
