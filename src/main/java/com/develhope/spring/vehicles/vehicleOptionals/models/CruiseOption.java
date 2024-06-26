package com.develhope.spring.vehicles.vehicleOptionals.models;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Entity
@Table(name = "Cruise_Option")
public class CruiseOption implements VehicleOptionals {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private BigDecimal price;

    //Getter


    public BigDecimal getPrice() {
        return price;
    }

    public long getId() {
        return id;
    }

    //Setter


    public void setId(long id) {
        this.id = id;
    }

    public void setPrice(BigDecimal price) {
        this.price = price.setScale(2, RoundingMode.HALF_EVEN);
    }

    //Constructor

    public CruiseOption() {
    }

    public CruiseOption(long id, BigDecimal price) {
        this.id = id;
        this.price= price.setScale(2,RoundingMode.HALF_EVEN);
    }
}
