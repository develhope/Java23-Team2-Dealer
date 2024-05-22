package com.develhope.spring.vehicle.vehicleEnums;

public enum KindOfVehicle {
    CAR,
    MOTO,
    SCOOTER,
    VAN,
    TRUCK,
    NOT_DEFINED

    public static KindOfVehicle convertFromString(String type) {
        return switch (type.toLowerCase()) {
            case "car" -> KindOfVehicle.CAR;
            case "motorbike" -> KindOfVehicle.MOTO;
            case "scooter" -> KindOfVehicle.SCOOTER;
            case "van" -> KindOfVehicle.VAN;
            case "truck" -> KindOfVehicle.TRUCK;
            default -> KindOfVehicle.NOT_DEFINED;
        };
    }
}
