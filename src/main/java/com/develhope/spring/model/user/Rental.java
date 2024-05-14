package com.develhope.spring.model.user;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Rental {
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal dailyCost;
    private BigDecimal totalCost;
    private boolean paid;

    //it was decided to use vehicle (object) but instead of using
    // Vehicle as object is created private int vehicleId;
    // passed to the constructor
    // handled the same way in the Order class of User
    private int vehicleId;


    public Rental(LocalDate startDate, LocalDate endDate, BigDecimal dailyCost, int vehicleId) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.dailyCost = dailyCost;
        this.vehicleId = vehicleId;
        this.calculateTotalCost();
        this.paid = false;
    }

    private void calculateTotalCost() {
        long rentalDays = startDate.until(endDate).getDays();
        this.totalCost = dailyCost.multiply(BigDecimal.valueOf(rentalDays));
    }

    public void markAsPaid() {
        this.paid = true;
    }

    // Getters
    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public boolean isPaid() {
        return paid;
    }
}
