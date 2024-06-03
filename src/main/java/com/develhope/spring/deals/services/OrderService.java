package com.develhope.spring.deals.services;


import com.develhope.spring.deals.dtos.OrderCreatorDTO;
import com.develhope.spring.deals.dtos.OrderResponseDTO;
import com.develhope.spring.deals.models.Order;
import com.develhope.spring.deals.models.OrderMapper;
import com.develhope.spring.deals.models.OrderStatus;
import com.develhope.spring.deals.models.exceptions.OrderCreationException;
import com.develhope.spring.deals.repositories.OrderRepository;
import com.develhope.spring.users.models.User;
import com.develhope.spring.users.models.UserMapper;
import com.develhope.spring.users.repositories.UserRepository;
import com.develhope.spring.users.responseStatus.UserNotFoundException;
import com.develhope.spring.vehicles.models.Vehicle;
import com.develhope.spring.vehicles.models.VehicleMapper;
import com.develhope.spring.vehicles.repositories.VehicleRepository;
import com.develhope.spring.vehicles.responseStatus.VehicleNotFoundException;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private VehicleMapper vehicleMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private UserRepository userRepository;

    /*public OrderResponseDTO create(OrderCreatorDTO orderCreatorDTO) {
        Vehicle vehicle = vehicleRepository.findById(orderCreatorDTO.getVehicleId())
                .orElseThrow(() -> new VehicleNotFoundException("Vehicle not found"));
        User user = userRepository.findById(orderCreatorDTO.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        Order order = orderMapper.toEntityFrom(orderCreatorDTO);
        order.setVehicle(vehicle);
        order.setUser(user);
        order.setOrderStatus(OrderStatus.PENDING);

        try {
            order = orderRepository.save(order);
        } catch (Exception e) {
            throw new OrderCreationException("Failed to create order");
        }
        return orderMapper.toResponseDTOFrom(order);
    }*/

    public OrderResponseDTO create(OrderCreatorDTO orderCreatorDTO) {
        Vehicle vehicle = vehicleRepository.findById(orderCreatorDTO.getVehicleId())
                .orElseThrow(() -> new VehicleNotFoundException("Vehicle not found"));
        User user = userRepository.findById(orderCreatorDTO.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        Order order = orderMapper.toEntityFrom(orderCreatorDTO);
        order.setVehicle(vehicle);
        order.setUser(user);

        try {
            order = orderRepository.save(order);
        } catch (Exception e) {
            throw new OrderCreationException("Failed to create order");
        }
        return orderMapper.toResponseDTOFrom(order);

    }
}