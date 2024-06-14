package com.develhope.spring.deals.dtos;

import com.develhope.spring.deals.models.OrderStatus;
import com.develhope.spring.users.models.User;

import java.util.List;

public class OrderCreatorDTO {
    private boolean downPayment;
    private long vehicleId;
    private long userId;
    private OrderStatus orderStatus;
    private boolean paid;
    private List<User> sellers;

    public OrderCreatorDTO() {
    }

    public OrderCreatorDTO(boolean downPayment, long vehicleId, long userId, OrderStatus orderStatus, boolean paid, List<User> sellers) {
        this.downPayment = downPayment;
        this.vehicleId = vehicleId;
        this.userId = userId;
        this.orderStatus = orderStatus;
        this.paid = paid;
        this.sellers = sellers;
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
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public List<User> getSellers() {
        return sellers;
    }

    public void setSellers(List<User> sellers) {
        this.sellers = sellers;
    }
}
