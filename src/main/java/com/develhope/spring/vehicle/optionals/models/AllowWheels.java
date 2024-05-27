package com.develhope.spring.vehicle.optionals.models;

import jakarta.persistence.*;

@Entity
@Table(name = "Rim")
public class AllowWheels {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private boolean chromiumPlated;
    private boolean alloyWheels;
    private boolean colored;
    public AllowWheels(){}
    public AllowWheels(long id, boolean chromiumPlated, boolean ilLega, boolean colored) {
        this.id = id;
        this.chromiumPlated = chromiumPlated;
        this.alloyWheels = ilLega;
        this.colored = colored;
    }

    public long getId() {
        return id;
    }

    public boolean isChromiumPlated() {
        return chromiumPlated;
    }

    public boolean isIlLega() {
        return alloyWheels;
    }

    public boolean isColored() {
        return colored;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setChromiumPlated(boolean chromiumPlated) {
        this.chromiumPlated = chromiumPlated;
    }

    public void setIlLega(boolean ilLega) {
        this.alloyWheels = ilLega;
    }

    public void setColored(boolean colored) {
        this.colored = colored;
    }
}