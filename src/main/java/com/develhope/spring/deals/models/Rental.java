package com.develhope.spring.deals.models;

import com.develhope.spring.users.models.User;
import com.develhope.spring.vehicles.models.Vehicle;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

@Entity
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @NotBlank(message = "Start date is mandatory")
    @Column(nullable = false)
    private LocalDate startDate;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @NotBlank(message = "End date is mandatory")
    @Column(nullable = false)
    private LocalDate endDate;

    @NotBlank(message = "Daily Cost is mandatory")
    @Column(nullable = false)
    private BigDecimal dailyCost;

    @Column(nullable = false)
    private BigDecimal totalCost;

    @Column(nullable = false)
    private boolean paid;

    @OneToOne
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    private Vehicle vehicle;

    void calculateTotalCost() {
        long rentalDays = startDate.until(endDate).getDays();
        this.totalCost = dailyCost.multiply(BigDecimal.valueOf(rentalDays).setScale(2, RoundingMode.HALF_EVEN));
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    // Getters
    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public boolean isPaid() {
        return paid;
    }

    public Rental(LocalDate startDate, LocalDate endDate, BigDecimal dailyCost, boolean paid, Vehicle vehicle, long id, User user) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.dailyCost = dailyCost;
        calculateTotalCost();
        this.paid = paid;
        this.vehicle = vehicle;
        this.id = id;
        this.user = user;
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

    public void setId(long id) {
        this.id = id;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}

