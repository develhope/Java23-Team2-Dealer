package com.develhope.spring.deals.models;

import com.develhope.spring.deals.responseStatus.NotAvailableVehicleException;
import com.develhope.spring.users.models.User;
import com.develhope.spring.vehicles.models.Vehicle;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

@Entity
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @Column(nullable = false)
    private BigDecimal totalCost;

    @Column(nullable = false)
    private boolean paid;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Vehicle vehicle;


    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public boolean isPaid() {
        return paid;
    }

    public Rental(LocalDate startDate, LocalDate endDate, BigDecimal totalCost, boolean paid, Vehicle vehicle, long id, User user) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalCost = totalCost;
        this.paid = paid;
        this.vehicle = vehicle;
        this.id = id;
        this.user = user;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Rental() {
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public long getId() {
        return id;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }
}

