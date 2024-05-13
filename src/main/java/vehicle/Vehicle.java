package vehicle;

import vehicle.vehicleEnums.*;


import java.math.BigDecimal;
import java.math.RoundingMode;

public class Vehicle {
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
    private int id;
    private Object doors;
    private Object informatics;
    private Object seats;
    private Object wheels;
    private Object windows;


    public boolean getDiscountFlag() {
        return discountFlag;
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

    public BigDecimal getOriginalPrice() {
        return originalPrice;
    }

    public int getId() {
        return id;
    }


    protected Vehicle(VehicleBuilder builder) {
        this.brand = builder.getBrand();
        this.marketStatus = builder.getMarketStatus();
        this.usedFlag = builder.getUsedFlag();
        this.originalPrice = builder.getOriginalPrice();
        this.discountedPrice = builder.getDiscountedPrice();
        this.powerSupply = builder.getPowerSupply();
        this.registrationYear = builder.getRegistrationYear();
        this.gear = builder.getGear();
        this.power = builder.getPower();
        this.color = builder.getColor();
        this.displacement = builder.getDisplacement();
        this.id = builder.getId();
        this.model = builder.getModel();
        this.discountFlag = builder.getDiscountFlag();
    }

    public static VehicleBuilder builder(String brand, String model, double price, int id) {
        return new VehicleBuilder(brand, model, price, id);
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "brand= '" + brand + '\'' +
                "\nmodel= '" + model + '\'' +
                "\ndisplacement= " + displacement +
                "\ncolor= " + color +
                "\npower= " + power +
                "\ngear= " + gear +
                "\nregistrationYear= " + registrationYear +
                "\npowerSupply= " + powerSupply +
                "\noriginalPrice= " + originalPrice +
                "\ndiscountedPrice= " + discountedPrice +
                "\nusedFlag= " + usedFlag +
                "\nmarketStatus= " + marketStatus +
                "\ndiscountFlag= " + discountFlag +
                "\nid= " + id +
                '}';
    }
}