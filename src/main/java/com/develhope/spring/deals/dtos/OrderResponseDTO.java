package com.develhope.spring.deals.dtos;

import com.develhope.spring.deals.models.OrderStatus;
import com.develhope.spring.vehicles.dtos.VehicleOrderReturnerDTO;

public class OrderResponseDTO {

    private long id;
    private boolean downPayment;
    private VehicleOrderReturnerDTO vehicle;
    private long sellerId;
    private long userId;
    private OrderStatus orderStatus;
    private boolean paid;

    public OrderResponseDTO() {
    }

    public OrderResponseDTO(long id, boolean downPayment, VehicleOrderReturnerDTO vehicle, long sellerId, long userId, OrderStatus orderStatus, boolean paid) {
        this.id = id;
        this.downPayment = downPayment;
        this.vehicle = vehicle;
        this.sellerId = sellerId;
        this.userId = userId;
        this.orderStatus = orderStatus;
        this.paid = paid;
    }

    public boolean isDownPayment() {
        return downPayment;
    }

    public void setDownPayment(boolean downPayment) {
        this.downPayment = downPayment;
    }

    public VehicleOrderReturnerDTO getVehicle() {
        return vehicle;
    }

    public void setVehicle(VehicleOrderReturnerDTO vehicle) {
        this.vehicle = vehicle;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public long getSellerId() {
        return sellerId;
    }

    public void setSellerId(long sellerId) {
        this.sellerId = sellerId;
    }
}
