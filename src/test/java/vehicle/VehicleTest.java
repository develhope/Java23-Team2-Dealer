package vehicle;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class VehicleTest {
    Vehicle testVehicle = Vehicle.builder("", "", 17000, 0).build();

    @Test
    public void testIfCalculateDiscountThrowAnExceptionIfOver100() {
        assertThrows(Exception.class, () -> testVehicle.calculateDiscount(150));
    }

    @Test
    public void testIfCalculateDiscountThrowAnExceptionIfUnder0() {
        assertThrows(Exception.class, () -> testVehicle.calculateDiscount(-1));
    }

    @Test
    public void testIfCalculateDiscountGivesTheRightResult() {
        BigDecimal expectedResult = new BigDecimal("15300.00");
        testVehicle.calculateDiscount(10);
        BigDecimal actualResult = testVehicle.getDiscountedPrice();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testIfActivateDiscountSetTheFlagAsTrue() {
        testVehicle.activateDiscount(50);
        assertTrue(testVehicle.isDiscountFlag());
    }
    @Test
    public void testIfRemoveDiscountSetTheFlagAsFalse(){
        testVehicle.activateDiscount(0);
        testVehicle.removeDiscount();
        assertFalse(testVehicle.isDiscountFlag());
    }

    @Test
    public void testIfRemoveDiscountReturnDiscountedPriceAsOriginal(){
        testVehicle.activateDiscount(50);
        testVehicle.removeDiscount();
        assertEquals(testVehicle.getOriginalPrice(), testVehicle.getDiscountedPrice());
    }
}
