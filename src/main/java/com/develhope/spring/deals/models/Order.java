package com.develhope.spring.deals.models;

import com.develhope.spring.users.models.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

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
    private int vehicleId;
    private boolean downPayment;
    private OrderStatus orderStatus;



    public Order(boolean downPayment, OrderStatus orderStatus, int vehicleId) {
        this.downPayment = downPayment;
        this.orderStatus = orderStatus;
        this.vehicleId = vehicleId;
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


    public int getVehicleId() {
        return vehicleId;
    }


    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
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
                ", Id del veicolo = " + vehicleId +
                '}';
    }
}
