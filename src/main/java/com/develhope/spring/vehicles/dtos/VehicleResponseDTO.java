package com.develhope.spring.vehicles.dtos;

import com.develhope.spring.vehicles.vehicleEnums.*;

import java.math.BigDecimal;

public class VehicleResponseDTO {
    private long id;
    private VehicleType vehicleType;
    private String brand;
    private String model;
    private int registrationYear;
    private MotorPowerSupply powerSupply;
    private BigDecimal price;
    private UsedFlag usedFlag;
    private String engine;

    //Getters

    public long getId() {
        return id;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public int getRegistrationYear() {
        return registrationYear;
    }

    public MotorPowerSupply getPowerSupply() {
        return powerSupply;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public UsedFlag getUsedFlag() {
        return usedFlag;
    }

    public String getEngine() {
        return engine;
    }

    //Setters
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

    public void setRegistrationYear(int registrationYear) {
        this.registrationYear = registrationYear;
    }

    public void setPowerSupply(MotorPowerSupply powerSupply) {
        this.powerSupply = powerSupply;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setUsedFlag(UsedFlag usedFlag) {
        this.usedFlag = usedFlag;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    //Costruttori
    public VehicleResponseDTO(long id, VehicleType vehicleType, String brand, String model, int registrationYear,
                              MotorPowerSupply powerSupply, BigDecimal price, UsedFlag usedFlag, String engine) {
        this.id = id;
        this.vehicleType = vehicleType;
        this.brand = brand;
        this.model = model;
        this.registrationYear = registrationYear;
        this.powerSupply = powerSupply;
        this.price = price;
        this.usedFlag = usedFlag;
        this.engine = engine;
    }

    public VehicleResponseDTO(){}

    public VehicleResponseDTO(long id){}
}
