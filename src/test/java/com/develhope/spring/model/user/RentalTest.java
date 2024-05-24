package com.develhope.spring.model.user;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import vehicle.Vehicle;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class RentalTest {

    private Rental rental;
    private Vehicle vehicle;


    @Test
    public void testCalculateTotalCost() {
        rental.calculateTotalCost();
        assertEquals(new BigDecimal("500.00").setScale(2, BigDecimal.ROUND_HALF_EVEN), rental.getTotalCost());
    }

    @Test
    public void testSetPaid() {
        rental.setPaid(true);
        assertTrue(rental.isPaid());
    }

    @Test
    public void testSetStartDate() {
        LocalDate newStartDate = LocalDate.now().plusDays(1);
        rental.setStartDate(newStartDate);
        assertEquals(newStartDate, rental.getStartDate());
    }

    @Test
    public void testSetEndDate() {
        LocalDate newEndDate = LocalDate.now().plusDays(10);
        rental.setEndDate(newEndDate);
        assertEquals(newEndDate, rental.getEndDate());
    }

    @Test
    public void testSetDailyCost() {
        BigDecimal newDailyCost = new BigDecimal("150.00");
        rental.setDailyCost(newDailyCost);
        assertEquals(newDailyCost, rental.getDailyCost());
    }

    @Test
    public void testSetTotalCost() {
        BigDecimal newTotalCost = new BigDecimal("600.00");
        rental.setTotalCost(newTotalCost);
        assertEquals(newTotalCost, rental.getTotalCost());
    }


}