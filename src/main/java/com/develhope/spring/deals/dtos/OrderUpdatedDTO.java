package com.develhope.spring.deals.dtos;

import com.develhope.spring.deals.models.OrderStatus;

public class OrderUpdatedDTO {

    private Long id;
    private boolean downPayment;
    private OrderStatus orderStatus;
    private boolean isPaid;

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
        return isPaid;
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
        isPaid = paid;
    }



    //constructors
    public OrderUpdatedDTO(Long id, boolean downPayment, OrderStatus orderStatus, boolean isPaid) {
        this.id = id;
        this.downPayment = downPayment;
        this.orderStatus = orderStatus;
        this.isPaid = isPaid;
    }

    public OrderUpdatedDTO(){}
}
