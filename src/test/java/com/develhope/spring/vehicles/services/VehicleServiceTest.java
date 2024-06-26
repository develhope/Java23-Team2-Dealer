package com.develhope.spring.vehicles.services;


import com.develhope.spring.users.models.Roles;
import com.develhope.spring.users.models.User;
import com.develhope.spring.users.repositories.UserRepository;
import com.develhope.spring.vehicles.dtos.*;
import com.develhope.spring.vehicles.models.Vehicle;
import com.develhope.spring.vehicles.repositories.VehicleRepository;
import com.develhope.spring.vehicles.responseStatus.ExcessiveParameterException;
import com.develhope.spring.vehicles.vehicleEnums.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.develhope.spring.configurations.VehicleUnitTestConfiguration.DEFAULT_VEHICLE;
import static com.develhope.spring.configurations.VehicleUnitTestConfiguration.DEFAULT_VEHICLE_CREATOR_DTO;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class VehicleServiceTest {

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Autowired
    private VehicleService vehicleService;

    @MockBean
    private VehicleRepository vehicleRepository;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private Pattern pattern;

    @MockBean
    private Matcher matcher;

    private final VehicleStatusDTO DEFAULT_VEHICLE_STATUS_DTO = new VehicleStatusDTO(MarketStatus.ORDERABLE);

    private final VehicleCreatorDTO DEFAULT_VEHICLE_UPDATE_DTO = new VehicleCreatorDTO(
            VehicleType.CAR,
            "Lamborghini",
            "Marco",
            7000,
            Colors.BLACK,
            9001,
            Gears.AUTOMATIC,
            4002,
            MotorPowerSupply.GPL,
            BigDecimal.valueOf(400000).setScale(2, RoundingMode.HALF_EVEN),
            BigDecimal.valueOf(40).setScale(2, RoundingMode.HALF_EVEN),
            UsedFlag.NEW,
            MarketStatus.ORDERABLE,
            "motore a curvatura"
    );

    private Vehicle DEFAULT_VEHICLE() {
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

    @Test
    void createVehicle_successfulCreationTest() {
        Vehicle vehicle = new Vehicle();
        User admin = new User(1L, "Gabriel", "Dello", "panenutella", "1234", 3467789, "hey@itsadmin.com", Roles.ADMIN);
        when(userRepository.findById(admin.getId()))
                .thenReturn(Optional.of(admin));
        when(vehicleRepository.save(any()))
                .thenReturn(DEFAULT_VEHICLE());
        VehicleSavedDTO result = vehicleService.create(DEFAULT_VEHICLE_CREATOR_DTO);
        VehicleSavedDTO expected = new VehicleSavedDTO(
                DEFAULT_VEHICLE().getId(),
                DEFAULT_VEHICLE().getVehicleType(),
                DEFAULT_VEHICLE().getBrand(),
                DEFAULT_VEHICLE().getModel(),
                DEFAULT_VEHICLE().getColor(),
                DEFAULT_VEHICLE().getPrice(),
                DEFAULT_VEHICLE().getDailyCost(),
                DEFAULT_VEHICLE().getUsedFlag(),
                DEFAULT_VEHICLE().getMarketStatus()
        );
        assertEquals(expected.getBrand(), result.getBrand());
    }

//    @Test
//    void search_returnCorrectVehicleListLength_whenASingleFilterEqualIsPassed() {
//        List<Vehicle> expected = new ArrayList<>();
//        expected.add(DEFAULT_VEHICLE());
//        Pattern pattern = Pattern.compile("(\\p{Punct}?)(\\w+?)(:|<|>)(\\p{Punct}?)(\\w+?)(\\p{Punct}?),");
//        VehicleFilterDTO vehicleFilterDTO = new VehicleFilterDTO(null, "CAR", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
//        VehicleSpecificationsBuilder vehicleSpecificationsBuilder = new VehicleSpecificationsBuilder();
//        Matcher matcher = pattern.matcher(vehicleFilterDTO.DTOToString() + ",");
//        while (matcher.find()) {
//            vehicleSpecificationsBuilder.with(matcher.group(1), matcher.group(2), matcher.group(3), matcher.group(5), matcher.group(4), matcher.group(6));
//        }
//        Specification<Vehicle> spec = vehicleSpecificationsBuilder.build();
//        when(vehicleRepository.findAll(spec))
//                .thenReturn(expected);
//        List<Vehicle> result = vehicleService.search(vehicleFilterDTO);
//        assertEquals(expected.size(), result.size());
//    }

    @Test
    void updateVehicleTest() {
        when(vehicleRepository.findById(1L))
                .thenReturn(Optional.of(DEFAULT_VEHICLE));
        Vehicle updatedVehicle = new Vehicle(
                DEFAULT_VEHICLE.getId(),
                DEFAULT_VEHICLE_UPDATE_DTO.getVehicleType(),
                DEFAULT_VEHICLE_UPDATE_DTO.getBrand(),
                DEFAULT_VEHICLE_UPDATE_DTO.getModel(),
                DEFAULT_VEHICLE_UPDATE_DTO.getDisplacement(),
                DEFAULT_VEHICLE_UPDATE_DTO.getColor(),
                DEFAULT_VEHICLE_UPDATE_DTO.getPower(),
                DEFAULT_VEHICLE_UPDATE_DTO.getGear(),
                DEFAULT_VEHICLE_UPDATE_DTO.getRegistrationYear(),
                DEFAULT_VEHICLE_UPDATE_DTO.getPowerSupply(),
                DEFAULT_VEHICLE_UPDATE_DTO.getPrice(),
                DEFAULT_VEHICLE_UPDATE_DTO.getDailyCost(),
                DEFAULT_VEHICLE_UPDATE_DTO.getUsedFlag(),
                DEFAULT_VEHICLE_UPDATE_DTO.getMarketStatus(),
                DEFAULT_VEHICLE_UPDATE_DTO.getEngine()
        );
        when(vehicleRepository.save(any()))
                .thenReturn(updatedVehicle);

        VehicleReworkedDTO expected = new VehicleReworkedDTO(
                1,
                VehicleType.CAR,
                "Lamborghini",
                "Marco",
                7000,
                Colors.BLACK,
                9001,
                Gears.AUTOMATIC,
                4002,
                MotorPowerSupply.GPL,
                BigDecimal.valueOf(99999).setScale(2, RoundingMode.HALF_EVEN),
                BigDecimal.valueOf(40).setScale(2, RoundingMode.HALF_EVEN),
                UsedFlag.NEW,
                MarketStatus.ORDERABLE,
                "motore a curvatura"
        );
        VehicleReworkedDTO result = vehicleService.update(1L, DEFAULT_VEHICLE_UPDATE_DTO);
        assertEquals(expected.getDisplacement(), result.getDisplacement());
    }

    @Test
    void updateVehicleTest_checkIfIdIsUnchanged() {
        when(vehicleRepository.findById(1L))
                .thenReturn(Optional.of(DEFAULT_VEHICLE));
        Vehicle updatedVehicle = new Vehicle(
                DEFAULT_VEHICLE.getId(),
                DEFAULT_VEHICLE_UPDATE_DTO.getVehicleType(),
                DEFAULT_VEHICLE_UPDATE_DTO.getBrand(),
                DEFAULT_VEHICLE_UPDATE_DTO.getModel(),
                DEFAULT_VEHICLE_UPDATE_DTO.getDisplacement(),
                DEFAULT_VEHICLE_UPDATE_DTO.getColor(),
                DEFAULT_VEHICLE_UPDATE_DTO.getPower(),
                DEFAULT_VEHICLE_UPDATE_DTO.getGear(),
                DEFAULT_VEHICLE_UPDATE_DTO.getRegistrationYear(),
                DEFAULT_VEHICLE_UPDATE_DTO.getPowerSupply(),
                DEFAULT_VEHICLE_UPDATE_DTO.getPrice(),
                DEFAULT_VEHICLE_UPDATE_DTO.getDailyCost(),
                DEFAULT_VEHICLE_UPDATE_DTO.getUsedFlag(),
                DEFAULT_VEHICLE_UPDATE_DTO.getMarketStatus(),
                DEFAULT_VEHICLE_UPDATE_DTO.getEngine()
        );
        when(vehicleRepository.save(any()))
                .thenReturn(updatedVehicle);

        VehicleReworkedDTO expected = new VehicleReworkedDTO(
                1,
                VehicleType.CAR,
                "Lamborghini",
                "Marco",
                7000,
                Colors.BLACK,
                9001,
                Gears.AUTOMATIC,
                4002,
                MotorPowerSupply.GPL,
                BigDecimal.valueOf(99999).setScale(2, RoundingMode.HALF_EVEN),
                BigDecimal.valueOf(40).setScale(2, RoundingMode.HALF_EVEN),
                UsedFlag.NEW,
                MarketStatus.ORDERABLE,
                "motore a curvatura"
        );
        VehicleReworkedDTO result = vehicleService.update(1, DEFAULT_VEHICLE_UPDATE_DTO);
        assertEquals(expected.getId(), result.getId());
    }

    @Test
    void updateVehicleStatusTest() {
        when(vehicleRepository.findById(1L))
                .thenReturn(Optional.of(DEFAULT_VEHICLE));
        Vehicle updatedVehicle = new Vehicle(
                DEFAULT_VEHICLE.getId(),
                DEFAULT_VEHICLE.getVehicleType(),
                DEFAULT_VEHICLE.getBrand(),
                DEFAULT_VEHICLE.getModel(),
                DEFAULT_VEHICLE.getDisplacement(),
                DEFAULT_VEHICLE.getColor(),
                DEFAULT_VEHICLE.getPower(),
                DEFAULT_VEHICLE.getGear(),
                DEFAULT_VEHICLE.getRegistrationYear(),
                DEFAULT_VEHICLE.getPowerSupply(),
                DEFAULT_VEHICLE.getPrice(),
                DEFAULT_VEHICLE_UPDATE_DTO.getDailyCost(),
                DEFAULT_VEHICLE.getUsedFlag(),
                DEFAULT_VEHICLE_STATUS_DTO.getMarketStatus(),
                DEFAULT_VEHICLE.getEngine()
        );
        when(vehicleRepository.save(any()))
                .thenReturn(updatedVehicle);
        VehicleReworkedDTO expected = new VehicleReworkedDTO(
                1,
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
                BigDecimal.valueOf(40).setScale(2, RoundingMode.HALF_EVEN),
                UsedFlag.USED,
                MarketStatus.ORDERABLE,
                "V8");
        VehicleReworkedDTO result = vehicleService.updateStatus(1L, DEFAULT_VEHICLE_STATUS_DTO);
        assertEquals(result.getMarketStatus(), expected.getMarketStatus());
    }

    @Test
    void updateVehicleStatusTest_otherThingsAreUnchanged() {
        when(vehicleRepository.findById(1L))
                .thenReturn(Optional.of(DEFAULT_VEHICLE));
        Vehicle updatedVehicle = new Vehicle(
                DEFAULT_VEHICLE.getId(),
                DEFAULT_VEHICLE.getVehicleType(),
                DEFAULT_VEHICLE.getBrand(),
                DEFAULT_VEHICLE.getModel(),
                DEFAULT_VEHICLE.getDisplacement(),
                DEFAULT_VEHICLE.getColor(),
                DEFAULT_VEHICLE.getPower(),
                DEFAULT_VEHICLE.getGear(),
                DEFAULT_VEHICLE.getRegistrationYear(),
                DEFAULT_VEHICLE.getPowerSupply(),
                DEFAULT_VEHICLE.getPrice(),
                DEFAULT_VEHICLE_UPDATE_DTO.getDailyCost(),
                DEFAULT_VEHICLE.getUsedFlag(),
                DEFAULT_VEHICLE_STATUS_DTO.getMarketStatus(),
                DEFAULT_VEHICLE.getEngine()
        );
        when(vehicleRepository.save(any()))
                .thenReturn(updatedVehicle);
        VehicleReworkedDTO expected = new VehicleReworkedDTO(
                1,
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
                BigDecimal.valueOf(40).setScale(2, RoundingMode.HALF_EVEN),
                UsedFlag.USED,
                MarketStatus.ORDERABLE,
                "V8");
        VehicleReworkedDTO result = vehicleService.updateStatus(1L, DEFAULT_VEHICLE_STATUS_DTO);
        assertEquals(result.getId(), expected.getId());
    }

    @Test
    void discountPrice_throwExcessiveParameterException_Over100() {
        when(vehicleRepository.findById(1L))
                .thenReturn(Optional.of(DEFAULT_VEHICLE()));
        assertThrows(ExcessiveParameterException.class, () -> vehicleService.calculatePriceDiscount(101, 1L));
    }

    @Test
    void discountPrice_throwExcessiveParameterException_Below0() {
        when(vehicleRepository.findById(1L))
                .thenReturn(Optional.of(DEFAULT_VEHICLE()));
        assertThrows(ExcessiveParameterException.class, () -> vehicleService.calculatePriceDiscount(-1, 1L));
    }

    @Test
    void discountPrice_setTheRightDiscountedPrice() {
        BigDecimal expectedDiscountedPrice = BigDecimal.valueOf(400000).setScale(2, RoundingMode.HALF_EVEN);
        when(vehicleRepository.findById(1L))
                .thenReturn(Optional.of(DEFAULT_VEHICLE()));
        Vehicle expectedVehicle = DEFAULT_VEHICLE();
        expectedVehicle.setDiscountedPrice(expectedDiscountedPrice);
        expectedVehicle.setDiscountFlag(true);
        when(vehicleRepository.save(any()))
                .thenReturn(expectedVehicle);
        VehicleDiscountedDTO expected = new VehicleDiscountedDTO(
                1,
                VehicleType.CAR,
                "Ferrari",
                "Enzo",
                2004,
                BigDecimal.valueOf(400000).setScale(2, RoundingMode.HALF_EVEN),
                BigDecimal.valueOf(40).setScale(2, RoundingMode.HALF_EVEN),
                true);
        VehicleDiscountedDTO result = vehicleService.discountPrice(50, 1L);
        assertEquals(result.getPrice(), expected.getPrice());
    }

    @Test
    void discountPrice_changeDiscountFlag() {
        BigDecimal expectedDiscountedPrice = BigDecimal.valueOf(400000).setScale(2, RoundingMode.HALF_EVEN);
        when(vehicleRepository.findById(1L))
                .thenReturn(Optional.of(DEFAULT_VEHICLE()));
        Vehicle expectedVehicle = DEFAULT_VEHICLE();
        expectedVehicle.setDiscountedPrice(expectedDiscountedPrice);
        expectedVehicle.setDiscountFlag(true);
        when(vehicleRepository.save(any()))
                .thenReturn(expectedVehicle);
        VehicleDiscountedDTO expected = new VehicleDiscountedDTO(
                1,
                VehicleType.CAR,
                "Ferrari",
                "Enzo",
                2004,
                BigDecimal.valueOf(400000).setScale(2, RoundingMode.HALF_EVEN),
                BigDecimal.valueOf(40).setScale(2, RoundingMode.HALF_EVEN),
                true);
        VehicleDiscountedDTO result = vehicleService.discountPrice(50, 1L);
        assertTrue(result.isDiscountFlag());
    }

    @Test
    void discountPrice_idDoNotChange() {
        BigDecimal expectedDiscountedPrice = BigDecimal.valueOf(400000).setScale(2, RoundingMode.HALF_EVEN);
        when(vehicleRepository.findById(1L))
                .thenReturn(Optional.of(DEFAULT_VEHICLE()));
        Vehicle expectedVehicle = DEFAULT_VEHICLE();
        expectedVehicle.setDiscountedPrice(expectedDiscountedPrice);
        expectedVehicle.setDiscountFlag(true);
        when(vehicleRepository.save(any()))
                .thenReturn(expectedVehicle);
        VehicleDiscountedDTO expected = new VehicleDiscountedDTO(
                1,
                VehicleType.CAR,
                "Ferrari",
                "Enzo",
                2004,
                BigDecimal.valueOf(400000).setScale(2, RoundingMode.HALF_EVEN),
                BigDecimal.valueOf(40).setScale(2, RoundingMode.HALF_EVEN),
                true);
        VehicleDiscountedDTO result = vehicleService.discountPrice(50, 1L);
        assertEquals(result.getId(), expected.getId());
    }

//    @Test
//    void priceRemoveDiscount_changeDiscountFlag() {
//        when(vehicleRepository.findById(1L))
//                .thenReturn(Optional.of(DEFAULT_VEHICLE()));
//        VehicleDiscountedDTO result = vehicleService.removeDiscountPrice(1L);
//        assertFalse(result.isDiscountFlag());
//    }
//
//    @Test
//    void priceRemoveDiscount_setDiscountedPriceEqualAsPrice() {
//        when(vehicleRepository.findById(1L))
//                .thenReturn(Optional.of(DEFAULT_VEHICLE()));
//        Vehicle expectedVehicle = DEFAULT_VEHICLE();
//        expectedVehicle.setDiscountedPrice(DEFAULT_VEHICLE().getPrice());
//        expectedVehicle.setDiscountFlag(false);
//        when(vehicleRepository.save(any()))
//                .thenReturn(expectedVehicle);
//        VehicleDiscountedDTO expected = new VehicleDiscountedDTO(
//                1,
//                VehicleType.CAR,
//                "Ferrari",
//                "Enzo",
//                2004,
//                BigDecimal.valueOf(400000).setScale(2, RoundingMode.HALF_EVEN),
//                BigDecimal.valueOf(40).setScale(2, RoundingMode.HALF_EVEN),
//                true);
//        VehicleDiscountedDTO result = vehicleService.discountPrice(50, 1L);
//        assertEquals(expected.getPrice(), result.getPrice());
//    }
}
