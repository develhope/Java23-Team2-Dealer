package com.develhope.spring.deals.components;

import com.develhope.spring.deals.dtos.RentalCreatorDTO;
import com.develhope.spring.deals.dtos.RentalResponseDTO;
import com.develhope.spring.deals.models.Rental;
import com.develhope.spring.users.components.UserMapper;
import com.develhope.spring.users.dtos.BuyerRentalReturnerDto;
import com.develhope.spring.users.models.User;
import com.develhope.spring.vehicles.dtos.VehicleRentalReturnerDTO;
import com.develhope.spring.vehicles.models.Vehicle;
import com.develhope.spring.vehicles.models.VehicleMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class RentalMapperTest {

    @Mock
    private VehicleMapper vehicleMapper;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private RentalMapper rentalMapper;

    private final List<User> DEFAULT_SELLERS_LIST = new ArrayList<>();
    @Test
    void toEntity_From_testSuccessfulSameTotalPrice() {
        LocalDate startDate = LocalDate.of(2024, 06, 3);
        LocalDate endDate = LocalDate.of(2024, 06, 5);

        RentalCreatorDTO rentalCreatorDTO = new RentalCreatorDTO(
                startDate,
                endDate,
                BigDecimal.valueOf(40).setScale(2, RoundingMode.HALF_EVEN),
                true,
                1,
                1,
                DEFAULT_SELLERS_LIST
                );

        Vehicle vehicle = new Vehicle(1);

        User user = new User(1);

        Rental result = rentalMapper.toEntity(rentalCreatorDTO);
        Rental expected = new Rental(1, startDate, endDate, BigDecimal.valueOf(40), BigDecimal.valueOf(80).setScale(2, RoundingMode.HALF_EVEN), true, vehicle, user,DEFAULT_SELLERS_LIST);
        assertEquals(expected.getTotalCost(), BigDecimal.valueOf(80).setScale(2, RoundingMode.HALF_EVEN));
    }

    @Test
    void toEntity_From_testSuccessfulSameUserSuccessfulSameUser() {
        LocalDate startDate = LocalDate.of(2024, 06, 3);
        LocalDate endDate = LocalDate.of(2024, 06, 5);

        RentalCreatorDTO rentalCreatorDTO = new RentalCreatorDTO(
                startDate,
                endDate,
                BigDecimal.valueOf(40).setScale(2, RoundingMode.HALF_EVEN),
                true,
                1,
                1,
                DEFAULT_SELLERS_LIST
                );

        Vehicle vehicle = new Vehicle(1);

        User user = new User(1);

        Rental result = rentalMapper.toEntity(rentalCreatorDTO);
        Rental expected = new Rental(1, startDate, endDate, BigDecimal.valueOf(40), BigDecimal.valueOf(80), true, vehicle, user, DEFAULT_SELLERS_LIST);
        assertEquals(expected.getBuyer().getId(), result.getBuyer().getId());
    }

    @Test
    void toResponseDTO_From_testSuccessfulSameTotalPrice() {
        Vehicle vehicle = new Vehicle();
        vehicle.setId(1);

        User user = new User(1);

        LocalDate startDate = LocalDate.of(2024, 06, 3);
        LocalDate endDate = LocalDate.of(2024, 06, 5);
        BigDecimal dailyCost = BigDecimal.valueOf(40).setScale(2, RoundingMode.HALF_EVEN);
        Rental rental = new Rental(1, startDate, endDate, dailyCost, BigDecimal.valueOf(80),false, vehicle, user, DEFAULT_SELLERS_LIST);

        VehicleRentalReturnerDTO vehicleRentalReturnerDTO = new VehicleRentalReturnerDTO();
        vehicleRentalReturnerDTO.setId(rental.getVehicle().getId());

        BuyerRentalReturnerDto buyerRentalReturnerDto = new BuyerRentalReturnerDto();
        buyerRentalReturnerDto.setEmail(rental.getBuyer().getEmail());

        RentalResponseDTO result = rentalMapper.toResponseDTO(rental);
        RentalResponseDTO expected = new RentalResponseDTO(1, startDate, endDate, dailyCost, false, vehicleRentalReturnerDTO, buyerRentalReturnerDto,DEFAULT_SELLERS_LIST);
        assertEquals(expected.getTotalCost(), result.getTotalCost());
    }
}
