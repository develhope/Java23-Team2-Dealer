package com.develhope.spring.deals.dtos;

import com.develhope.spring.vehicles.models.Vehicle;

import java.math.BigDecimal;
import java.time.LocalDate;

public class RentalCreatorDTO {

    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal dailyCost;
    private BigDecimal totalCost;
    private boolean paid;
    private long vehicleId;

    public RentalCreatorDTO(LocalDate startDate, LocalDate endDate, BigDecimal dailyCost, BigDecimal totalCost, boolean paid, long vehicleId) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.dailyCost = dailyCost;
        this.totalCost = totalCost;
        this.paid = paid;
        this.vehicleId = vehicleId;
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

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(long vehicleId) {
        this.vehicleId = vehicleId;
    }
}
