package com.develhope.spring.deals.services;


import com.develhope.spring.deals.components.mappers.OrderMapper;
import com.develhope.spring.deals.dtos.ordersDtos.OrderCreatorDTO;
import com.develhope.spring.deals.dtos.ordersDtos.OrderResponseDTO;
import com.develhope.spring.deals.dtos.ordersDtos.OrderUpdatedDTO;
import com.develhope.spring.deals.models.Order;
import com.develhope.spring.deals.repositories.OrderRepository;
import com.develhope.spring.deals.responseStatus.NotAvailableVehicleException;
import com.develhope.spring.users.models.User;
import com.develhope.spring.users.repositories.UserRepository;
import com.develhope.spring.vehicles.models.Vehicle;
import com.develhope.spring.vehicles.repositories.VehicleRepository;
import com.develhope.spring.vehicles.vehicleEnums.MarketStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        Optional<Vehicle> vehicle = vehicleRepository.findById(orderCreatorDTO.getVehicleId());
        if (vehicle.isPresent() && vehicle.get().getMarketStatus() == MarketStatus.NOTAVAILABLE) {
            throw new NotAvailableVehicleException("Vehicle not orderable.");
        }
    }

    void checkIfOrderExists(long orderId, User userDetails) {
        if (orderRepository.findByIdAndUserId(orderId, userDetails.getId()).isEmpty()) {
            throw new NoSuchElementException("No order found with these IDs");
        }
    }

    public OrderUpdatedDTO update(long orderId, OrderCreatorDTO orderCreatorDTO) {
        checkValidVehicleMarketStatus(orderCreatorDTO);
        Order orderToUpdate = orderRepository.findById(orderId).orElseThrow(() -> new NoSuchElementException("No order found with this ID"));
        Optional<Vehicle> newVehicle = vehicleRepository.findById(orderCreatorDTO.getVehicleId());
        User seller = null;
        if(orderCreatorDTO.getSellerId()!=null){
            seller = userRepository.findById(orderCreatorDTO.getSellerId()).orElseThrow(() -> new NoSuchElementException("No seller found with this ID"));
        }
        orderToUpdate.setDownPayment(orderCreatorDTO.isDownPayment());
        orderToUpdate.setOrderStatus(orderCreatorDTO.getOrderStatus());
        orderToUpdate.setPaid(orderCreatorDTO.isPaid());
        newVehicle.ifPresent(orderToUpdate::setVehicle);
        orderToUpdate.setSeller(seller);
        Order newOrderSaved = orderRepository.save(orderToUpdate);
        return orderMapper.toOrderUpdatedDTO(newOrderSaved);
    }

    public void deleteBy(long orderId) {
        orderRepository.deleteById(orderId);
    }

    public void deleteOrderByIdAndUserId(long orderId, User userDetails) {
        checkIfOrderExists(orderId, userDetails);
        orderRepository.deleteById(orderId);
    }

    public boolean checkOrderId(long orderId, User userDetails) {
        return orderRepository.findByIdAndUserId(orderId, userDetails.getId()).isPresent();
    }

    void addReference(Order order) {
        User buyer = order.getUser();
        long buyerId = buyer.getId();
        User seller = order.getSeller();
        long sellerId = seller.getId();
        User referencedUser = (userRepository.findById(buyerId)
                .orElseThrow(() -> new NoSuchElementException("No user found with this ID")));
        referencedUser.getOrders().add(order);
        if (seller != null && sellerId != 0) {
            User referencedSeller = (userRepository.findById(sellerId)
                    .orElseThrow(() -> new NoSuchElementException("No seller found with this ID")));
            referencedSeller.getOrders().add(order);
        }
    }
}