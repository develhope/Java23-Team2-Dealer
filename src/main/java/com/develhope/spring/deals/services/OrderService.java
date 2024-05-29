package com.develhope.spring.deals.services;

import com.develhope.spring.deals.models.Order;
import com.develhope.spring.deals.models.OrderStatus;
import com.develhope.spring.deals.repositories.OrderRepository;
import com.develhope.spring.users.models.Roles;
import com.develhope.spring.users.models.User;
import com.develhope.spring.users.repositories.UserRepository;
import com.develhope.spring.vehicles.models.Vehicle;
import com.develhope.spring.vehicles.repositories.VehicleRepository;
import com.develhope.spring.vehicles.services.VehicleService;
import com.develhope.spring.vehicles.vehicleEnums.MarketStatus;
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

    public Order createOrder(boolean downPayment, long vehicleId, User user) throws Exception {
        Vehicle vehicle = vehicleService.findVehicleById(vehicleId)
                .orElseThrow(() -> new Exception("Vehicle not found"));

        if ((user.getRoles() == Roles.BUYER || user.getRoles() == Roles.SALESPERSON) && !vehicle.getOrderable()) {
            throw new Exception("Vehicle is not orderable");
        }
        Order order = new Order(downPayment, OrderStatus.PENDING, vehicleId, user);
        return orderRepository.save(order);
    }

    public Order createOrderForUser(boolean downPayment, long vehicleId, User admin, User user) throws Exception {
        Vehicle vehicle = vehicleService.findVehicleById(vehicleId)
                .orElseThrow(() -> new Exception("Vehicle not found"));

        if (admin.getRoles() != Roles.ADMIN) {
            throw new Exception("Only admin can create an order for another user");
        }
        Order order = new Order(downPayment, OrderStatus.PENDING, vehicleId, user);
        return orderRepository.save(order);
    }
}
