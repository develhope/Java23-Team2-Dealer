package com.develhope.spring.deals.services;

import com.develhope.spring.deals.repositories.OrderRepository;
import com.develhope.spring.users.repositories.UserRepository;
import com.develhope.spring.vehicles.repositories.VehicleRepository;
import com.develhope.spring.vehicles.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private OrderRepository orderRepository;

    /*public Order create(OrderCreatorDTO orderCreatorDTO) {
        Vehicle vehicle = vehicleService.findVehicleById(orderCreatorDTO.getVehicleId())
                .orElseThrow(() -> new VehicleNotFoundException("Vehicle not found"));

        if (vehicle.getOrderStatus() != OrderStatus.PENDING && vehicle.getOrderStatus() != OrderStatus.SHIPPED) {
            throw new RuntimeException("Vehicle is not orderable");
        }

        User user = userRepository.findById(orderCreatorDTO.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        if (orderCreatorDTO.getAdminId() != null) {
            User admin = userRepository.findById(orderCreatorDTO.getAdminId())
                    .orElseThrow(() -> new UserNotFoundException("Admin not found"));
            if (admin.getRoles() != Roles.ADMIN) {
                throw new RuntimeException("Only admin can create an order for another user");
            }
        }

        Order order = new Order(orderCreatorDTO.isDownPayment(), OrderStatus.PENDING, orderCreatorDTO.getVehicleId(), user);
        vehicle.setOrderStatus(OrderStatus.PENDING);
        vehicleRepository.save(vehicle);
        return orderRepository.save(order);
    }*/


}
