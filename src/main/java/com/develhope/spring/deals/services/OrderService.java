package com.develhope.spring.deals.services;

import com.develhope.spring.deals.dto.OrderDTO;
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

    public Order createOrder(OrderDTO orderDTO) {
        Vehicle vehicle = vehicleService.findVehicleById(orderDTO.getVehicleId())
                .orElseThrow(() -> new VehicleNotFoundException("Vehicle not found"));

        if (vehicle.getOrderStatus() != OrderStatus.PENDING && vehicle.getOrderStatus() != OrderStatus.SHIPPED) {
            throw new RuntimeException("Vehicle is not orderable");
        }

        User user = userRepository.findById(orderDTO.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        // Possibile validazione aggiuntiva se adminId è presente
        if (orderDTO.getAdminId() != null) {
            User admin = userRepository.findById(orderDTO.getAdminId())
                    .orElseThrow(() -> new UserNotFoundException("Admin not found"));
            if (admin.getRoles() != Roles.ADMIN) {
                throw new RuntimeException("Only admin can create an order for another user");
            }
        }

        Order order = new Order(orderDTO.isDownPayment(), OrderStatus.PENDING, orderDTO.getVehicleId(), user);
        return orderRepository.save(order);
    }
}
