package vehicle;

import org.junit.jupiter.api.Test;
import vehicle.enumerators.*;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class VehicleTest {
    Vehicle testVehicle = Vehicle.builder("", "", Colors.BLACK, 500, 500, Gears.AUTOMATIC,2024, MotorPowerSupply.DIESEL,
            17000, UsedFlag.NEW, MarketStatus.AVIABLE, 0).build();

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
        BigDecimal expectedResult = new BigDecimal("1700.00");
        BigDecimal actualResult = testVehicle.calculateDiscount(10);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testIfActivateDiscountSetTheFlagAsTrue() {
        testVehicle.activateDiscount(50);
        assertTrue(testVehicle.isDiscountFlag());
    }
}
