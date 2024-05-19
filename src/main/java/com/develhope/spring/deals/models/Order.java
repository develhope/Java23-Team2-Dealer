package com.develhope.spring.deals.models;

/**
 * Represents an order or purchase. This class includes details about
 * the payment status, order status, and identifies the vehicle associated with the order.
 */
public class Order {
    private boolean downPayment;
    private OrderStatus orderStatus;
    private int vehicleId;


    public Order(boolean downPayment, OrderStatus orderStatus, int vehicleId) {
        this.downPayment = downPayment;
        this.orderStatus = orderStatus;
        this.vehicleId = vehicleId;
    }

    public boolean getDownPayment() {
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
