package com.develhope.spring.deals.components;

import com.develhope.spring.deals.dtos.RentalCreatorDTO;
import com.develhope.spring.deals.dtos.RentalReturnerDTO;
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

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class RentalMapperTest {

    @Mock
    private VehicleMapper vehicleMapper;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private RentalMapper rentalMapper;

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
                1);

        Vehicle vehicle = new Vehicle();
        vehicle.setId(1);

        User user = new User();
        user.setId(1);

        Rental result = rentalMapper.toEntityFrom(rentalCreatorDTO);
        Rental expected = new Rental(startDate, endDate, BigDecimal.valueOf(40), true, vehicle, 1, user);
        assertEquals(expected.getTotalCost(), BigDecimal.valueOf(80).setScale(2, RoundingMode.HALF_EVEN));
    }

    @Test
    void toEntity_From_testSuccessfulSameUserSuccessfulSameUser() {
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.of(2024, 06, 5);

        RentalCreatorDTO rentalCreatorDTO = new RentalCreatorDTO(
                startDate,
                endDate,
                BigDecimal.valueOf(40).setScale(2, RoundingMode.HALF_EVEN),
                true,
                1,
                1);

        Vehicle vehicle = new Vehicle();
        vehicle.setId(1);

        User user = new User();
        user.setId(1);

        Rental result = rentalMapper.toEntityFrom(rentalCreatorDTO);
        Rental expected = new Rental(startDate, endDate, BigDecimal.valueOf(40), true, vehicle, 1, user);
        assertEquals(expected.getUser().getId(), result.getUser().getId());
    }

    @Test
    void toReturnerDTO_From_testSuccessfulSameTotalPrice() {
        Vehicle vehicle = new Vehicle();
        vehicle.setId(1);

        User user = new User();
        user.setId(1);

        LocalDate startDate = LocalDate.now();
        LocalDate endDate= LocalDate.of(2024, 06, 5);
        BigDecimal dailyCost = BigDecimal.valueOf(40).setScale(2, RoundingMode.HALF_EVEN);
        Rental rental = new Rental(startDate, endDate, dailyCost, false, vehicle, 1, user);

        VehicleRentalReturnerDTO vehicleRentalReturnerDTO = new VehicleRentalReturnerDTO();
        vehicleRentalReturnerDTO.setId(rental.getVehicle().getId());

        BuyerRentalReturnerDto buyerRentalReturnerDto = new BuyerRentalReturnerDto();
        buyerRentalReturnerDto.setEmail(rental.getUser().getEmail());

        RentalReturnerDTO result = rentalMapper.toReturnerDTOFrom(rental);
        RentalReturnerDTO expected = new RentalReturnerDTO(startDate, endDate, dailyCost, false, vehicleRentalReturnerDTO, buyerRentalReturnerDto);
        assertEquals(expected.getTotalCost(), result.getTotalCost());
    }
}
