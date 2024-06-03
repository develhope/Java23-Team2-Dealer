package com.develhope.spring.deals.models;

import com.develhope.spring.users.models.User;
import com.develhope.spring.vehicles.models.Vehicle;
import jakarta.persistence.*;


@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean downPayment;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    private boolean isPaid;

    @ManyToOne
    private Vehicle vehicle;

    @ManyToOne
    @JoinColumn
    private User user;


    public Order() {
    }

    public Order(boolean downPayment, OrderStatus orderStatus, Vehicle vehicle) {
        this.downPayment = downPayment;
        this.orderStatus = orderStatus;
        this.vehicle = vehicle;
    }

    public Order(long id, long vehicleId, boolean downPayment, OrderStatus orderStatus, boolean isPaid, Vehicle vehicle) {
        this.id = id;
        this.downPayment = downPayment;
        this.orderStatus = orderStatus;
        this.isPaid = isPaid;
        this.vehicle = vehicle;
    }


    public boolean isDownPayment() {
        return downPayment;
    }

    public void setDownPayment(boolean downPayment) {
        this.downPayment = downPayment;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

}
