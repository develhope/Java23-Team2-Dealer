package com.develhope.spring.deals.dtos;

import com.develhope.spring.users.dtos.BuyerRentalReturnerDto;
import com.develhope.spring.users.models.User;
import com.develhope.spring.vehicles.dtos.VehicleRentalReturnerDTO;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

/**
 * This is the DTO that you get as response when you create a Rental
 * not usable for GET requests.
 */
public class RentalResponseDTO {
    private long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal dailyCost;
    private BigDecimal totalCost;
    private boolean paid;
    private VehicleRentalReturnerDTO vehicle;
    private BuyerRentalReturnerDto buyer;
    private List<User> sellers;

    public RentalResponseDTO() {
    }

    void calculateTotalCost() {
        long rentalDays = startDate.until(endDate).getDays();
        this.totalCost = dailyCost.multiply(BigDecimal.valueOf(rentalDays).setScale(2, RoundingMode.HALF_EVEN));
    }

    public RentalResponseDTO(long id, LocalDate startDate, LocalDate endDate, BigDecimal dailyCost, boolean paid, VehicleRentalReturnerDTO vehicle, BuyerRentalReturnerDto buyer, List<User> sellers) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.dailyCost = dailyCost;
        this.paid = paid;
        this.vehicle = vehicle;
        this.buyer = buyer;
        calculateTotalCost();
        this.id = id;
        this.sellers = sellers;
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

    public List<User> getSellers() {
        return sellers;
    }

    public void setSellers(List<User> sellers) {
        this.sellers = sellers;
    }
}