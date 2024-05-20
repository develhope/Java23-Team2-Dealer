package com.develhope.spring.vehicle.optionals.models;

import com.develhope.spring.vehicle.optionals.enums.TypeOfStereos;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Entity
@Table(name = "stereos")
public class Stereo implements Optionals {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TypeOfStereos type;

    @Column(nullable = false)
    private BigDecimal price;

    //Getters


    public long getId() {
        return id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public TypeOfStereos getType() {
        return type;
    }

    //Setters


    public void setType(TypeOfStereos type) {
        this.type = type;
    }

    public void setPrice(double price) {
        this.price = BigDecimal.valueOf(price).setScale(2, RoundingMode.HALF_EVEN);
    }

    //Constructors

    public Stereo() {
    }

    public Stereo(long id, TypeOfStereos type, double price) {
        this.id = id;
        this.type = type;
        this.price = BigDecimal.valueOf(price).setScale(2, RoundingMode.HALF_EVEN);
    }
}
