package com.develhope.spring.deals.dtos;

import com.develhope.spring.deals.models.OrderStatus;
import com.develhope.spring.vehicles.dtos.VehicleOrderReturnerDTO;

public class OrderResponseDTO {

    private boolean downPayment;
    private VehicleOrderReturnerDTO vehicle;
    private long userId;
    private OrderStatus orderStatus;
    private boolean isPaid;

    public OrderResponseDTO() {
    }

    public OrderResponseDTO(boolean downPayment, VehicleOrderReturnerDTO vehicle, long userId, OrderStatus orderStatus, boolean isPaid) {
        this.downPayment = downPayment;
        this.vehicle = vehicle;
        this.userId = userId;
        this.orderStatus = orderStatus;
        this.isPaid = isPaid;
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
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }
}
