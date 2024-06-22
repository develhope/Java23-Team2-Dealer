package com.develhope.spring.deals.dtos.orders;

import com.develhope.spring.deals.models.OrderStatus;

public class OrderUpdatedDTO {

    private Long id;
    private boolean downPayment;
    private OrderStatus orderStatus;
    private boolean paid;

    //getters
    public Long getId() {
        return id;
    }

    public boolean isDownPayment() {
        return downPayment;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public boolean isPaid() {
        return paid;
    }



    //setters

    public void setId(Long id) {
        this.id = id;
    }

    public void setDownPayment(boolean downPayment) {
        this.downPayment = downPayment;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }



    //constructors
    public OrderUpdatedDTO(Long id, boolean downPayment, OrderStatus orderStatus, boolean paid) {
        this.id = id;
        this.downPayment = downPayment;
        this.orderStatus = orderStatus;
        this.paid = paid;
    }

    public OrderUpdatedDTO(){}
}
