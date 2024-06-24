package com.develhope.spring.deals.services;


import com.develhope.spring.deals.dtos.ordersDtos.OrderCreatorDTO;
import com.develhope.spring.deals.dtos.ordersDtos.OrderResponseDTO;
import com.develhope.spring.deals.dtos.ordersDtos.OrderUpdatedDTO;
import com.develhope.spring.deals.models.Order;
import com.develhope.spring.deals.components.mappers.OrderMapper;
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
import org.springframework.stereotype.*;

import java.util.NoSuchElementException;

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
        if (operator.getRole().equals(Roles.BUYER)){
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

    public void deleteBy(long orderId) {
        orderRepository.deleteById(orderId);
    }

    public void deleteOrderByIdAndUserId(long orderId, User userDetails) {
        if (orderRepository.findByIdAndUserId(orderId, userDetails.getId()).isEmpty()) {
            throw new OrderNotFoundException("Order Not Found!");
        }
        orderRepository.deleteById(orderId);
    }

    public boolean checkOrderId(long orderId, User userDetails) {
        return orderRepository.findByIdAndUserId(orderId, userDetails.getId()).isPresent();
    }
}