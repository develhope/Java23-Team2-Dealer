package com.develhope.spring.vehicles.dtos;

import com.develhope.spring.vehicles.vehicleEnums.Colors;
import com.develhope.spring.vehicles.vehicleEnums.UsedFlag;
import com.develhope.spring.vehicles.vehicleEnums.VehicleType;

import java.math.BigDecimal;

public class VehicleOrderReturnerDTO {

    private long id;
    private VehicleType vehicleType;
    private String brand;
    private String model;
    private Colors colors;
    private BigDecimal price;
    private UsedFlag usedFlag;
    private String engine;

    public VehicleOrderReturnerDTO() {
    }

    public VehicleOrderReturnerDTO(long id, VehicleType vehicleType, String brand, String model, Colors colors, BigDecimal price, UsedFlag usedFlag, String engine) {
        this.id = id;
        this.vehicleType = vehicleType;
        this.brand = brand;
        this.model = model;
        this.colors = colors;
        this.price = price;
        this.usedFlag = usedFlag;
        this.engine = engine;
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

    public Colors getColors() {
        return colors;
    }

    public void setColors(Colors colors) {
        this.colors = colors;
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

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }
}
