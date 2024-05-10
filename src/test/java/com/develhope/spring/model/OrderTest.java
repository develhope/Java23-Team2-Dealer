package com.develhope.spring.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class OrderTests {

    @Test
    void testOrderInitialization() {
        Order order = new Order(true, false, OrderStatus.PENDING, 123);
        assertNotNull(order, "L'ordine non deve essere nullo");
        assertTrue(order.getDownPayment(), "L'acconto dovrebbe essere vero");
        assertFalse(order.getPaid(), "Inizialmente l'ordine non deve essere contrassegnato come pagato");
        assertEquals(OrderStatus.PENDING, order.getOrderStatus(), "Lo stato dell'ordine dovrebbe essere PENDING (IN ATTESA)");
        assertEquals(123, order.getVehicleId(), "L'ID del veicolo deve corrispondere all'argomento del costruttore");
    }

    @Test
    void testOrderInitializationWithNoPayments() {
        Order order = new Order(false, false, OrderStatus.PENDING, 123);
        assertNotNull(order, "L'ordine non deve essere nullo");
        assertFalse(order.getDownPayment(), "L'acconto dovrebbe essere falso");
        assertFalse(order.getPaid(), "Inizialmente l'ordine non deve essere contrassegnato come pagato");
        assertEquals(OrderStatus.PENDING, order.getOrderStatus(), "Lo stato dell'ordine dovrebbe essere PENDING (IN ATTESA)");
        assertEquals(123, order.getVehicleId(), "L'ID del veicolo deve corrispondere all'argomento del costruttore");
    }

    @Test
    void testOrderInitializationWithPaidOnly() {
        Order order = new Order(false, true, OrderStatus.PENDING, 123);
        assertNotNull(order, "L'ordine non deve essere nullo");
        assertFalse(order.getDownPayment(), "L'acconto dovrebbe essere falso");
        assertTrue(order.getPaid(), "L'ordine dovrebbe essere contrassegnato come pagato");
        assertEquals(OrderStatus.PENDING, order.getOrderStatus(), "Lo stato dell'ordine dovrebbe essere PENDING (IN ATTESA)");
        assertEquals(123, order.getVehicleId(), "L'ID del veicolo deve corrispondere all'argomento del costruttore");
    }

    @Test
    void testOrderInitializationWithBothPayments() {
        Order order = new Order(true, true, OrderStatus.PENDING, 123);
        assertNotNull(order, "L'ordine non deve essere nullo");
        assertTrue(order.getDownPayment(), "L'acconto dovrebbe essere vero");
        assertTrue(order.getPaid(), "L'ordine dovrebbe essere contrassegnato come pagato");
        assertEquals(OrderStatus.PENDING, order.getOrderStatus(), "Lo stato dell'ordine dovrebbe essere PENDING (IN ATTESA)");
        assertEquals(123, order.getVehicleId(), "L'ID del veicolo deve corrispondere all'argomento del costruttore");
    }

    @Test
    void testOrderSetters() {
        Order order = new Order(false, false, OrderStatus.PENDING, 123);
        order.setDownPayment(true);
        order.setPaid(true);
        order.setOrderStatus(OrderStatus.SHIPPED);
        order.setVehicleId(456);

        assertTrue(order.getDownPayment(), "L'acconto deve essere aggiornato a true");
        assertTrue(order.getPaid(), "L'ordine deve essere contrassegnato come pagato");
        assertEquals(OrderStatus.SHIPPED, order.getOrderStatus(), "Lo stato dell'ordine dovrebbe essere aggiornato in SHIPPED (SPEDITO)");
        assertEquals(456, order.getVehicleId(), "L'ID del veicolo deve essere aggiornato");
    }

    @Test
    void testOrderSettersStartingWithPaidOnly() {
        Order order = new Order(false, true, OrderStatus.PENDING, 123);
        order.setDownPayment(true);
        order.setPaid(false);
        order.setOrderStatus(OrderStatus.SHIPPED);
        order.setVehicleId(456);

        assertTrue(order.getDownPayment(), "L'acconto deve essere aggiornato a true");
        assertFalse(order.getPaid(), "L'ordine non deve essere contrassegnato come pagato");
        assertEquals(OrderStatus.SHIPPED, order.getOrderStatus(), "Lo stato dell'ordine dovrebbe essere aggiornato in SHIPPED (SPEDITO)");
        assertEquals(456, order.getVehicleId(), "L'ID del veicolo deve essere aggiornato");
    }

    @Test
    void testOrderSettersStartingWithDownPaymentOnly() {
        Order order = new Order(true, false, OrderStatus.PENDING, 123);
        order.setDownPayment(false);
        order.setPaid(true);
        order.setOrderStatus(OrderStatus.CANCELLED);
        order.setVehicleId(789);

        assertFalse(order.getDownPayment(), "L'acconto non deve essere true");
        assertTrue(order.getPaid(), "L'ordine deve essere contrassegnato come pagato");
        assertEquals(OrderStatus.CANCELLED, order.getOrderStatus(), "Lo stato dell'ordine dovrebbe essere aggiornato in CANCELLED (ANNULLATO)");
        assertEquals(789, order.getVehicleId(), "L'ID del veicolo deve essere aggiornato");
    }

    @Test
    void testOrderSettersStartingWithBothPayments() {
        Order order = new Order(true, true, OrderStatus.PENDING, 123);
        order.setDownPayment(false);
        order.setPaid(false);
        order.setOrderStatus(OrderStatus.PENDING);
        order.setVehicleId(321);

        assertFalse(order.getDownPayment(), "L'acconto non deve essere true");
        assertFalse(order.getPaid(), "L'ordine non deve essere contrassegnato come pagato");
        assertEquals(OrderStatus.PENDING, order.getOrderStatus(), "Lo stato dell'ordine dovrebbe rimanere PENDING (IN ATTESA)");
        assertEquals(321, order.getVehicleId(), "L'ID del veicolo deve essere aggiornato");
    }

    @Test
    void testOrderSettersStartingPaidToUnpaid() {
        Order order = new Order(true, true, OrderStatus.PAID, 123);
        order.setDownPayment(false);
        order.setPaid(false);
        order.setOrderStatus(OrderStatus.PAID);
        order.setVehicleId(456);

        assertFalse(order.getDownPayment(), "L'acconto non deve essere true");
        assertFalse(order.getPaid(), "L'ordine non deve essere contrassegnato come pagato");
        assertEquals(OrderStatus.PAID, order.getOrderStatus(), "Lo stato dell'ordine dovrebbe rimanere PAID (PAGATO)");
        assertEquals(456, order.getVehicleId(), "L'ID del veicolo deve essere aggiornato");
    }

    @Test
    void testOrderAdjustmentsWhilePaid() {
        Order order = new Order(true, true, OrderStatus.PAID, 123);
        order.setDownPayment(false);
        order.setVehicleId(789);

        assertFalse(order.getDownPayment(), "L'acconto deve essere aggiornato a false");
        assertTrue(order.getPaid(), "L'ordine deve rimanere contrassegnato come pagato");
        assertEquals(OrderStatus.PAID, order.getOrderStatus(), "Lo stato dell'ordine dovrebbe rimanere PAID (PAGATO)");
        assertEquals(789, order.getVehicleId(), "L'ID del veicolo deve essere aggiornato");
    }

    @Test
    void testSettingOrderToFullyPaidFromUnpaid() {
        Order order = new Order(false, false, OrderStatus.PENDING, 123);
        order.setDownPayment(true);
        order.setPaid(true);
        order.setOrderStatus(OrderStatus.PAID);
        order.setVehicleId(456);

        assertTrue(order.getDownPayment(), "L'acconto deve essere impostato a true");
        assertTrue(order.getPaid(), "L'ordine deve essere contrassegnato come pagato");
        assertEquals(OrderStatus.PAID, order.getOrderStatus(), "Lo stato dell'ordine dovrebbe essere aggiornato a PAID (PAGATO)");
        assertEquals(456, order.getVehicleId(), "L'ID del veicolo deve essere aggiornato");
    }

    @Test
    void testAdjustmentsFromPartiallyPaidToFullyPaid() {
        Order order = new Order(true, false, OrderStatus.PENDING, 123);
        order.setDownPayment(true);
        order.setPaid(true);
        order.setOrderStatus(OrderStatus.PAID);
        order.setVehicleId(789);

        assertTrue(order.getDownPayment(), "L'acconto deve rimanere vero");
        assertTrue(order.getPaid(), "L'ordine deve essere contrassegnato come pagato");
        assertEquals(OrderStatus.PAID, order.getOrderStatus(), "Lo stato dell'ordine dovrebbe essere aggiornato a PAID (PAGATO)");
        assertEquals(789, order.getVehicleId(), "L'ID del veicolo deve essere aggiornato");
    }
}