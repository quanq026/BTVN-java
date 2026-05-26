package model;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private int orderId;
    private final List<Product> products = new ArrayList<>();

    public Order() {
    }

    public Order(int orderId) {
        this.orderId = orderId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public double getTotalMoney() {
        double total = 0;

        for (Product product : products) {
            total += product.getPrice();
        }

        return total;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", products=" + products +
                ", totalMoney=" + String.format("%.0f", getTotalMoney()) +
                '}';
    }
}
