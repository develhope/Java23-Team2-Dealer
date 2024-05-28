package com.develhope.spring.deals.services;

import com.develhope.spring.deals.models.Order;
import com.develhope.spring.deals.models.OrderStatus;
import com.develhope.spring.users.models.Roles;
import com.develhope.spring.users.models.User;
import com.develhope.spring.users.repositories.UserRepository;
import com.develhope.spring.vehicles.models.Vehicle;
import com.develhope.spring.vehicles.repositories.VehicleRepository;
import com.develhope.spring.vehicles.vehicleEnums.MarketStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private UserRepository userRepository;

    public Order createOrderForBuyer(User buyer, long vehicleId) throws Exception {
        if (buyer.getRoles() != Roles.BUYER) {
            throw new Exception("User is not authorized to create an order as a buyer");
        }

        Vehicle vehicle = vehicleRepository.findById(vehicleId).orElseThrow(() -> new Exception("Vehicle not found"));

        if (vehicle.getMarketStatus() != MarketStatus.ORDERABLE) {
            throw new Exception("Vehicle is not orderable");
        }

        return new Order(true, OrderStatus.PENDING, (int) vehicle.getId());
    }

    public Order createOrderForAdmin(long userId, long vehicleId) throws Exception {
        User user = userRepository.findById(userId).orElseThrow(() -> new Exception("User not found"));

        Vehicle vehicle = vehicleRepository.findById(vehicleId).orElseThrow(() -> new Exception("Vehicle not found"));

        return new Order(true, OrderStatus.PENDING, (int) vehicle.getId());
    }

    public Order createOrderForSalesperson(User salesperson, long vehicleId) throws Exception {
        if (salesperson.getRoles() != Roles.SALESPERSON) {
            throw new Exception("User is not authorized to create an order as a salesperson");
        }

        Vehicle vehicle = vehicleRepository.findById(vehicleId).orElseThrow(() -> new Exception("Vehicle not found"));

        if (vehicle.getMarketStatus() != MarketStatus.ORDERABLE) {
            throw new Exception("Vehicle is not orderable");
        }

        return new Order(true, OrderStatus.PENDING, (int) vehicle.getId());
    }
}
