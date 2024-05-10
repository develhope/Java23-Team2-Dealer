package com.develhope.spring.model;

/**
 * Represents an order or purchase. This class includes details about
 * the payment status, order status, and identifies the vehicle associated with the order.
 */
public class Order {
    private boolean downPayment;
    private boolean paid;
    private OrderStatus orderStatus;
    private String vehicleId;

    /**
     * Constructs a new Order with specified characteristics.
     *
     * @param downPayment Indicates whether a down payment has been made.
     * @param paid Indicates whether the order has been fully paid.
     * @param orderStatus The current status of the order.
     * @param vehicleId The identifier for the vehicle associated with this order.
     */
    public Order(boolean downPayment, boolean paid, OrderStatus orderStatus, String vehicleId) {
        this.downPayment = downPayment;
        this.paid = paid;
        this.orderStatus = orderStatus;
        this.vehicleId = vehicleId;
    }

    /**
     * Returns true if a down payment has been made.
     *
     * @return true if down payment is made, false otherwise.
     */
    public boolean hasDownPayment() {
        return downPayment;
    }

    /**
     * Sets the down payment status of this order.
     *
     * @param downPayment true if a down payment is made, false otherwise.
     */
    public void setDownPayment(boolean downPayment) {
        this.downPayment = downPayment;
    }

    /**
     * Returns true if the order has been fully paid.
     *
     * @return true if fully paid, false otherwise.
     */
    public boolean isPaid() {
        return paid;
    }

    /**
     * Sets the payment status of the order.
     *
     * @param paid true if the order is fully paid, false otherwise.
     */
    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    /**
     * Gets the current status of the order.
     *
     * @return the current order status.
     */
    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    /**
     * Sets the order status.
     *
     * @param orderStatus the new status of the order.
     */
    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    /**
     * Returns the vehicle ID associated with this order.
     *
     * @return the vehicle ID.
     */
    public String getVehicleId() {
        return vehicleId;
    }

    /**
     * Sets the vehicle ID for this order.
     *
     * @param vehicleId the new vehicle ID.
     */
    public void setVehicleId(String vehicleId) {
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
                ", Pagato =" + paid +
                ", Stato dell'ordine = " + orderStatus +
                ", Id del veicolo = " + vehicleId +
                '}';
    }
}
