package vehicle;

import vehicle.enumerators.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collection;

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
    private Collection<Optionals> optionals;
    private UsedFlag usedFlag;
    private MarketStatus marketStatus;
    private boolean discountFlag;
    private int id;

    public VehicleBuilder(String brand, String model, double originalPrice, int id) {
        this.brand = brand;
        this.model = model;
        this.originalPrice = BigDecimal.valueOf(originalPrice).setScale(2, RoundingMode.HALF_EVEN);
        this.id = id;
        discountedPrice = this.originalPrice;
        discountFlag = false;
        optionals = new ArrayList<>();
    }


    public BigDecimal getDiscountedPrice() {
        return discountedPrice;
    }

    public Vehicle build() {
        return new Vehicle(this);
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

    public Collection<Optionals> getOptionals() {
        return optionals;
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

    public VehicleBuilder setOptionals(Collection<Optionals> optionals) {
        this.optionals = optionals;
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
                ", price=" + originalPrice +
                ", optionals=" + optionals +
                ", usedFlag=" + usedFlag +
                ", marketStatus=" + marketStatus +
                ", discountFlag=" + discountFlag +
                ", id=" + id +
                '}';
    }
}
