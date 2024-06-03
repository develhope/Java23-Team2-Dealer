package com.develhope.spring.deals.models;

import com.develhope.spring.vehicles.models.Vehicle;
import jakarta.persistence.*;

/**
 * Represents an order or purchase. This class includes details about
 * the payment status, order status, and identifies the vehicle associated with the order.
 */
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private boolean downPayment;

    @Column(nullable = false)
    private OrderStatus orderStatus;

    @Column(nullable = false)
    private boolean isPaid;
    @OneToOne
    private Vehicle vehicle;

    //costruttori
    public Order(long id, boolean downPayment, OrderStatus orderStatus, boolean isPaid, Vehicle vehicle) {
        this.id = id;
        this.downPayment = downPayment;
        this.isPaid = isPaid;
        this.orderStatus = orderStatus;
        this.vehicle = vehicle;
    }

    public Order() {
    }

    //Setter e getter
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
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

}
