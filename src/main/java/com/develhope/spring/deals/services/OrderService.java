package com.develhope.spring.deals.services;

import com.develhope.spring.deals.dtos.OrderCreatorDTO;
import com.develhope.spring.deals.models.Order;
import com.develhope.spring.deals.models.OrderMapper;
import com.develhope.spring.deals.models.OrderStatus;
import com.develhope.spring.deals.models.exceptions.OrderCreationException;
import com.develhope.spring.users.models.Roles;
import com.develhope.spring.users.models.User;
import com.develhope.spring.users.repositories.UserRepository;
import com.develhope.spring.users.responseStatus.UserNotFoundException;
import com.develhope.spring.vehicles.models.Vehicle;
import com.develhope.spring.vehicles.repositories.VehicleRepository;
import com.develhope.spring.vehicles.responseStatus.VehicleNotFoundException;
import com.develhope.spring.vehicles.services.VehicleService;
import com.develhope.spring.vehicles.vehicleEnums.MarketStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.develhope.spring.deals.repositories.OrderRepository;

import java.util.Optional;

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

    public OrderCreatorDTO create(OrderCreatorDTO orderCreatorDTO) {
        Optional<Vehicle> optionalVehicle = Optional.ofNullable(vehicleService.findVehicleBy(orderCreatorDTO.getVehicleId()));
        Vehicle vehicle = optionalVehicle.orElseThrow(() -> new VehicleNotFoundException("Vehicle not found"));

        if (vehicle.getMarketStatus() != MarketStatus.ORDERABLE || vehicle.getOrderStatus() == OrderStatus.SHIPPED) {
            throw new OrderCreationException("Vehicle is not orderable");
        }

        User user = userRepository.findById(orderCreatorDTO.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        if (orderCreatorDTO.getAdminId() != null) {
            User admin = userRepository.findById(orderCreatorDTO.getAdminId())
                    .orElseThrow(() -> new UserNotFoundException("Admin not found"));

            if (!Roles.contains(Roles.ADMIN, admin.getRoles())) {
                throw new OrderCreationException("Only admin can create an order for another user");
            }
        }

        Order order = OrderMapper.toEntity(orderCreatorDTO, user, vehicle);
        vehicle.setOrderStatus(OrderStatus.PENDING);
        vehicleRepository.save(vehicle);
        orderRepository.save(order);
        return OrderMapper.toDTO(order);
    }
}

