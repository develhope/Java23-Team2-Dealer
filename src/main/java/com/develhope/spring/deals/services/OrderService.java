package com.develhope.spring.deals.services;


import com.develhope.spring.deals.dtos.OrderCreatorDTO;
import com.develhope.spring.deals.models.Order;
import com.develhope.spring.deals.models.OrderStatus;
import com.develhope.spring.deals.repositories.OrderRepository;
import com.develhope.spring.users.models.User;
import com.develhope.spring.users.repositories.UserRepository;
import com.develhope.spring.users.responseStatus.UserNotFoundException;
import com.develhope.spring.vehicles.models.Vehicle;
import com.develhope.spring.vehicles.repositories.VehicleRepository;
import com.develhope.spring.vehicles.responseStatus.VehicleNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;


@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Transactional
    public Order createOrderForUser(OrderCreatorDTO orderDTO) {
        User user = userRepository.findById(orderDTO.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        Vehicle vehicle = vehicleRepository.findById(orderDTO.getVehicleId())
                .orElseThrow(() -> new VehicleNotFoundException("Vehicle not found"));

        Order order = new Order(orderDTO.isDownPayment(), OrderStatus.PENDING, vehicle);
        order.setUser(user);
        return orderRepository.save(order);
    }

    @Transactional
    public Order createOrderForVehicle(OrderCreatorDTO orderDTO) {
        Vehicle vehicle = vehicleRepository.findById(orderDTO.getVehicleId())
                .orElseThrow(() -> new VehicleNotFoundException("Vehicle not found"));

        Order order = new Order(orderDTO.isDownPayment(), OrderStatus.PENDING, vehicle);
        return orderRepository.save(order);
    }

    @Transactional
    public Order purchaseVehicle(OrderCreatorDTO orderDTO) {
        User user = userRepository.findById(orderDTO.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        Vehicle vehicle = vehicleRepository.findById(orderDTO.getVehicleId())
                .orElseThrow(() -> new VehicleNotFoundException("Vehicle not found"));

        Order order = new Order(orderDTO.isDownPayment(), OrderStatus.PAID, vehicle);
        order.setUser(user);
        order.setPaid(true);
        return orderRepository.save(order);
    }
}
