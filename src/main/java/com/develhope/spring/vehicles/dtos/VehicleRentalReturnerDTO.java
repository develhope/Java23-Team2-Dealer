package com.develhope.spring.vehicles.dtos;

import com.develhope.spring.vehicles.vehicleEnums.Colors;
import com.develhope.spring.vehicles.vehicleEnums.Gears;
import com.develhope.spring.vehicles.vehicleEnums.VehicleType;
import org.apache.catalina.Engine;

public class VehicleRentalReturnerDTO {

    private long id;

    private VehicleType vehicleType;

    private String brand;

    private String model;

    private Colors color;

    private Gears gear;

    private String engine;

    public VehicleRentalReturnerDTO() {
    }

    public VehicleRentalReturnerDTO(long id, VehicleType vehicleType, String brand, String model, Colors color, Gears gear, String engine) {
        this.id = id;
        this.vehicleType = vehicleType;
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.gear = gear;
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

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }
}
