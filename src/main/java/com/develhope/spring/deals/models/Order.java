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
    @JoinColumn(name = "user_id")
    private User user;


    public Order() {
    }

    public Order(boolean downPayment, OrderStatus orderStatus, Vehicle vehicle) {
        this.downPayment = downPayment;
        this.orderStatus = orderStatus;
        this.vehicle = vehicle;
    }

    public boolean isDownPayment() {
        return downPayment;
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

    public User getUser() {
        return user;
    }

}
