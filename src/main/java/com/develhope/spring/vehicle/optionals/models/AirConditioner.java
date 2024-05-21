package com.develhope.spring.vehicle.optionals.models;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Entity
@Table(name = "air_conditioner")
public class AirConditioner implements Optionals {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private BigDecimal price;

    //getter
    public BigDecimal getPrice() {
        return price;
    }

    public long getId() {
        return id;
    }

    //setter
    public void setPrice(double price) {
        this.price = BigDecimal.valueOf(price).setScale(2, RoundingMode.HALF_EVEN);
    }

    public AirConditioner() {
    }

    public AirConditioner(long id, double price) {
        this.id = id;
        this.price = BigDecimal.valueOf(price).setScale(2,RoundingMode.HALF_EVEN);
    }
}
