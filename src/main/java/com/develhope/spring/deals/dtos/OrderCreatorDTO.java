package com.develhope.spring.deals.dtos;

import com.develhope.spring.deals.models.OrderStatus;

public class OrderCreatorDTO {
    private boolean downPayment;
    private long vehicleId;
    private long userId;
    private OrderStatus orderStatus;
    private boolean isPaid;

    public OrderCreatorDTO() {
    }

    public OrderCreatorDTO(boolean downPayment, long vehicleId, long userId, OrderStatus orderStatus, boolean isPaid) {
        this.downPayment = downPayment;
        this.vehicleId = vehicleId;
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

    public long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(long vehicleId) {
        this.vehicleId = vehicleId;
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
