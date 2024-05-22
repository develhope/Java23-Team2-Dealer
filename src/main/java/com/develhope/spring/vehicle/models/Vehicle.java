package com.develhope.spring.vehicle.models;


import com.develhope.spring.vehicle.ExcessiveParameterException;
import com.develhope.spring.vehicle.vehicleEnums.*;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Entity
@Table(name = "vehicles")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private KindOfVehicle type;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private int displacement;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private Colors color;

    @Column(nullable = false)
    private int power;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private Gears gear;

    @Column(nullable = false)
    private int registrationYear;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private MotorPowerSupply powerSupply;

    @Column(nullable = false)
    private BigDecimal originalPrice;
    @Column
    private BigDecimal discountedPrice;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private UsedFlag usedFlag;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private MarketStatus marketStatus;

    @Column
    private boolean discountFlag;

    @Column(nullable = false)
    private String engine;

    //Getters
    public boolean getDiscountFlag() {
        return discountFlag;
    }

    public String getEngine() {
        return engine;
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

    public KindOfVehicle getType() {
        return type;
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

    public long getId() {
        return id;
    }

    public Vehicle(){}

    //Costruttori
    protected Vehicle(VehicleBuilder builder) {
        this.type = builder.getType();
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
        this.engine = builder.getEngine();
    }

    public static VehicleBuilder builder(KindOfVehicle type, String brand, String model, double price, long id) {
        return new VehicleBuilder(type, brand, model, price, id);
    }

    /**
     * Calcola il prezzo scontato e modifica la variabile discountedPrice.
     * Inserisce un double che viene convertito internamente in un BigDecimal.
     *
     * @param discountPercentage è la percentuale di sconto che si desidera applicare
     * @throws ExcessiveParameterException se la percentuale inserita è fuori dai limiti 0 e 100
     */

    public void calculateDiscount(double discountPercentage) throws ExcessiveParameterException {
        if (discountPercentage > 100 || discountPercentage < 0) {
            throw new ExcessiveParameterException("The discount percentage must be comprehended between 0 and 100");
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
        } catch (ExcessiveParameterException e) {
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
        return "Vehicle{" + "brand= '" + brand + '\'' + "\nmodel= '" + model + '\'' + "\ndisplacement= " + displacement + "\ncolor= " + color + "\npower= " + power + "\ngear= " + gear + "\nregistrationYear= " + registrationYear + "\npowerSupply= " + powerSupply + "\noriginalPrice= " + originalPrice + "\ndiscountedPrice= " + discountedPrice + "\nusedFlag= " + usedFlag + "\nmarketStatus= " + marketStatus + "\ndiscountFlag= " + discountFlag + "\nid= " + id + '}';
    }
}