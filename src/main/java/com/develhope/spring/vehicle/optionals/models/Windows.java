package com.develhope.spring.vehicle.optionals.models;

import com.develhope.spring.vehicle.optionals.enums.TypeOfWindows;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "windows")
public class Windows implements Optionals {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private TypeOfWindows type;

    @Column(nullable = false)
    private BigDecimal price;

    public Windows() {
    }

    public Windows(long id, TypeOfWindows type, BigDecimal price) {
        this.id = id;
        this.type = type;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public TypeOfWindows getType() {
        return type;
    }

    public void setType(TypeOfWindows type) {
        this.type = type;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
