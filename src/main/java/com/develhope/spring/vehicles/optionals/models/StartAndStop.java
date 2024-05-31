package com.develhope.spring.vehicles.optionals.models;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Entity
@Table(name = "Start&Stop")
public class StartAndStop {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(nullable = false)
    private BigDecimal price;
    private boolean installed;
//Empty builder
    public StartAndStop() {
    }
//Full builder
    public StartAndStop(long id, BigDecimal price, boolean installed) {
        this.id = id;
        this.price = price.setScale(2 , RoundingMode.HALF_EVEN);
        this.installed = installed;
    }
    //Getter

    public long getId() {
        return id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public boolean isInstalled() {
        return installed;
    }
//Setter
    public void setId(long id) {
        this.id = id;
    }

    public void setPrice(BigDecimal price) {
        this.price = price.setScale(2,RoundingMode.HALF_EVEN);
    }

    public void setInstalled(boolean installed) {
        this.installed = installed;
    }
}
