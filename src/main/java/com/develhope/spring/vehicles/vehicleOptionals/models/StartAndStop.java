package com.develhope.spring.vehicles.vehicleOptionals.models;

import com.develhope.spring.vehicles.models.Vehicle;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Entity
@Table(name = "Start_And_Stop")
public class StartAndStop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private BigDecimal price;
    @OneToOne
    private Vehicle vehicle;

    public StartAndStop() {
    }

    public StartAndStop(long id, BigDecimal price, Vehicle vehicle) {
        this.id = id;
        this.price = price.setScale(2, RoundingMode.HALF_EVEN);
        this.vehicle = vehicle;
    }

    //Getter
    public long getId() {
        return id;
    }

    public BigDecimal getPrice() {
        return price.setScale(2, RoundingMode.HALF_EVEN);
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    //Setter
    public void setId(long id) {
        this.id = id;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}

