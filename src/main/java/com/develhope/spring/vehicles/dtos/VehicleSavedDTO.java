package com.develhope.spring.vehicles.dtos;

import com.develhope.spring.vehicles.vehicleEnums.Colors;
import com.develhope.spring.vehicles.vehicleEnums.MarketStatus;
import com.develhope.spring.vehicles.vehicleEnums.UsedFlag;
import com.develhope.spring.vehicles.vehicleEnums.VehicleType;

import java.math.BigDecimal;

public class VehicleSavedDTO {

    private long id;

    private VehicleType vehicleType;

    private String brand;

    private String model;

    private Colors color;

    private BigDecimal price;

    private UsedFlag usedFlag;

    private MarketStatus marketStatus;

    public VehicleSavedDTO() {
    }

    public VehicleSavedDTO(long id, VehicleType vehicleType, String brand, String model, Colors color, BigDecimal price, UsedFlag usedFlag, MarketStatus marketStatus) {
        this.id = id;
        this.vehicleType = vehicleType;
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.price = price;
        this.usedFlag = usedFlag;
        this.marketStatus = marketStatus;
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

    public Colors getColor() {
        return color;
    }

    public void setColor(Colors color) {
        this.color = color;
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
}
