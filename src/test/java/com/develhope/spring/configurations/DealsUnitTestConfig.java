package com.develhope.spring.configurations;

import com.develhope.spring.deals.dtos.ordersDtos.OrderCreatorDTO;
import com.develhope.spring.deals.models.Order;
import com.develhope.spring.deals.models.OrderStatus;
import com.develhope.spring.users.dtos.UserOrderReturnerDTO;
import com.develhope.spring.users.models.User;
import com.develhope.spring.vehicles.dtos.VehicleOrderReturnerDTO;
import com.develhope.spring.vehicles.models.Vehicle;
import com.develhope.spring.vehicles.vehicleEnums.Colors;
import com.develhope.spring.vehicles.vehicleEnums.UsedFlag;
import com.develhope.spring.vehicles.vehicleEnums.VehicleType;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DealsUnitTestConfig {

    public static final long DEFAULT_ID = 1;
    public static final long DEFAULT_ADMIN_ID = 2;

    public static final OrderCreatorDTO DEFAULT_ORDER_CREATOR_DTO = new OrderCreatorDTO(
            true,
            1,
            1,
            OrderStatus.PAID,
            true
    );
    public static final Order DEFAULT_ORDER = new Order(1);

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
    public static final User DEFAULT_USER = new User(1);

    public static final Order DEFAULT_ORDER_ID = new Order();
    public static final UserOrderReturnerDTO DEFAULT_USER_ORDER_RETURNER_DTO = new UserOrderReturnerDTO();
}
