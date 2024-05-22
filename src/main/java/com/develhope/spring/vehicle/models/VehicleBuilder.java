package com.develhope.spring.vehicle.models;

import com.develhope.spring.vehicle.vehicleEnums.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class VehicleBuilder {
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
    private long id;
    private String engine;

    private KindOfVehicle type;

    //Getter
    public BigDecimal getDiscountedPrice() {
        return discountedPrice;
    }

    public long getId() {
        return id;
    }

    public String getEngine() {
        return engine;
    }

    public boolean getDiscountFlag() {
        return discountFlag;
    }

    public BigDecimal getOriginalPrice() {
        return originalPrice.setScale(2, RoundingMode.HALF_EVEN);
    }

    public Colors getColor() {
        return color;
    }

    public Gears getGear() {
        return gear;
    }

    public KindOfVehicle getType() {
        return type;
    }

    public int getDisplacement() {
        return displacement;
    }

    public int getPower() {
        return power;
    }

    public int getRegistrationYear() {
        return registrationYear;
    }

    public MarketStatus getMarketStatus() {
        return marketStatus;
    }

    public MotorPowerSupply getPowerSupply() {
        return powerSupply;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public UsedFlag getUsedFlag() {
        return usedFlag;
    }

    //Setter
    public VehicleBuilder setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public VehicleBuilder setEngine(String engine) {
        this.engine = engine;
        return this;
    }

    public VehicleBuilder setType(KindOfVehicle type) {
        this.type = type;
        return this;
    }

    public VehicleBuilder setDiscountedPrice(BigDecimal discountedPrice) {
        this.discountedPrice = discountedPrice;
        return this;
    }

    public VehicleBuilder setOriginalPrice(BigDecimal originalPrice) {
        this.originalPrice = originalPrice;
        return this;
    }

    public VehicleBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public VehicleBuilder setColor(Colors color) {
        this.color = color;
        return this;
    }

    public VehicleBuilder setDisplacement(int displacement) {
        this.displacement = displacement;
        return this;
    }

    public VehicleBuilder setGear(Gears gear) {
        this.gear = gear;
        return this;
    }

    public VehicleBuilder setMarketStatus(MarketStatus marketStatus) {
        this.marketStatus = marketStatus;
        return this;
    }

    public VehicleBuilder setModel(String model) {
        this.model = model;
        return this;
    }

    public VehicleBuilder setPower(int power) {
        this.power = power;
        return this;
    }

    public VehicleBuilder setPowerSupply(MotorPowerSupply powerSupply) {
        this.powerSupply = powerSupply;
        return this;
    }

    public VehicleBuilder setOriginalPrice(double originalPrice) {
        this.originalPrice = BigDecimal.valueOf(originalPrice).setScale(2, RoundingMode.HALF_EVEN);
        return this;
    }

    public VehicleBuilder setRegistrationYear(int registrationYear) {
        this.registrationYear = registrationYear;
        return this;
    }

    public VehicleBuilder setUsedFlag(UsedFlag usedFlag) {
        this.usedFlag = usedFlag;
        return this;
    }

    //Costruttori
    public VehicleBuilder(KindOfVehicle type, String brand, String model, double originalPrice, long id) {
        this.type = type;
        this.brand = brand;
        this.model = model;
        this.originalPrice = BigDecimal.valueOf(originalPrice).setScale(2, RoundingMode.HALF_EVEN);
        this.id = id;
        discountedPrice = this.originalPrice;
    }

    public Vehicle build() {
        return new Vehicle(this);
    }
}
