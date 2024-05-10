package vehicle;

import vehicle.vehicleEnums.*;
import vehicle.optionals.doors.Doors;
import vehicle.optionals.informatic.Informatic;
import vehicle.optionals.seats.Seats;
import vehicle.optionals.wheels.Wheels;
import vehicle.optionals.windows.Windows;

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
    private Doors doors;
    private Informatic informatics;
    private Seats seats;
    private Wheels wheels;
    private Windows windows;


    public boolean isDiscountFlag() {
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

    public Wheels getWheels() {
        return wheels;
    }

    public Informatic getInformatics() {
        return informatics;
    }

    public Windows getWindows() {
        return windows;
    }

    public Seats getSeats() {
        return seats;
    }

    public Doors getDoors() {
        return doors;
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
        this.discountFlag = builder.isDiscountFlag();
        this.doors = builder.getDoors();
        this.informatics = builder.getInformatics();
        this.seats = builder.getSeats();
        this.wheels = builder.getWheels();
        this.windows = builder.getWindows();
    }

    public static VehicleBuilder builder(String brand, String model, double price, int id) {
        return new VehicleBuilder(brand, model, price, id);
    }

    /**
     * Calcola il prezzo scontato e modifica la variabile discountedPrice.
     *
     * @param discountPercentage è la percentuale di sconto che si desidera applicare
     * @throws IllegalArgumentException se la percentuale inserita è fuori dai limiti 0 e 100
     */

    protected void calculateDiscount(double discountPercentage) {
        if (discountPercentage > 100 || discountPercentage < 0) {
            throw new IllegalArgumentException("The discount percentage must be comprehended between 0 and 100");
        }
        BigDecimal discountRate = BigDecimal.valueOf(discountPercentage / 100).setScale(2, RoundingMode.HALF_EVEN);
        BigDecimal removedPrice = originalPrice.multiply(discountRate).setScale(2, RoundingMode.HALF_EVEN);
        discountedPrice = originalPrice.subtract(removedPrice).setScale(2, RoundingMode.HALF_EVEN);
    }

    /**
     * Permette di decidere se attivare uno sconto e di scegliere di quanto scontare il prodotto.
     *
     * @param discountPercentage è la percentuale di sconto che si desidera applicare.
     */
    public void activateDiscount(double discountPercentage) {
        discountFlag = true;
        try {
            calculateDiscount(discountPercentage);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Permette di rimuovere lo sconto e fa tornare il prezzo scontato come l'originale
     */
    public void removeDiscount() {
        discountFlag = false;
        discountedPrice = getOriginalPrice();
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
                ", originalPrice=" + originalPrice +
                ", discountedPrice=" + discountedPrice +
                ", usedFlag=" + usedFlag +
                ", marketStatus=" + marketStatus +
                ", discountFlag=" + discountFlag +
                ", id=" + id +
                ", doors=" + doors +
                ", informatics=" + informatics +
                ", seats=" + seats +
                ", wheels=" + wheels +
                ", windows=" + windows +
                '}';
    }
}