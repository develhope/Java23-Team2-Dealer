package com.develhope.spring.deals.models;

import com.develhope.spring.users.models.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import com.develhope.spring.vehicles.models.Vehicle;


/**
 * Represents an order or purchase. This class includes details about
 * the payment status, order status, and identifies the vehicle associated with the order.
 */

@Table(name = "orders")
@Entity

public class Order {

    @Id
    @GeneratedValue
    private long id;
    private boolean downPayment;
    private OrderStatus orderStatus;
    private boolean isPaid;
    private Vehicle vehicle;

    public Order(long id, long vehicleId, boolean downPayment, OrderStatus orderStatus, boolean isPaid, Vehicle vehicle) {
        this.id = id;
        this.downPayment = downPayment;
        this.orderStatus = orderStatus;
        this.isPaid = isPaid;
        this.vehicle = vehicle;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
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
