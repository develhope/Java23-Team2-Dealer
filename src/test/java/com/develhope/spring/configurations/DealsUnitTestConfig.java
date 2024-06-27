package com.develhope.spring.configurations;

import com.develhope.spring.deals.dtos.ordersDtos.OrderCreatorDTO;
import com.develhope.spring.deals.dtos.rentalsDtos.RentalCreatorDTO;
import com.develhope.spring.deals.models.Order;
import com.develhope.spring.deals.models.OrderStatus;
import com.develhope.spring.deals.models.Rental;

import com.develhope.spring.users.dtos.UserOrderReturnerDTO;
import com.develhope.spring.users.dtos.UserRentalReturnerDto;
import com.develhope.spring.users.models.User;
import com.develhope.spring.vehicles.dtos.VehicleOrderReturnerDTO;
import com.develhope.spring.vehicles.dtos.VehicleRentalReturnerDTO;
import com.develhope.spring.vehicles.models.Vehicle;
import com.develhope.spring.vehicles.vehicleEnums.Colors;
import com.develhope.spring.vehicles.vehicleEnums.UsedFlag;
import com.develhope.spring.vehicles.vehicleEnums.VehicleType;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DealsUnitTestConfig {


    public static final Order DEFAULT_ORDER_ID = new Order();
    public static final long DEFAULT_ID = 1;
    public static final long DEFAULT_ADMIN_ID = 2;
    public static final User DEFAULT_USER = new User(2);
    public static final User DEFAULT_SELLER = new User(1);
    public static final OrderCreatorDTO DEFAULT_ORDER_CREATOR_DTO = new OrderCreatorDTO(
            true,
            1,
            1L,
            2,
            OrderStatus.PAID,
            true
    );
    public static final Order DEFAULT_ORDER = new Order(
            1,
            true,
            OrderStatus.PAID,
            true,
            DEFAULT_VEHICLE(),
            DEFAULT_USER,
            DEFAULT_SELLER);

    public static final Vehicle DEFAULT_VEHICLE = new Vehicle(1);
    public static final VehicleOrderReturnerDTO DEFAULT_VEHICLE_ORDER_RETURNER_DTO = new VehicleOrderReturnerDTO(
            1,
            VehicleType.CAR,
            "Fiat",
            "Fiorino",
            Colors.WHITE,
            BigDecimal.valueOf(1000).setScale(2, RoundingMode.HALF_EVEN),
            UsedFlag.NEW,
            "Motore"
    );
    public static final UserOrderReturnerDTO DEFAULT_USER_ORDER_RETURNER_DTO = new UserOrderReturnerDTO();

    public static final LocalDate DEFAULT_RENTAL_START_DATE = LocalDate.of(2024, 6, 3);
    public static final LocalDate DEFAULT_RENTAL_END_DATE = LocalDate.of(2024, 6, 6);
    public static final BigDecimal DEFAULT_PRICE = BigDecimal.valueOf(40).setScale(2, RoundingMode.HALF_EVEN);

    public static final RentalCreatorDTO DEFAULT_RENTAL_CREATOR_DTO = new RentalCreatorDTO(
            DEFAULT_RENTAL_START_DATE,
            DEFAULT_RENTAL_END_DATE,
            true,
            1,
            2,
            1);

    public static Vehicle DEFAULT_VEHICLE() {
        Vehicle vehicle = new Vehicle(1);
        vehicle.setDailyCost(DEFAULT_PRICE);
        return vehicle;
    }

    public static final User DEFAULT_USER2 = new User(2);

    public static final Rental DEFAULT_RENTAL = new Rental();

    public static final VehicleRentalReturnerDTO DEFAULT_VEHICLE_RENTAL_RETURNER_DTO = new VehicleRentalReturnerDTO(1);
    public static final UserRentalReturnerDto DEFAULT_BUYER_RENTAL_RETURNER_DTO = new UserRentalReturnerDto(2);
    public static final UserRentalReturnerDto DEFAULT_SELLER_RENTAL_RETURNER_DTO = new UserRentalReturnerDto(1);
    public static final Rental DEFAULT_EXISTING_RENTAL = new Rental(
            LocalDate.of(2024, 6, 7),
            LocalDate.of(2024, 6, 10),
            DEFAULT_VEHICLE().getDailyCost(),
            true,
            DEFAULT_VEHICLE(),
            1,
            DEFAULT_USER,
            DEFAULT_SELLER
    );

    public static final Rental DEFAULT_EXISTING_RENTAL2 = new Rental(
            LocalDate.of(2024, 6, 11),
            LocalDate.of(2024, 6, 14),
            DEFAULT_VEHICLE().getDailyCost(),
            true,
            DEFAULT_VEHICLE(),
            2,
            DEFAULT_USER,
            DEFAULT_SELLER
    );

    public static final Collection<Rental> DEFAULT_EXISTING_RENTALS = new ArrayList<>(List.of(DEFAULT_EXISTING_RENTAL, DEFAULT_EXISTING_RENTAL2));

}
