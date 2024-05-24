package com.develhope.spring.vehicles.optionals.models;

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
    public void setPrice(BigDecimal price) {
        this.price = price.setScale(2, RoundingMode.HALF_EVEN);
    }

    public void setId(long id) {
        this.id = id;
    }

    //Constructors

    public AirConditioner() {
    }

    public AirConditioner(long id, BigDecimal price) {
        this.id = id;
        this.price = price.setScale(2,RoundingMode.HALF_EVEN);
    }
}
