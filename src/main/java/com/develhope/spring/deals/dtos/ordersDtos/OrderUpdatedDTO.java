package com.develhope.spring.deals.dtos.ordersDtos;

import com.develhope.spring.deals.models.OrderStatus;

public class OrderUpdatedDTO {

    private Long id;
    private boolean downPayment;
    private OrderStatus orderStatus;
    private boolean paid;
    private Long sellerId;

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

    public Long getSellerId() {
        return sellerId;
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

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    //constructors
    public OrderUpdatedDTO(Long id, Long sellerId, boolean downPayment, OrderStatus orderStatus, boolean paid) {
        this.id = id;
        this.sellerId = sellerId;
        this.downPayment = downPayment;
        this.orderStatus = orderStatus;
        this.paid = paid;
    }

    public OrderUpdatedDTO() {
    }
}
