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

    @Column(nullable = false)
    private boolean downPayment;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Column(nullable = false)
    private boolean isPaid;

    @ManyToOne(fetch = FetchType.LAZY)
    private Vehicle vehicle;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public Order() {
    }

    public Order(Long id) {
        this.id = id;
    }

    public Order(long id, long vehicleId, boolean downPayment, OrderStatus orderStatus, boolean isPaid, Vehicle vehicle, User user) {
        this.id = id;
        this.downPayment = downPayment;
        this.orderStatus = orderStatus;
        this.isPaid = isPaid;
        this.vehicle = vehicle;
        this.user = user;
    }

    public Long getId() {
        return id;
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
