package com.develhope.spring.deals.models;

import com.develhope.spring.users.models.User;
import com.develhope.spring.vehicles.models.Vehicle;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

/**
 * Represents an order or purchase. This class includes details about
 * the payment status, order status, and identifies the vehicle associated with the order.
 */
@Entity
public class Order {
    private boolean downPayment;
    private OrderStatus orderStatus;
    @OneToOne
    private Vehicle vehicle;

    public Order(boolean downPayment, OrderStatus orderStatus, Vehicle vehicle) {
        this.downPayment = downPayment;
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
