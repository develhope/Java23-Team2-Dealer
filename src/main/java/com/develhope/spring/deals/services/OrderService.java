package com.develhope.spring.deals.services;


import com.develhope.spring.deals.dtos.OrderCreatorDTO;
import com.develhope.spring.deals.dtos.OrderResponseDTO;
import com.develhope.spring.deals.models.Order;
import com.develhope.spring.deals.models.OrderMapper;
import com.develhope.spring.deals.models.exceptions.OrderCreationException;
import com.develhope.spring.deals.repositories.OrderRepository;
import com.develhope.spring.deals.responsestatus.NotAvailableVehicleException;
import com.develhope.spring.vehicles.dtos.VehicleCreatorDTO;
import com.develhope.spring.vehicles.models.Vehicle;
import com.develhope.spring.vehicles.repositories.VehicleRepository;
import com.develhope.spring.vehicles.services.VehicleService;
import com.develhope.spring.vehicles.vehicleEnums.MarketStatus;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import javax.swing.text.html.Option;
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
        Optional<Vehicle> vehicleOptional = vehicleRepository.findById(orderCreatorDTO.getVehicleId());
        if (vehicleOptional.isPresent() && vehicleOptional.get().getMarketStatus() == MarketStatus.NOTAVAILABLE) {
            throw new NotAvailableVehicleException("Vehicle not orderable.");
        }
        Order order = orderMapper.toEntityFrom(orderCreatorDTO);
        return orderMapper.toResponseDTOFrom(order);
    }
}