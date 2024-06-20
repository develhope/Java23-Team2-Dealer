package com.develhope.spring.vehicles.dtos;

public class VehicleFilterDTO {

    private final String id;

    private final String vehicleType;

    private final String brand;

    private final String displacement;

    private final String displacementOver;

    private final String displacementBelow;

    private final String color;

    private final String power;

    private final String gear;

    private final String year;

    private final String yearOver;

    private final String yearBelow;

    private final String powerSupply;

    private final String priceOver;

    private final String priceBelow;

    private final String usedFlag;

    private final String marketStatus;

    public VehicleFilterDTO(
            String id,
            String vehicleType,
            String brand,
            String displacement,
            String displacementOver,
            String displacementBelow,
            String color,
            String power,
            String gear,
            String year,
            String yearOver,
            String yearBelow,
            String powerSupply,
            String priceOver,
            String priceBelow,
            String usedFlag,
            String marketStatus
    ) {
        this.id = id;
        this.vehicleType = vehicleType;
        this.brand = brand;
        this.displacement = displacement;
        this.displacementOver = displacementOver;
        this.displacementBelow = displacementBelow;
        this.color = color;
        this.power = power;
        this.gear = gear;
        this.year = year;
        this.yearBelow = yearBelow;
        this.yearOver = yearOver;
        this.powerSupply = powerSupply;
        this.priceOver = priceOver;
        this.priceBelow = priceBelow;
        this.usedFlag = usedFlag;
        this.marketStatus = marketStatus;
    }

    public String getDisplacementOver() {
        return displacementOver;
    }

    public String getDisplacementBelow() {
        return displacementBelow;
    }

    public String getId() {
        return id;
    }

    public String getMarketStatus() {
        return marketStatus;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public String getBrand() {
        return brand;
    }

    public String getDisplacement() {
        return displacement;
    }

    public String getColor() {
        return color;
    }

    public String getPower() {
        return power;
    }

    public String getGear() {
        return gear;
    }

    public String getYear() {
        return year;
    }

    public String getYearOver() {
        return yearOver;
    }

    public String getYearBelow() {
        return yearBelow;
    }

    public String getPowerSupply() {
        return powerSupply;
    }

    public String getPriceOver() {
        return priceOver;
    }

    public String getPriceBelow() {
        return priceBelow;
    }

    public String getUsedFlag() {
        return usedFlag;
    }

    public String DTOToString() {
        StringBuilder searchString = new StringBuilder();
        if (this.id != null) searchString.append("id:").append(getId()).append(",");
        if (this.vehicleType != null) searchString.append("vehicleType:").append(getVehicleType()).append(",");
        if (this.brand != null) searchString.append("brand:").append(getBrand()).append(",");
        if (this.gear != null) searchString.append("gear:").append(getGear()).append(",");
        if (this.color != null) searchString.append("color:").append(getColor()).append(",");
        if (this.displacement != null) searchString.append("displacement:").append(getDisplacement()).append(",");
        if (this.displacementOver != null) searchString.append("displacement>").append(getDisplacementOver()).append(",");
        if (this.displacementBelow != null) searchString.append("displacement<").append(getDisplacementBelow()).append(",");
        if (this.power != null) searchString.append("power:").append(getPower()).append(",");
        if (this.powerSupply != null) searchString.append("powerSupply:").append(getPowerSupply()).append(",");
        if (this.priceBelow != null) searchString.append("price<").append(getPriceBelow()).append(",");
        if (this.priceOver != null) searchString.append("price>").append(getPriceOver()).append(",");
        if (this.year != null) searchString.append("registrationYear:").append(getYear()).append(",");
        if (this.yearOver != null) searchString.append("registrationYear>").append(getYearOver()).append(",");
        if (this.yearBelow != null) searchString.append("registrationYear<").append(getYearBelow()).append(",");
        if (this.usedFlag != null) searchString.append("usedFlag:").append(getUsedFlag()).append(",");
        if (this.marketStatus != null) searchString.append("marketStatus:").append(getMarketStatus()).append(",");

        return searchString.toString();
    }
}
