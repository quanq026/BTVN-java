package model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Order {
    private int id;
    private int customerId;
    private int productId;
    private int quantity;
    private LocalDate orderDate;
    private BigDecimal totalAmount;

    public Order() {
    }

    public Order(int id, int customerId, int productId, int quantity, LocalDate orderDate, BigDecimal totalAmount) {
        this.id = id;
        this.customerId = customerId;
        this.productId = productId;
        this.quantity = quantity;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
    }

    public int getId() {
        return id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    @Override
    public String toString() {
        return "Order{id=" + id + ", customerId=" + customerId + ", productId=" + productId +
                ", quantity=" + quantity + ", orderDate=" + orderDate + ", totalAmount=" + totalAmount + '}';
    }
}
