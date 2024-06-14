package com.develhope.spring.deals.services;


import com.develhope.spring.deals.dtos.OrderCreatorDTO;
import com.develhope.spring.deals.dtos.OrderResponseDTO;
import com.develhope.spring.deals.dtos.OrderReworkedDTO;
import com.develhope.spring.deals.dtos.OrderUpdaterDTO;
import com.develhope.spring.deals.models.Order;
import com.develhope.spring.deals.components.OrderMapper;
import com.develhope.spring.deals.repositories.OrderRepository;
import com.develhope.spring.deals.responseStatus.NotAvailableVehicleException;
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

    void checkValidVehicleMarketStatus(OrderCreatorDTO orderCreatorDTO) {
        Vehicle vehicle = vehicleRepository.findById(orderCreatorDTO.getVehicleId()).orElseThrow(NoSuchElementException::new);
        if (vehicle.getMarketStatus() == MarketStatus.NOTAVAILABLE) {
            throw new NotAvailableVehicleException("Vehicle not orderable.");
        }
    }


    public OrderResponseDTO create(OrderCreatorDTO orderCreatorDTO) {
        checkValidVehicleMarketStatus(orderCreatorDTO);
        Order insertedOrder = orderMapper.toEntity(orderCreatorDTO);
        Order savedOrder = orderRepository.save(insertedOrder);
        return orderMapper.toResponseDTO(savedOrder);
    }

    public OrderReworkedDTO update (long orderId, OrderUpdaterDTO orderUpdaterDTO){
        Order orderToUpdate = orderRepository.findById(orderId).orElseThrow();

        orderToUpdate.setDownPayment(orderUpdaterDTO.isDownPayment());
        orderToUpdate.setOrderStatus(orderUpdaterDTO.getOrderStatus());
        orderToUpdate.setPaid(orderUpdaterDTO.isPaid());

        Order newOrderSaved = orderRepository.save(orderToUpdate);
        return orderMapper.toReworkedDTO(newOrderSaved);
    }
}