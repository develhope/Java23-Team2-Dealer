package com.develhope.spring.vehicles.optionals.models;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Entity
@Table(name = "abs")
public class Abs implements Optionals{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
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
        this.price = price.setScale(2, RoundingMode.HALF_EVEN);;
    }
}
