package com.develhope.spring.vehicles.dtos;

import com.develhope.spring.vehicles.vehicleEnums.*;

import java.math.BigDecimal;

public class VehicleReworkedDTO {

    private long id;
    private VehicleType vehicleType;
    private String brand;
    private String model;
    private int displacement;
    private Colors color;
    private int power;
    private Gears gear;
    private int registrationYear;
    private MotorPowerSupply powerSupply;
    private BigDecimal price;
    private BigDecimal dailyCost;
    private UsedFlag usedFlag;
    private MarketStatus marketStatus;
    private String engine;

    public VehicleReworkedDTO() {
    }

    public VehicleReworkedDTO(long id) {
    }

    public VehicleReworkedDTO(long id, VehicleType vehicleType, String brand, String model, int displacement, Colors color,
                              int power, Gears gear, int registrationYear, MotorPowerSupply powerSupply,
                              BigDecimal price, BigDecimal dailyCost, UsedFlag usedFlag, MarketStatus marketStatus, String engine) {

        this.id = id;
        this.vehicleType = vehicleType;
        this.brand = brand;
        this.model = model;
        this.displacement = displacement;
        this.color = color;
        this.power = power;
        this.gear = gear;
        this.registrationYear = registrationYear;
        this.powerSupply = powerSupply;
        this.price = price;
        this.dailyCost = dailyCost;
        this.usedFlag = usedFlag;
        this.marketStatus = marketStatus;
        this.engine = engine;
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

    public int getDisplacement() {
        return displacement;
    }

    public void setDisplacement(int displacement) {
        this.displacement = displacement;
    }

    public Colors getColor() {
        return color;
    }

    public void setColor(Colors color) {
        this.color = color;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public Gears getGear() {
        return gear;
    }

    public void setGear(Gears gear) {
        this.gear = gear;
    }

    public int getRegistrationYear() {
        return registrationYear;
    }

    public void setRegistrationYear(int registrationYear) {
        this.registrationYear = registrationYear;
    }

    public MotorPowerSupply getPowerSupply() {
        return powerSupply;
    }

    public void setPowerSupply(MotorPowerSupply powerSupply) {
        this.powerSupply = powerSupply;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public UsedFlag getUsedFlag() {
        return usedFlag;
    }

    public void setUsedFlag(UsedFlag usedFlag) {
        this.usedFlag = usedFlag;
    }

    public MarketStatus getMarketStatus() {
        return marketStatus;
    }

    public void setMarketStatus(MarketStatus marketStatus) {
        this.marketStatus = marketStatus;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BigDecimal getDailyCost() {
        return dailyCost;
    }

    public void setDailyCost(BigDecimal dailyCost) {
        this.dailyCost = dailyCost;
    }
}
