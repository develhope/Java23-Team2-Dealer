package com.develhope.spring.deals.components;

import com.develhope.spring.deals.dtos.RentalCreatorDTO;
import com.develhope.spring.deals.dtos.RentalReturnerDTO;
import com.develhope.spring.deals.models.Rental;
import com.develhope.spring.users.components.UserMapper;
import com.develhope.spring.users.dtos.BuyerRentalReturnerDto;
import com.develhope.spring.users.models.User;
import com.develhope.spring.vehicles.dtos.VehicleRentalReturnerDTO;
import com.develhope.spring.vehicles.models.Vehicle;
import com.develhope.spring.vehicles.components.VehicleMapper;
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
                true,
                1,
                1);

        Vehicle vehicle = new Vehicle(1);
        vehicle.setDailyCost(BigDecimal.valueOf(40));

        User user = new User(1);

        Rental result = rentalMapper.toEntity(rentalCreatorDTO);
        Rental expected = new Rental(startDate, endDate, vehicle.getDailyCost(), true, vehicle, 1, user);
        assertEquals(expected.getVehicle().getId(), result.getVehicle().getId());
    }

    @Test
    void toEntity_From_testSuccessfulSameUserSuccessfulSameUser() {
        LocalDate startDate = LocalDate.of(2024, 06, 3);
        LocalDate endDate = LocalDate.of(2024, 06, 5);

        RentalCreatorDTO rentalCreatorDTO = new RentalCreatorDTO(
                startDate,
                endDate,
                true,
                1,
                1);

        Vehicle vehicle = new Vehicle(1);
        vehicle.setDailyCost(BigDecimal.valueOf(40));
        User user = new User(1);

        Rental result = rentalMapper.toEntity(rentalCreatorDTO);
        Rental expected = new Rental(startDate, endDate,vehicle.getDailyCost(), true, vehicle, 1, user);
        assertEquals(expected.getUser().getId(), result.getUser().getId());
    }

    @Test
    void toReturnerDTO_From_testSuccessfulSameTotalPrice() {
        Vehicle vehicle = new Vehicle();
        vehicle.setId(1);
        vehicle.setDailyCost(BigDecimal.valueOf(40));
        User user = new User(1);

        LocalDate startDate = LocalDate.of(2024, 06, 3);
        LocalDate endDate = LocalDate.of(2024, 06, 5);
        Rental rental = new Rental(startDate, endDate, vehicle.getDailyCost(), false, vehicle, 1, user);

        VehicleRentalReturnerDTO vehicleRentalReturnerDTO = new VehicleRentalReturnerDTO();
        vehicleRentalReturnerDTO.setId(rental.getVehicle().getId());

        BuyerRentalReturnerDto buyerRentalReturnerDto = new BuyerRentalReturnerDto();
        buyerRentalReturnerDto.setEmail(rental.getUser().getEmail());

        RentalReturnerDTO result = rentalMapper.toReturnerDTO(rental);
        RentalReturnerDTO expected = new RentalReturnerDTO(1, startDate, endDate, vehicle.getDailyCost(), rental.getTotalCost(), false, vehicleRentalReturnerDTO, buyerRentalReturnerDto);
        assertEquals(expected.getTotalCost(), result.getTotalCost());
    }
}
