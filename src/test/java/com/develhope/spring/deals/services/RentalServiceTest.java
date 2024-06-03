package com.develhope.spring.deals.services;

import com.develhope.spring.deals.repositories.RentalRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RentalServiceTest {

    @Mock
    private RentalRepository rentalRepository;

    @InjectMocks
    private RentalService rentalService;

    @Test
    void successfulCreateRental_test() {

    }
}
