package com.develhope.spring.vehicles.dtos;

import com.develhope.spring.vehicles.vehicleEnums.Colors;
import com.develhope.spring.vehicles.vehicleEnums.Gears;
import com.develhope.spring.vehicles.vehicleEnums.MotorPowerSupply;
import com.develhope.spring.vehicles.vehicleEnums.VehicleType;

import java.math.BigDecimal;

public class VehicleRentalReturnerDTO {

    private long id;

    private VehicleType vehicleType;

    private String brand;

    private String model;

    private Colors color;

    private Gears gear;

    private MotorPowerSupply motorPowerSupply;

    private BigDecimal dailyCost;

    public VehicleRentalReturnerDTO() {
    }

    public VehicleRentalReturnerDTO(long id,
                                    VehicleType vehicleType,
                                    String brand,
                                    String model,
                                    Colors color,
                                    Gears gear,
                                    MotorPowerSupply motorPowerSupply,
                                    BigDecimal dailyCost
    ) {
        this.id = id;
        this.vehicleType = vehicleType;
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.gear = gear;
        this.motorPowerSupply = motorPowerSupply;
        this.dailyCost = dailyCost;
    }

    public BigDecimal getDailyCost() {
        return dailyCost;
    }

    public VehicleRentalReturnerDTO(long id) {
        this.id = id;
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

    public Gears getGear() {
        return gear;
    }

    public void setGear(Gears gear) {
        this.gear = gear;
    }

    public MotorPowerSupply getMotorPowerSupply() {
        return motorPowerSupply;
    }

    public void setMotorPowerSupply(MotorPowerSupply motorPowerSupply) {
        this.motorPowerSupply = motorPowerSupply;
    }
}
