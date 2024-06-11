package com.develhope.spring.vehicles.vehicleOptionals.models;
import com.develhope.spring.vehicles.vehicleOptionals.enums.RimType;
import jakarta.persistence.*;
import org.springframework.data.util.Optionals;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Entity
@Table(name = "Rims")
public class Rims implements Optionals {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private BigDecimal price;
    private RimType rimType;

    public Rims(){}

    public Rims(long id, BigDecimal price , RimType rimType) {
        this.id = id;
        this.price = price.setScale(2 , RoundingMode.HALF_EVEN);
        this.rimType = rimType;

    }

    public RimType getRimType() {
        return rimType;
    }

    public void setRimType(RimType rimType) {
        this.rimType = rimType;
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
        this.price = price.setScale(2, RoundingMode.HALF_EVEN);
    }
}