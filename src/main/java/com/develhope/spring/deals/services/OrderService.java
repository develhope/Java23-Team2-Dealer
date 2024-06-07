package com.develhope.spring.deals.services;

import com.develhope.spring.deals.dtos.OrderCreatorDTO;
import com.develhope.spring.deals.dtos.OrderResponseDTO;
import com.develhope.spring.deals.models.Order;
import com.develhope.spring.deals.components.OrderMapper;
import com.develhope.spring.deals.repositories.OrderRepository;
import com.develhope.spring.deals.responseStatus.NotAvailableVehicleException;
import com.develhope.spring.deals.responseStatus.OrderNotFoundException;
import com.develhope.spring.vehicles.models.Vehicle;
import com.develhope.spring.vehicles.repositories.VehicleRepository;
import com.develhope.spring.vehicles.vehicleEnums.MarketStatus;
import org.springframework.beans.factory.annotation.*;
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


    public OrderResponseDTO create(OrderCreatorDTO orderCreatorDTO) {
        checkValidVehicleMarketStatus(orderCreatorDTO);
        Order insertedOrder = orderMapper.toEntity(orderCreatorDTO);
        Order savedOrder = orderRepository.save(insertedOrder);
        return orderMapper.toResponseDTO(savedOrder);
    }

    public void delete(Long orderId) {
        try {
            orderRepository.deleteById(orderId);
        } catch (NoSuchElementException ex) {
            throw new OrderNotFoundException("Order with ID " + orderId + " not found", ex);
        }
    }

    private void checkValidVehicleMarketStatus(OrderCreatorDTO orderCreatorDTO) {
        Optional<Vehicle> vehicleOptional = vehicleRepository.findById(orderCreatorDTO.getVehicleId());
        if (vehicleOptional.isPresent() && vehicleOptional.orElseThrow().getMarketStatus() == MarketStatus.NOTAVAILABLE) {
            throw new NotAvailableVehicleException("Vehicle not orderable.");
        }
    }




}