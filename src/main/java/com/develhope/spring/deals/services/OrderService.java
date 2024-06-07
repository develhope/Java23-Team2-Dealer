package com.develhope.spring.deals.services;


import com.develhope.spring.deals.dtos.OrderCreatorDTO;
import com.develhope.spring.deals.dtos.OrderResponseDTO;
import com.develhope.spring.deals.models.Order;
import com.develhope.spring.deals.models.OrderMapper;
import com.develhope.spring.deals.repositories.OrderRepository;
import com.develhope.spring.deals.responsestatus.NotAvailableVehicleException;
import com.develhope.spring.vehicles.models.Vehicle;
import com.develhope.spring.vehicles.repositories.VehicleRepository;
import com.develhope.spring.vehicles.vehicleEnums.MarketStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        Optional<Vehicle> vehicleOptional = vehicleRepository.findById(orderCreatorDTO.getVehicleId());
        if (vehicleOptional.isPresent() && vehicleOptional.get().getMarketStatus() == MarketStatus.NOTAVAILABLE) {
            throw new NotAvailableVehicleException("Vehicle not orderable.");
        }
        Order orderToInsert = orderMapper.toEntityFrom(orderCreatorDTO);
        Order savedOrder = orderRepository.save(orderToInsert);
        return orderMapper.toResponseDTOFrom(savedOrder);
    }

    public OrderResponseDTO update(long idOrder, OrderCreatorDTO orderCreatorDTO) {
        orderRepository.findById(idOrder).orElseThrow(NotAvailableVehicleException::new);
        Order updatedOrder = orderMapper.toEntityFrom(orderCreatorDTO);
        return orderMapper.toResponseDTOFrom(orderRepository.save(updatedOrder));
    }
}