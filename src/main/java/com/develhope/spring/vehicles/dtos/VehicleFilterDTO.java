package com.develhope.spring.vehicles.dtos;

public class VehicleFilterDTO {

    private String vehicleType;

    private String brand;

    private String displacement;

    private String color;

    private String power;

    private String gear;

    private String year;

    private String powerSupply;

    private String priceOver;

    private String priceBelow;

    private String usedFlag;

    private String discountedFlag;

    public VehicleFilterDTO(
            String vehicleType,
            String brand,
            String displacement,
            String color,
            String power,
            String gear,
            String year,
            String powerSupply,
            String priceOver,
            String priceBelow,
            String usedFlag,
            String discountedFlag
    ) {
        this.vehicleType = "vehicleType:" + vehicleType;
        this.brand = "brand:" + brand;
        this.displacement = "displacement:" + displacement;
        this.color = "color:" + color;
        this.power = "power:" + power;
        this.gear = "gear:" + gear;
        this.year = "registrationYear:" + year;
        this.powerSupply = "powerSupply:" + powerSupply;
        this.priceOver = "price>" + priceOver;
        this.priceBelow = "price<" + priceBelow;
        this.usedFlag = "usedFlag:" + usedFlag;
        this.discountedFlag = "discountedFlag:" + discountedFlag;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDisplacement() {
        return displacement;
    }

    public void setDisplacement(String displacement) {
        this.displacement = displacement;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getGear() {
        return gear;
    }

    public void setGear(String gear) {
        this.gear = gear;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getPowerSupply() {
        return powerSupply;
    }

    public void setPowerSupply(String powerSupply) {
        this.powerSupply = powerSupply;
    }

    public String getPriceOver() {
        return priceOver;
    }

    public void setPriceOver(String priceOver) {
        this.priceOver = priceOver;
    }

    public String getPriceBelow() {
        return priceBelow;
    }

    public void setPriceBelow(String priceBelow) {
        this.priceBelow = priceBelow;
    }

    public String getUsedFlag() {
        return usedFlag;
    }

    public void setUsedFlag(String usedFlag) {
        this.usedFlag = usedFlag;
    }

    public String getDiscountedFlag() {
        return discountedFlag;
    }

    public void setDiscountedFlag(String discountedFlag) {
        this.discountedFlag = discountedFlag;
    }
}
