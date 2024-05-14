package com.develhope.spring.model.user;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class RentalTest {

    @Test
    public void testCalculateTotalCost() {
        // Inizialize some used variables
        LocalDate startDate = LocalDate.of(2022, 1, 1);
        LocalDate endDate = LocalDate.of(2022, 1, 5);
        BigDecimal dailyCost = new BigDecimal("50.00");
        int vehicleId = 1;

        // Inizialize a rent with known variables
        Rental rental = new Rental(startDate, endDate, dailyCost, vehicleId);

        // Assert expected true 50* 4 days = 200
        BigDecimal expectedTotalCost = new BigDecimal("200.00");
        assertEquals(expectedTotalCost, rental.getTotalCost());
    }

    @Test
    public void testMarkAsPaid() {
        // Inizialize some used variables
        LocalDate startDate = LocalDate.of(2022, 1, 1);
        LocalDate endDate = LocalDate.of(2022, 1, 5);
        BigDecimal dailyCost = new BigDecimal("50.00");
        int vehicleId = 123;
        Rental rental = new Rental(startDate, endDate, dailyCost, vehicleId);

        // Make next assert true
        rental.markAsPaid();

        // Assert expected true
        assertTrue(rental.isPaid());
    }
}