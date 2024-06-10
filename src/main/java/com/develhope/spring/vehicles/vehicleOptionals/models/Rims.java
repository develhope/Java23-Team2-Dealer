package com.develhope.spring.vehicles.vehicleOptionals.models;

import aj.org.objectweb.asm.Opcodes;
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

    public Rims(){}

    public Rims(long id, BigDecimal price) {
        this.id = id;
        this.price = price.setScale(2 , RoundingMode.HALF_EVEN);
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