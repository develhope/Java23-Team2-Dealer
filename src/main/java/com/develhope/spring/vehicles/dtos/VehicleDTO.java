package com.develhope.spring.vehicles.dtos;

import com.develhope.spring.vehicles.vehicleEnums.*;

import java.math.BigDecimal;

public class VehicleDTO {

    private KindOfVehicle type;
    private String brand;
    private String model;
    private int displacement;
    private Colors color;
    private int power;
    private Gears gear;
    private int registrationYear;
    private MotorPowerSupply powerSupply;
    private BigDecimal originalPrice;
    private BigDecimal discountedPrice;
    private UsedFlag usedFlag;
    private MarketStatus marketStatus;
    private boolean discountFlag;
    private String engine;

    public VehicleDTO() {
    }

    public VehicleDTO(KindOfVehicle type, String brand, String model, int displacement, Colors color, int power, Gears gear, int registrationYear, MotorPowerSupply powerSupply, BigDecimal originalPrice, BigDecimal discountedPrice, UsedFlag usedFlag, MarketStatus marketStatus, boolean discountFlag, String engine) {
        this.type = type;
        this.brand = brand;
        this.model = model;
        this.displacement = displacement;
        this.color = color;
        this.power = power;
        this.gear = gear;
        this.registrationYear = registrationYear;
        this.powerSupply = powerSupply;
        this.originalPrice = originalPrice;
        this.discountedPrice = discountedPrice;
        this.usedFlag = usedFlag;
        this.marketStatus = marketStatus;
        this.discountFlag = discountFlag;
        this.engine = engine;
    }

    public KindOfVehicle getType() {
        return type;
    }

    public void setType(KindOfVehicle type) {
        this.type = type;
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

    public BigDecimal getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(BigDecimal originalPrice) {
        this.originalPrice = originalPrice;
    }

    public BigDecimal getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(BigDecimal discountedPrice) {
        this.discountedPrice = discountedPrice;
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

    public boolean isDiscountFlag() {
        return discountFlag;
    }

    public void setDiscountFlag(boolean discountFlag) {
        this.discountFlag = discountFlag;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }


}
