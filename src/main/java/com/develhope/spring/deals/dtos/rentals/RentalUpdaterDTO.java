package com.develhope.spring.deals.dtos.rentals;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

public class RentalUpdaterDTO {
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal dailyCost;
    private BigDecimal totalCost;
    private boolean paid;
    private long vehicleId;

    public RentalUpdaterDTO(LocalDate startDate, LocalDate endDate, BigDecimal dailyCost, boolean paid, long vehicleId) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.dailyCost = dailyCost.setScale(2, RoundingMode.HALF_EVEN);
        calculateTotalCost();
        this.paid = paid;
        this.vehicleId = vehicleId;
    }

    void calculateTotalCost() {
        long rentalDays = startDate.until(endDate).getDays();
        this.totalCost = dailyCost.multiply(BigDecimal.valueOf(rentalDays));
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
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
        this.dailyCost = dailyCost.setScale(2, RoundingMode.HALF_EVEN);
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
