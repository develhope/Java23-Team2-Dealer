package com.develhope.spring.vehicle.models;

import com.develhope.spring.model.vehicle.optionals.Optionals;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Entity
@Table(name = "traction_control")
public class TractionControl implements Optionals {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private BigDecimal price;

    public TractionControl() {
    }

    public TractionControl(BigDecimal price) {
        this.price = price.setScale(2, RoundingMode.HALF_EVEN);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price.setScale(2, RoundingMode.HALF_EVEN);
    }

    public void setPrice(BigDecimal price) {
        this.price = price.setScale(2, RoundingMode.HALF_EVEN);
    }

    @Override
    public String toString() {
        return "TractionControl{" +
                "id=" + id +
                ", price=" + price +
                '}';
    }
}