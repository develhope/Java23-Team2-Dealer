package com.develhope.spring.vehicle.optionals.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Entity
public class Abs implements Optionals{

    @Id
    @GeneratedValue
    private long id;
    private BigDecimal price;

    public Abs(){}

    public Abs(long id, BigDecimal price) {
        this.id = id;
        this.price = price.setScale(2, RoundingMode.HALF_EVEN);;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
