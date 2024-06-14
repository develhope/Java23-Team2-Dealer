package com.develhope.spring.deals.services;


import com.develhope.spring.deals.dtos.OrderCreatorDTO;
import com.develhope.spring.deals.dtos.OrderResponseDTO;
import com.develhope.spring.deals.dtos.OrderUpdatedDTO;
import com.develhope.spring.deals.models.Order;
import com.develhope.spring.deals.components.OrderMapper;
import com.develhope.spring.deals.repositories.OrderRepository;
import com.develhope.spring.deals.responseStatus.NotAvailableVehicleException;
import com.develhope.spring.deals.responseStatus.OrderNotFoundException;
import com.develhope.spring.users.models.Roles;
import com.develhope.spring.users.models.User;
import com.develhope.spring.users.repositories.UserRepository;
import com.develhope.spring.vehicles.models.Vehicle;
import com.develhope.spring.vehicles.repositories.VehicleRepository;
import com.develhope.spring.vehicles.responseStatus.NotAuthorizedOperationException;
import com.develhope.spring.vehicles.vehicleEnums.MarketStatus;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.*;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private UserRepository userRepository;

    public OrderResponseDTO create(OrderCreatorDTO orderCreatorDTO) {
        checkValidVehicleMarketStatus(orderCreatorDTO);
        Order insertedOrder = orderMapper.toEntity(orderCreatorDTO);
        Order savedOrder = orderRepository.save(insertedOrder);
        return orderMapper.toResponseDTO(savedOrder);
    }

    void checkValidVehicleMarketStatus(OrderCreatorDTO orderCreatorDTO) {
        Vehicle vehicle = vehicleRepository.findById(orderCreatorDTO.getVehicleId()).orElseThrow(NoSuchElementException::new);
        if (vehicle.getMarketStatus() == MarketStatus.NOTAVAILABLE) {
            throw new NotAvailableVehicleException("Vehicle not orderable.");
        }
    }

    void checkValidOperator(long userId) {
        User operator = userRepository.findById(userId).orElseThrow(NoSuchElementException::new);
        if (operator.getRoles().equals(Roles.BUYER)) {
            throw new NotAuthorizedOperationException("Permesso negato. Non sei autorizzato ad effettuare questa operazione");
        }
    }

    public OrderUpdatedDTO update(long orderId, OrderCreatorDTO orderCreatorDTO) {
        checkValidVehicleMarketStatus(orderCreatorDTO);
        Order orderToUpdate = orderRepository.findById(orderId).orElseThrow();
        Vehicle newVehicle = vehicleRepository.findById(orderCreatorDTO.getVehicleId()).orElseThrow();
        orderToUpdate.setDownPayment(orderCreatorDTO.isDownPayment());
        orderToUpdate.setOrderStatus(orderCreatorDTO.getOrderStatus());
        orderToUpdate.setPaid(orderCreatorDTO.isPaid());
        orderToUpdate.setVehicle(newVehicle);
        Order newOrderSaved = orderRepository.save(orderToUpdate);
        return orderMapper.toOrderUpdateDTO(newOrderSaved);
    }

    public void deleteByAdmin(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException(new NoSuchElementException()));
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ADMIN"));

        if (isAdmin || isOrderOwner(authentication, order)) {
            orderRepository.deleteById(orderId);
        } else {
            throw new NotAuthorizedOperationException("You do not have permission to cancel this order");
        }
    }

    private boolean isOrderOwner(Authentication authentication, Order order) {
        String username = authentication.getName();
        Roles userRole = order.getUser().getRoles();

        return username.equals(order.getUser().getEmail()) &&
                (userRole == Roles.SALESPERSON || userRole == Roles.BUYER);
    }

    public void delete(Long orderId) {
        orderRepository.deleteById(orderId);
    }
}