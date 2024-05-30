package com.develhope.spring.vehicles.dtos;

import com.develhope.spring.vehicles.vehicleEnums.MarketStatus;

public class VehicleStatusDTO {
    private MarketStatus marketStatus;

    public MarketStatus getMarketStatus() {
        return marketStatus;
    }

    public void setMarketStatus(MarketStatus marketStatus) {
        this.marketStatus = marketStatus;
    }
}
