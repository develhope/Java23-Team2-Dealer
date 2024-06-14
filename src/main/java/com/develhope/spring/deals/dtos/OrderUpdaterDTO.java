package com.develhope.spring.deals.dtos;

import com.develhope.spring.deals.models.OrderStatus;
import com.develhope.spring.users.models.User;

import java.util.List;

public class OrderUpdaterDTO {
    private boolean downPayment;
    private OrderStatus orderStatus;
    private boolean paid;

    public OrderUpdaterDTO() {
    }

    public OrderUpdaterDTO(boolean downPayment, OrderStatus orderStatus, boolean paid) {
        this.downPayment = downPayment;
        this.orderStatus = orderStatus;
        this.paid = paid;
    }

    public boolean isDownPayment() {
        return downPayment;
    }

    public void setDownPayment(boolean downPayment) {
        this.downPayment = downPayment;
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
}
