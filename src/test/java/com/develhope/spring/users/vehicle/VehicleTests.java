package com.develhope.spring.users.vehicle;

import com.develhope.spring.vehicle.ExcessiveParameterException;
import com.develhope.spring.vehicle.models.Vehicle;
import com.develhope.spring.vehicle.vehicleEnums.KindOfVehicle;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class VehicleTests {

    Vehicle testVehicle = Vehicle.builder(KindOfVehicle.CAR,"", "", 17000, 0).build();
    @Test
    public void testCalculateDiscount_ThrowAnExceptionIfOver100() {
        assertThrows(Exception.class, () -> testVehicle.calculateDiscount(150));
    }
    @Test
    public void testCalculateDiscount_ThrowAnExceptionIfUnder0() {
        assertThrows(Exception.class, () -> testVehicle.calculateDiscount(-1));
    }
    @Test
    public void testCalculateDiscount_GivesTheRightResult() throws ExcessiveParameterException {
        BigDecimal expectedResult = new BigDecimal("15300.00");
        testVehicle.calculateDiscount(10);
        BigDecimal actualResult = testVehicle.getDiscountedPrice();
        assertEquals(expectedResult, actualResult);
    }
    @Test
    public void testActivateDiscount_SetTheFlagAsTrue() {
        testVehicle.activateDiscount(50);
        assertTrue(testVehicle.isDiscountFlag());
    }
    @Test
    public void testRemoveDiscount_SetTheFlagAsFalse(){
        testVehicle.activateDiscount(0);
        testVehicle.removeDiscount();
        assertFalse(testVehicle.isDiscountFlag());
    }
    @Test
    public void testRemoveDiscount_ReturnDiscountedPriceAsOriginal(){
        testVehicle.activateDiscount(50);
        testVehicle.removeDiscount();
        assertEquals(testVehicle.getOriginalPrice(), testVehicle.getDiscountedPrice());
    }
}
