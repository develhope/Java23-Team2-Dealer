package com.develhope.spring.vehicles.optionals.models;

import com.develhope.spring.vehicles.optionals.enums.TypeOfRim;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "Rim")
public class AlloyWheels {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(nullable = false)
    private BigDecimal price;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private TypeOfRim type;

    //Empty builder
    public AlloyWheels() {
    }

    //Full builder
    public AlloyWheels(long id, BigDecimal price) {
        this.id = id;
        this.price = price;
    }

    //Getter
    public long getId() {
        return id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    //Setter
    public void setId(long id) {
        this.id = id;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
