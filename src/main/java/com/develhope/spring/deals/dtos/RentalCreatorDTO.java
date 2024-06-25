package com.develhope.spring.deals.dtos;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

public class RentalCreatorDTO {

    private LocalDate startDate;
    private LocalDate endDate;
    private boolean paid;
    private long vehicleId;
    private long userId;

    public RentalCreatorDTO() {
    }

    public RentalCreatorDTO(LocalDate startDate, LocalDate endDate, boolean paid, long vehicleId, long userId) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.paid = paid;
        this.vehicleId = vehicleId;
        this.userId = userId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
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
