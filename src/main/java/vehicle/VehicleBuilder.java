package vehicle;

import vehicle.vehicleEnums.*;
import vehicle.optionals.doors.Doors;
import vehicle.optionals.informatic.Informatic;
import vehicle.optionals.seats.Seats;
import vehicle.optionals.wheels.Wheels;
import vehicle.optionals.windows.Windows;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class VehicleBuilder {
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


    public Doors getDoors() {
        return doors;
    }

    public Informatic getInformatics() {
        return informatics;
    }

    public Seats getSeats() {
        return seats;
    }

    public Wheels getWheels() {
        return wheels;
    }

    public Windows getWindows() {
        return windows;
    }

    public BigDecimal getDiscountedPrice() {
        return discountedPrice;
    }

    public int getId() {
        return id;
    }

    public boolean isDiscountFlag() {
        return discountFlag;
    }

    public BigDecimal getOriginalPrice() {
        return originalPrice.setScale(2, RoundingMode.HALF_EVEN);
    }

    public Colors getColor() {
        return color;
    }

    public Gears getGear() {
        return gear;
    }

    public int getDisplacement() {
        return displacement;
    }

    public int getPower() {
        return power;
    }

    public int getRegistrationYear() {
        return registrationYear;
    }

    public MarketStatus getMarketStatus() {
        return marketStatus;
    }

    public MotorPowerSupply getPowerSupply() {
        return powerSupply;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public UsedFlag getUsedFlag() {
        return usedFlag;
    }

    public VehicleBuilder setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public VehicleBuilder setDiscountedPrice(BigDecimal discountedPrice) {
        this.discountedPrice = discountedPrice;
        return this;
    }

    public VehicleBuilder setDoors(Doors doors) {
        this.doors = doors;
        return this;
    }

    public VehicleBuilder setInformatics(Informatic informatics) {
        this.informatics = informatics;
        return this;
    }

    public VehicleBuilder setOriginalPrice(BigDecimal originalPrice) {
        this.originalPrice = originalPrice;
        return this;
    }

    public VehicleBuilder setSeats(Seats seats) {
        this.seats = seats;
        return this;
    }

    public VehicleBuilder setWheels(Wheels wheels) {
        this.wheels = wheels;
        return this;
    }

    public VehicleBuilder setWindows(Windows windows) {
        this.windows = windows;
        return this;
    }

    public VehicleBuilder setDiscountFlag(boolean discountFlag) {
        this.discountFlag = discountFlag;
        return this;
    }

    public VehicleBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public VehicleBuilder setColor(Colors color) {
        this.color = color;
        return this;
    }

    public VehicleBuilder setDisplacement(int displacement) {
        this.displacement = displacement;
        return this;
    }

    public VehicleBuilder setGear(Gears gear) {
        this.gear = gear;
        return this;
    }

    public VehicleBuilder setMarketStatus(MarketStatus marketStatus) {
        this.marketStatus = marketStatus;
        return this;
    }

    public VehicleBuilder setModel(String model) {
        this.model = model;
        return this;
    }

    public VehicleBuilder setPower(int power) {
        this.power = power;
        return this;
    }

    public VehicleBuilder setPowerSupply(MotorPowerSupply powerSupply) {
        this.powerSupply = powerSupply;
        return this;
    }

    public VehicleBuilder setOriginalPrice(double originalPrice) {
        this.originalPrice = BigDecimal.valueOf(originalPrice).setScale(2, RoundingMode.HALF_EVEN);
        return this;
    }

    public VehicleBuilder setRegistrationYear(int registrationYear) {
        this.registrationYear = registrationYear;
        return this;
    }

    public VehicleBuilder setUsedFlag(UsedFlag usedFlag) {
        this.usedFlag = usedFlag;
        return this;
    }

    public VehicleBuilder(String brand, String model, double originalPrice, int id) {
        this.brand = brand;
        this.model = model;
        this.originalPrice = BigDecimal.valueOf(originalPrice).setScale(2, RoundingMode.HALF_EVEN);
        this.id = id;
        discountedPrice = this.originalPrice;
        discountFlag = false;
    }

    public Vehicle build() {
        return new Vehicle(this);
    }

    @Override
    public String toString() {
        return "VehicleBuilder{" +
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
