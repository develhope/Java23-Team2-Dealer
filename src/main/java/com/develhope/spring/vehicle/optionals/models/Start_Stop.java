package com.develhope.spring.vehicle.optionals.models;

import jakarta.persistence.*;
import org.springframework.boot.SpringApplication;

import javax.naming.Name;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Entity
@Table(name = "Start&Stop")
public class Start_Stop {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private BigDecimal price;

    public Start_Stop(){};
    public Start_Stop(long id , BigDecimal price){
        this.id = id;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price.setScale(2, RoundingMode.HALF_EVEN);
    }
}
