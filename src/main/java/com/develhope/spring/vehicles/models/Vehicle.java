package com.develhope.spring.vehicles.models;

import com.develhope.spring.vehicles.responseStatus.ExcessiveParameterException;
import com.develhope.spring.vehicles.vehicleEnums.*;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Entity
@Table(name = "vehicles")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private String model;

    @Column
    private int displacement;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Colors color;

    @Column(nullable = false)
    private int power;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gears gear;

    @Column(nullable = false)
    private int registrationYear;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MotorPowerSupply powerSupply;

    @Column(nullable = false)
    private BigDecimal price;

    private BigDecimal dailyCost;

    @Column
    private BigDecimal discountedPrice;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UsedFlag usedFlag;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MarketStatus marketStatus;

    @Column
    private boolean discountFlag;

    @Column(nullable = false)
    private String engine;

    //Getters

    public BigDecimal getDailyCost() {
        return dailyCost;
    }

    public boolean isDiscountFlag() {
        return discountFlag;
    }

    public String getEngine() {
        return engine;
    }

    public BigDecimal getDiscountedPrice() {
        return discountedPrice;
    }

    public UsedFlag getUsedFlag() {
        return usedFlag;
    }

    public String getModel() {
        return model;
    }

    public String getBrand() {
        return brand;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public MotorPowerSupply getPowerSupply() {
        return powerSupply;
    }

    public MarketStatus getMarketStatus() {
        return marketStatus;
    }

    public int getRegistrationYear() {
        return registrationYear;
    }

    public int getPower() {
        return power;
    }

    public int getDisplacement() {
        return displacement;
    }

    public Gears getGear() {
        return gear;
    }

    public Colors getColor() {
        return color;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public long getId() {
        return id;
    }
    // Setter
    public void setId(long id) {
        this.id = id;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setDisplacement(int displacement) {
        this.displacement = displacement;
    }

    public void setColor(Colors color) {
        this.color = color;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public void setGear(Gears gear) {
        this.gear = gear;
    }

    public void setRegistrationYear(int registrationYear) {
        this.registrationYear = registrationYear;
    }

    public void setPowerSupply(MotorPowerSupply powerSupply) {
        this.powerSupply = powerSupply;
    }

    public void setPrice(BigDecimal price) {
        this.price = price.setScale(2, RoundingMode.HALF_EVEN);
    }

    public void setDailyCost(BigDecimal dailyCost) {
        this.dailyCost = dailyCost;
    }

    public void setDiscountedPrice(BigDecimal discountedPrice) {
        this.discountedPrice = discountedPrice.setScale(2, RoundingMode.HALF_EVEN);
    }

    public void setUsedFlag(UsedFlag usedFlag) {
        this.usedFlag = usedFlag;
    }

    public void setMarketStatus(MarketStatus marketStatus) {
        this.marketStatus = marketStatus;
    }

    public void setDiscountFlag(boolean discountFlag) {
        this.discountFlag = discountFlag;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }


    //Costruttori
    public Vehicle() {
    }

    public Vehicle(long id) {
        this.id = id;
    }

    public Vehicle(long id, VehicleType vehicleType, String brand, String model, int displacement, Colors color, int power,
                   Gears gear, int registrationYear, MotorPowerSupply powerSupply, BigDecimal price, BigDecimal dailyCost,
                   UsedFlag usedFlag, MarketStatus marketStatus, String engine) {
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
        this.price = price.setScale(2, RoundingMode.HALF_EVEN);
        this.dailyCost = dailyCost.setScale(2, RoundingMode.HALF_EVEN);
        this.discountedPrice = price.setScale(2, RoundingMode.HALF_EVEN);
        this.usedFlag = usedFlag;
        this.marketStatus = marketStatus;
        this.discountFlag = false;
        this.engine = engine;
    }
}