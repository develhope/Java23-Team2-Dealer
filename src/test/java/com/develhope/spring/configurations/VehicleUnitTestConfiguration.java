package com.develhope.spring.configurations;

import com.develhope.spring.users.dtos.UserRegistrationDTO;
import com.develhope.spring.users.models.Roles;
import com.develhope.spring.users.models.User;
import com.develhope.spring.vehicles.dtos.VehicleCreatorDTO;
import com.develhope.spring.vehicles.dtos.VehicleFilterDTO;
import com.develhope.spring.vehicles.models.Vehicle;
import com.develhope.spring.vehicles.vehicleEnums.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class VehicleUnitTestConfiguration {
    public static final Vehicle DEFAULT_VEHICLE = new Vehicle();
    public static final User DEFAULT_USER = new User(1);
    public static final UserRegistrationDTO DEFAULT_USER_USER_CREATOR_DTO = new UserRegistrationDTO(
            "Gabriel",
            "Dello",
            "paneNutella",
            "12345",
            "12345",
            3467789L,
            "hey@itsadmin.com",
            Roles.ADMIN
    );

    public static final VehicleCreatorDTO DEFAULT_VEHICLE_CREATOR_DTO = new VehicleCreatorDTO(
            VehicleType.CAR,
            "Ferrari",
            "Enzo",
            2100,
            Colors.RED,
            3000,
            Gears.MANUAL,
            2004,
            MotorPowerSupply.GASOLINE,
            BigDecimal.valueOf(800000).setScale(2, RoundingMode.HALF_EVEN),
            UsedFlag.USED,
            MarketStatus.AVAILABLE,
            "V8"
    );

    public static Vehicle DEFAULT_VEHICLE () {
        Vehicle vehicle = new Vehicle(1);
        vehicle.setVehicleType(DEFAULT_VEHICLE_CREATOR_DTO.getVehicleType());
        vehicle.setBrand(DEFAULT_VEHICLE_CREATOR_DTO.getBrand());
        vehicle.setModel(DEFAULT_VEHICLE_CREATOR_DTO.getModel());
        vehicle.setDisplacement(DEFAULT_VEHICLE_CREATOR_DTO.getDisplacement());
        vehicle.setColor(DEFAULT_VEHICLE_CREATOR_DTO.getColor());
        vehicle.setPower(DEFAULT_VEHICLE_CREATOR_DTO.getPower());
        vehicle.setGear(DEFAULT_VEHICLE_CREATOR_DTO.getGear());
        vehicle.setRegistrationYear(DEFAULT_VEHICLE_CREATOR_DTO.getRegistrationYear());
        vehicle.setPowerSupply(DEFAULT_VEHICLE_CREATOR_DTO.getPowerSupply());
        vehicle.setPrice(DEFAULT_VEHICLE_CREATOR_DTO.getPrice());
        vehicle.setUsedFlag(DEFAULT_VEHICLE_CREATOR_DTO.getUsedFlag());
        vehicle.setMarketStatus(DEFAULT_VEHICLE_CREATOR_DTO.getMarketStatus());
        vehicle.setEngine(DEFAULT_VEHICLE_CREATOR_DTO.getEngine());
        return vehicle;
    }

    public static VehicleFilterDTO DEFAULT_VEHICLE_FILTER_DTO = new VehicleFilterDTO(
            "1",
            "CAR",
            "Ferrari",
            "2000",
            "1000",
            "3000",
            "RED",
            "2500",
            "MANUAL",
            "2024",
            "2000",
            "2025",
            "GASOLINE",
            "10000",
            "950000",
            "NEW",
            "AVAILABLE"
    );
}
