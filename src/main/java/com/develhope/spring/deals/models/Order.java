package com.develhope.spring.deals.models;

import com.develhope.spring.users.models.User;
import com.develhope.spring.vehicles.models.Vehicle;

/**
 * Represents an order or purchase. This class includes details about
 * the payment status, order status, and identifies the vehicle associated with the order.
 */

public class Order {
    private boolean downPayment;
    private OrderStatus orderStatus;
    private boolean isPaid;
    private Vehicle vehicle;

    public Order(boolean downPayment, OrderStatus orderStatus, boolean isPaid, Vehicle vehicle) {
        this.downPayment = downPayment;
        this.isPaid = isPaid;
        this.orderStatus = orderStatus;
        this.vehicle = vehicle;
    }


    public Order(boolean downPayment, OrderStatus orderStatus, long vehicleId, User user) {
    }

    public boolean isDownPayment() {
        return downPayment;
    }

    public void setDownPayment(boolean downPayment) {
        this.downPayment = downPayment;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }


    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public Vehicle getVehicleId() {
        return vehicle;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicle = vehicle;
    }

    /**
     * Returns a string representation of the order, including all details.
     *
     * @return a string representation of the order.
     */
    @Override
    public String toString() {
        return "Ordine { " +
                "Acconto = " + downPayment +
                ", Stato dell'ordine = " + orderStatus +
                ", Id del veicolo = " + vehicle +
                '}';
    }
}
