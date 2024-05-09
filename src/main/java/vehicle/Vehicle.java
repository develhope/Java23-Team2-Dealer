package vehicle;

import vehicle.enumerators.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collection;

public class Vehicle {
    private String brand;
    private String model;
    private int displacement;
    private Colors color;
    private int power;
    private Gears gear;
    private int registrationYear;
    private MotorPowerSupply powerSupply;
    private BigDecimal price;
    private Collection<Optionals> optionals;
    private UsedFlag usedFlag;
    private MarketStatus marketStatus;
    private boolean discountFlag;

    public boolean isDiscountFlag() {
        return discountFlag;
    }

    protected Vehicle(VehicleBuilder builder) {
        this.brand = builder.getBrand();
        this.marketStatus = builder.getMarketStatus();
        this.usedFlag = builder.getUsedFlag();
        this.optionals = builder.getOptionals() ;
        this.price = builder.getPrice();
        this.powerSupply = builder.getPowerSupply();
        this.registrationYear = builder.getRegistrationYear();
        this.gear = builder.getGear();
        this.power = builder.getPower();
        this.color = builder.getColor();
        this.displacement = builder.getDisplacement();
        this.model = builder.getModel();
        this.discountFlag = builder.isDiscountFlag();
    }

    public static VehicleBuilder builder(String brand, String model, Colors color, int displacement, int power, Gears gear,
                                  int registrationYear, MotorPowerSupply powerSupply, double price, UsedFlag usedFlag,
                                  MarketStatus marketStatus){
        return new VehicleBuilder(brand,model,color,displacement,power,gear,registrationYear,powerSupply,price,usedFlag,marketStatus);
    }

    protected BigDecimal calculateDiscount(double discountPercentage) {
        if (discountPercentage > 100 || discountPercentage < 0) {
            throw new RuntimeException("The discount percentage must be comprehended between 0 and 100");
        }
        BigDecimal discountRate = BigDecimal.valueOf(discountPercentage / 100).setScale(2, RoundingMode.HALF_EVEN);
        return price.multiply(discountRate).setScale(2, RoundingMode.HALF_EVEN);
    }

    public void activateDiscount(double discountPercentage) {
        discountFlag = true;
        try {
            calculateDiscount(discountPercentage);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", displacement=" + displacement +
                ", color=" + color +
                ", power=" + power +
                ", gear=" + gear +
                ", registrationYear=" + registrationYear +
                ", powerSupply=" + powerSupply +
                ", price=" + price +
                ", optionals=" + optionals +
                ", usedFlag=" + usedFlag +
                ", marketStatus=" + marketStatus +
                ", discountFlag=" + discountFlag +
                '}';
    }


}

