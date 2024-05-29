package com.develhope.spring.vehicles.optionals.models;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "Rim")
public class AllowWeels implements Optionals {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(nullable = false)
    private BigDecimal price;

    private boolean chrome;
    private boolean alloyWheels;
    private boolean color;

    //Empty builder
    public AllowWeels(){}

    //Full builder
    public AllowWeels(long id, BigDecimal price, boolean chrome, boolean alloy, boolean color) {
        this.id = id;
        this.price = price;
        this.chrome = chrome;
        this.alloyWheels = alloy;
        this.color = color;
    }
    //Getter

    public long getId() {
        return id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public boolean isChrome() {
        return chrome;
    }

    public boolean isAlloyWheels() {
        return alloyWheels;
    }

    public boolean isColor() {
        return color;
    }

    //Setter

    public void setId(long id) {
        this.id = id;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setChrome(boolean chrome) {
        this.chrome = chrome;
    }

    public void setAlloyWheels(boolean alloyWheels) {
        this.alloyWheels = alloyWheels;
    }

    public void setColor(boolean color) {
        this.color = color;
    }
}
