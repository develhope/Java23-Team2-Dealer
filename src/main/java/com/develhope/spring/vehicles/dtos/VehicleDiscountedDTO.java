package com.develhope.spring.vehicles.dtos;

import com.develhope.spring.vehicles.vehicleEnums.*;

import java.math.BigDecimal;

public class VehicleDiscountedDTO {
    private long id;
    private VehicleType vehicleType;
    private String brand;
    private String model;
    private int registrationYear;
    private BigDecimal discountedPrice;
    private BigDecimal dailyCost;
    private boolean discountFlag;



    public VehicleDiscountedDTO() {
    }

    public VehicleDiscountedDTO(long id) {
    }

    public VehicleDiscountedDTO(long id, VehicleType vehicleType, String brand, String model, int registrationYear,
                                BigDecimal discountedPrice, BigDecimal dailyCost, boolean discountFlag) {
        this.id = id;
        this.vehicleType = vehicleType;
        this.brand = brand;
        this.model = model;
        this.registrationYear = registrationYear;
        this.discountedPrice = discountedPrice;
        this.dailyCost = dailyCost;
        this.discountFlag = discountFlag;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getRegistrationYear() {
        return registrationYear;
    }

    public void setRegistrationYear(int registrationYear) {
        this.registrationYear = registrationYear;
    }

    public BigDecimal getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(BigDecimal discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

    public BigDecimal getDailyCost() {
        return dailyCost;
    }

    public void setDailyCost(BigDecimal dailyCost) {
        this.dailyCost = dailyCost;
    }

    public boolean isDiscountFlag() {
        return discountFlag;
    }

    public void setDiscountFlag(boolean discountFlag) {
        this.discountFlag = discountFlag;
    }
}
