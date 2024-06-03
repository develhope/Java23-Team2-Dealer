package com.develhope.spring.deals.services;

import com.develhope.spring.deals.components.RentalMapper;
import com.develhope.spring.deals.dtos.RentalCreatorDTO;
import com.develhope.spring.deals.models.Rental;
import com.develhope.spring.deals.repositories.RentalRepository;
import com.develhope.spring.users.dtos.BuyerRentalReturnerDto;
import com.develhope.spring.users.models.User;
import com.develhope.spring.users.models.UserMapper;
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

import static org.mockito.Mockito.when;

@SpringBootTest
public class RentalServiceTest {

    @Mock
    private VehicleMapper vehicleMapper;

    @Mock
    private UserMapper userMapper;

    @Mock
    private RentalMapper rentalMapper;

    @Mock
    private RentalRepository rentalRepository;

    @InjectMocks
    private RentalService rentalService;

    @Test
    void successfulCreateRental_test() {
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.of(2024, 6, 5);
        BigDecimal price = BigDecimal.valueOf(40).setScale(2, RoundingMode.HALF_EVEN);
        RentalCreatorDTO rentalCreatorDTO = new RentalCreatorDTO(
                startDate,
                endDate,
                price,
                true,
                1,
                1);

        Vehicle vehicle = new Vehicle();
        vehicle.setId(1);
        User user = new User();
        user.setId(1);


        Rental rental = rentalMapper.toEntityFrom(rentalCreatorDTO);
        Rental expected = new Rental(
                startDate,
                endDate,
                price,
                true,
                vehicle,
                1,
                user
        );

        VehicleRentalReturnerDTO vehicleRentalReturnerDTO = new VehicleRentalReturnerDTO();
        vehicleRentalReturnerDTO.setId(rental.getVehicle().getId());
        BuyerRentalReturnerDto buyerRentalReturnerDto = new BuyerRentalReturnerDto();
        buyerRentalReturnerDto.setEmail(rental.getUser().getEmail());


        when(rentalRepository.save(rental))
                .thenReturn(expected);

        Rental result = rentalMapper.toEntityFrom(rentalService.create(rentalCreatorDTO));
    }
}
