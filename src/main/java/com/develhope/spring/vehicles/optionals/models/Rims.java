package com.develhope.spring.vehicles.optionals.models;

import com.develhope.spring.vehicles.optionals.enums.TypeOfRim;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Entity
@Table(name = "Rim")
public class Rims {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(nullable = false)
    private BigDecimal price;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private TypeOfRim typeOfRim;

    //Empty builder
    public Rims() {
    }

    //Full builder
    public Rims(long id, BigDecimal price) {
        this.id = id;
        this.price = price.setScale(2, RoundingMode.HALF_EVEN);
    }

    //Getter
    public long getId() {
        return id;
    }

    public BigDecimal getPrice() {
        return price.setScale(2 , RoundingMode.HALF_EVEN);
    }

    public TypeOfRim getTypeOfRim() {
        return typeOfRim;
    }

    //Setter
    public void setId(long id) {
        this.id = id;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void RimsType(TypeOfRim typeOfRim) {
        this.typeOfRim = typeOfRim;
    }
}