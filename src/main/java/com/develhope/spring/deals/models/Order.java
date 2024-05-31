package com.develhope.spring.deals.models;

import com.develhope.spring.users.models.User;
<<<<<<< HEAD
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
=======
import com.develhope.spring.vehicles.models.Vehicle;
>>>>>>> db94af9e660c4ae21c88f9f9e6f577c5b5315a99

/**
 * Represents an order or purchase. This class includes details about
 * the payment status, order status, and identifies the vehicle associated with the order.
 */
<<<<<<< HEAD
@Table(name = "orders")
@Entity
=======

>>>>>>> db94af9e660c4ae21c88f9f9e6f577c5b5315a99
public class Order {

    @Id
    @GeneratedValue
    private long id;
    private int vehicleId;
    private boolean downPayment;
    private OrderStatus orderStatus;
<<<<<<< HEAD

=======
    private boolean isPaid;
    private Vehicle vehicle;
>>>>>>> db94af9e660c4ae21c88f9f9e6f577c5b5315a99

    public Order(boolean downPayment, OrderStatus orderStatus, boolean isPaid, Vehicle vehicle) {
        this.downPayment = downPayment;
        this.isPaid = isPaid;
        this.orderStatus = orderStatus;
        this.vehicle = vehicle;
    }


    public Order(boolean downPayment, OrderStatus orderStatus, long vehicleId, User user) {
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
