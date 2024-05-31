package com.develhope.spring.vehicles.vehicleEnums;

public enum VehicleType {
    CAR,
    MOTO,
    SCOOTER,
    VAN,
    TRUCK,
    NOT_DEFINED;

    public static VehicleType convertFromString(String type) {
        return switch (type.toLowerCase()) {
            case "car" -> VehicleType.CAR;
            case "motorbike" -> VehicleType.MOTO;
            case "scooter" -> VehicleType.SCOOTER;
            case "van" -> VehicleType.VAN;
            case "truck" -> VehicleType.TRUCK;
            default -> VehicleType.NOT_DEFINED;
        };
    }
}
