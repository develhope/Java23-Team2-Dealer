package com.develhope.spring.deals.components;

import com.develhope.spring.deals.dtos.RentalCreatorDTO;
import com.develhope.spring.deals.dtos.RentalReturnerDTO;
import com.develhope.spring.deals.models.Rental;
import com.develhope.spring.users.components.UserMapper;
import com.develhope.spring.users.dtos.UserRentalReturnerDto;
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
                2,
                1);

        Vehicle vehicle = new Vehicle(1);

        User user = new User(2);
        User seller = new User(1);

        Rental result = rentalMapper.toEntity(rentalCreatorDTO);
        Rental expected = new Rental(startDate, endDate, BigDecimal.valueOf(40),
                BigDecimal.valueOf(80).setScale(2, RoundingMode.HALF_EVEN), true, vehicle, 1, user, seller);
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
                2,
                1);

        Vehicle vehicle = new Vehicle(1);

        User user = new User(2);
        User seller = new User(1);

        Rental result = rentalMapper.toEntity(rentalCreatorDTO);
        Rental expected = new Rental(startDate, endDate, BigDecimal.valueOf(40), BigDecimal.valueOf(80), true, vehicle, 1, user, seller);
        assertEquals(expected.getUser().getId(), result.getUser().getId());
    }

    @Test
    void toReturnerDTO_From_testSuccessfulSameTotalPrice() {
        Vehicle vehicle = new Vehicle();
        vehicle.setId(1);

        User seller = new User(1);
        User user = new User(2);

        LocalDate startDate = LocalDate.of(2024, 06, 3);
        LocalDate endDate = LocalDate.of(2024, 06, 5);
        BigDecimal dailyCost = BigDecimal.valueOf(40).setScale(2, RoundingMode.HALF_EVEN);
        Rental rental = new Rental(startDate, endDate, dailyCost, BigDecimal.valueOf(80),false, vehicle, 1, user, seller);

        VehicleRentalReturnerDTO vehicleRentalReturnerDTO = new VehicleRentalReturnerDTO();
        vehicleRentalReturnerDTO.setId(rental.getVehicle().getId());

        UserRentalReturnerDto buyerRentalReturnerDto = new UserRentalReturnerDto();
        buyerRentalReturnerDto.setEmail(rental.getUser().getEmail());
        UserRentalReturnerDto sellerRentalReturnerDto = new UserRentalReturnerDto();

        RentalReturnerDTO result = rentalMapper.toReturnerDTO(rental);
        RentalReturnerDTO expected = new RentalReturnerDTO(1, startDate, endDate, dailyCost, false, vehicleRentalReturnerDTO, buyerRentalReturnerDto, sellerRentalReturnerDto);
        assertEquals(expected.getTotalCost(), result.getTotalCost());
    }
}
