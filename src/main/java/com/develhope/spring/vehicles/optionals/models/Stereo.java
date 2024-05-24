package com.develhope.spring.vehicles.optionals.models;

import com.develhope.spring.vehicles.optionals.enums.TypeOfStereos;
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
    @Column(nullable = false, unique = true)
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


    public void setId(long id) {
        this.id = id;
    }

    public void setType(TypeOfStereos type) {
        this.type = type;
    }

    public void setPrice(BigDecimal price) {
        this.price = price.setScale(2, RoundingMode.HALF_EVEN);
    }

    //Constructors

    public Stereo() {
    }

    public Stereo(long id, TypeOfStereos type, BigDecimal price) {
        this.id = id;
        this.type = type;
        this.price = price.setScale(2, RoundingMode.HALF_EVEN);
    }
}
