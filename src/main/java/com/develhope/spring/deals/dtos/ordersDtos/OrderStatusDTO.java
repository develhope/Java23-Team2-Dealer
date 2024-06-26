package com.develhope.spring.deals.dtos.ordersDtos;

import com.develhope.spring.deals.models.OrderStatus;

public class OrderStatusDTO {

    private OrderStatus orderStatus;

    public OrderStatusDTO() {
    }

    public OrderStatusDTO(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}
