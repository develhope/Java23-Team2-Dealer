package com.develhope.spring.deals.dtos;

public class OrderCreatorDTO {
    private boolean downPayment;
    private long vehicleId;
    private long userId;
    private Long adminId;

    public OrderCreatorDTO() {}

    public OrderCreatorDTO(boolean downPayment, long vehicleId, long userId, Long adminId) {
        this.downPayment = downPayment;
        this.vehicleId = vehicleId;
        this.userId = userId;
        this.adminId = adminId;
    }

    public boolean isDownPayment() {
        return downPayment;
    }

    public void setDownPayment(boolean downPayment) {
        this.downPayment = downPayment;
    }

    public long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }
}

