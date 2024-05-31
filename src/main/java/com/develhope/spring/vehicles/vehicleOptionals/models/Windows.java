package com.develhope.spring.vehicles.vehicleOptionals.models;

import com.develhope.spring.vehicles.vehicleOptionals.enums.WindowsType;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "windows")
public class Windows implements VehicleOptionals {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private WindowsType windowsType;

    @Column(nullable = false)
    private BigDecimal price;

    public Windows() {
    }

    public Windows(long id, WindowsType windowsType, BigDecimal price) {
        this.id = id;
        this.windowsType = windowsType;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public WindowsType getWindowsType() {
        return windowsType;
    }

    public void setWindowsType(WindowsType windowsType) {
        this.windowsType = windowsType;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
