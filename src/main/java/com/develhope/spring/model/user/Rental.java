package com.develhope.spring.model.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

@Entity
public class Rental {
    @Column(nullable = false)
    private LocalDate startDate;
    @Column(nullable = false)
    private LocalDate endDate;
    @Column(nullable = false)
    private BigDecimal dailyCost;
    @Column(nullable = false)
    private BigDecimal totalCost;
    @Column(nullable = false)
    private boolean paid;

    //it was decided to use vehicle (object) but instead of using
    // Vehicle as object is created private int vehicleId;
    // passed to the constructor
    // handled the same way in the Order class of User
    private int vehicleId;


    public Rental(LocalDate startDate, LocalDate endDate, BigDecimal dailyCost, int vehicleId, boolean paid) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.dailyCost = dailyCost;
        this.vehicleId = vehicleId;
        calculateTotalCost();
        this.paid = paid;
    }

    private void calculateTotalCost() {
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

    public Rental(LocalDate startDate, LocalDate endDate, BigDecimal dailyCost, BigDecimal totalCost, boolean paid, int vehicleId) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.dailyCost = dailyCost;
        this.totalCost = totalCost;
        this.paid = paid;
        this.vehicleId = vehicleId;
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

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }
}

