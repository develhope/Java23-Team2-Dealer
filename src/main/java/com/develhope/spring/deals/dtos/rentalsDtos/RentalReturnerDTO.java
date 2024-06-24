package com.develhope.spring.deals.dtos.rentalsDtos;

import com.develhope.spring.users.dtos.BuyerRentalReturnerDto;
import com.develhope.spring.vehicles.dtos.VehicleRentalReturnerDTO;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

public class RentalReturnerDTO {
    private long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal dailyCost;
    private BigDecimal totalCost;
    private boolean paid;
    private VehicleRentalReturnerDTO vehicle;
    private BuyerRentalReturnerDto buyer;

    public RentalReturnerDTO() {
    }

    void calculateTotalCost() {
        long rentalDays = startDate.until(endDate).getDays();
        this.totalCost = dailyCost.multiply(BigDecimal.valueOf(rentalDays).setScale(2, RoundingMode.HALF_EVEN));
    }

    public RentalReturnerDTO(long id, LocalDate startDate, LocalDate endDate, BigDecimal dailyCost, boolean paid, VehicleRentalReturnerDTO vehicle, BuyerRentalReturnerDto buyer) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.dailyCost = dailyCost;
        this.paid = paid;
        this.vehicle = vehicle;
        this.buyer = buyer;
        calculateTotalCost();
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BuyerRentalReturnerDto getBuyer() {
        return buyer;
    }

    public void setBuyer(BuyerRentalReturnerDto buyer) {
        this.buyer = buyer;
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

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    public BigDecimal getDailyCost() {
        return dailyCost;
    }

    public void setDailyCost(BigDecimal dailyCost) {
        this.dailyCost = dailyCost;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public VehicleRentalReturnerDTO getVehicle() {
        return vehicle;
    }

    public void setVehicle(VehicleRentalReturnerDTO vehicle) {
        this.vehicle = vehicle;
    }
}