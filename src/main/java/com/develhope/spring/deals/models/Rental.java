package com.develhope.spring.deals.models;

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
    private BigDecimal dailyCost;
    @Column(nullable = false)
    private BigDecimal totalCost;
    @Column(nullable = false)
    private boolean paid;
    @OneToOne(fetch = FetchType.EAGER)
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

    public Rental(LocalDate startDate, LocalDate endDate, BigDecimal dailyCost, BigDecimal totalCost, boolean paid, Vehicle vehicle, long id) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.dailyCost = dailyCost;
        this.totalCost = totalCost;
        this.paid = paid;
        this.vehicle = vehicle;
        this.id = id;
    }

    public Rental(LocalDate startDate, LocalDate endDate, BigDecimal dailyCost, int vehicleId, boolean b) {
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

