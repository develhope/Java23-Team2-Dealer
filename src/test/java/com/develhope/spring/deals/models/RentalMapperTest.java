package com.develhope.spring.deals.models;

import com.develhope.spring.configuration.ModelMapperConfig;
import com.develhope.spring.deals.dtos.RentalCreatorDTO;
import com.develhope.spring.users.models.Roles;
import com.develhope.spring.users.models.User;
import com.develhope.spring.users.models.exceptions.EmptyParameterException;
import com.develhope.spring.users.models.exceptions.WrongEmailFormatException;
import com.develhope.spring.vehicles.models.Vehicle;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class RentalMapperTest {

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private RentalMapper rentalMapper;

    @Test
    void toEntity_test() throws EmptyParameterException, WrongEmailFormatException {
        LocalDate endTime = LocalDate.of(2024, 12, 3);
        RentalCreatorDTO rentalCreatorDTO = new RentalCreatorDTO(LocalDate.now(), endTime, BigDecimal.valueOf(150), true, 1, 1);
        Rental result;
        result = rentalMapper.toEntity(rentalCreatorDTO);
        User expected = new User();
        expected.setId(1);
        expected.setName("hey");
        expected.setEmail("h@e.it");
        expected.setRoles(Roles.BUYER);
        assertEquals(expected.getId(), result.getUser().getId());
    }
}
