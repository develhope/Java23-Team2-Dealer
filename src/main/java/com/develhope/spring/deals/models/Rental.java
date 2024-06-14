package com.develhope.spring.deals.models;

import com.develhope.spring.users.models.User;
import com.develhope.spring.vehicles.models.Vehicle;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

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
    private BigDecimal dailyCost;


    private BigDecimal totalCost;

    @Column(nullable = false)
    private boolean paid;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Vehicle vehicle;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY)
    private List<User> sellers;

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public boolean isPaid() {
        return paid;
    }

    public Rental(LocalDate startDate, LocalDate endDate, BigDecimal dailyCost, BigDecimal totalCost,
                  boolean paid, Vehicle vehicle, long id, User user, List<User> sellers) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.dailyCost = dailyCost;
        this.totalCost = totalCost;
        this.paid = paid;
        this.vehicle = vehicle;
        this.user = user;
        this.sellers = sellers;
    }
    public Rental(long id){
        this.id = id;
    }

    public List<User> getSellers() {
        return sellers;
    }

    public void setSellers(List<User> sellers) {
        this.sellers = sellers;
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

    public BigDecimal getDailyCost() {
        return dailyCost;
    }

    public void setDailyCost(BigDecimal dailyCost) {
        this.dailyCost = dailyCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
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
}

