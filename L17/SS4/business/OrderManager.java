package business;

import model.Customer;
import model.Order;
import model.Product;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OrderManager {
    public boolean addProduct(Product product) throws SQLException {
        if (productNameExists(product.getName())) {
            return false;
        }
        String sql = "INSERT INTO products(name, price) VALUES (?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, product.getName());
            statement.setBigDecimal(2, product.getPrice());
            return statement.executeUpdate() > 0;
        }
    }

    public boolean updateCustomer(int customerId, Customer customer) throws SQLException {
        String sql = "UPDATE customers SET name = ?, email = ? WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, customer.getName());
            statement.setString(2, customer.getEmail());
            statement.setInt(3, customerId);
            return statement.executeUpdate() > 0;
        }
    }

    public boolean createOrder(Order order) throws SQLException {
        BigDecimal price = findProductPrice(order.getProductId());
        if (price == null || !customerExists(order.getCustomerId())) {
            return false;
        }
        BigDecimal total = price.multiply(BigDecimal.valueOf(order.getQuantity()));
        String sql = "INSERT INTO orders(customer_id, product_id, quantity, order_date, total_amount) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, order.getCustomerId());
            statement.setInt(2, order.getProductId());
            statement.setInt(3, order.getQuantity());
            statement.setDate(4, Date.valueOf(order.getOrderDate()));
            statement.setBigDecimal(5, total);
            return statement.executeUpdate() > 0;
        }
    }

    public List<String> listAllOrders() throws SQLException {
        List<String> orders = new ArrayList<>();
        String sql = "SELECT o.id, c.name, o.order_date, o.total_amount FROM orders o JOIN customers c ON o.customer_id = c.id";
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                orders.add("Order{id=" + resultSet.getInt("id") + ", customer='" + resultSet.getString("name") +
                        "', orderDate=" + resultSet.getDate("order_date") + ", totalAmount=" + resultSet.getBigDecimal("total_amount") + '}');
            }
        }
        return orders;
    }

    public List<String> getOrdersByCustomer(int customerId) throws SQLException {
        List<String> orders = new ArrayList<>();
        String sql = "SELECT id, order_date, total_amount FROM orders WHERE customer_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, customerId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    orders.add("Order{id=" + resultSet.getInt("id") + ", customerId=" + customerId +
                            ", orderDate=" + resultSet.getDate("order_date") + ", totalAmount=" + resultSet.getBigDecimal("total_amount") + '}');
                }
            }
        }
        return orders;
    }

    private boolean productNameExists(String name) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT id FROM products WHERE name = ?")) {
            statement.setString(1, name);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        }
    }

    private boolean customerExists(int id) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT id FROM customers WHERE id = ?")) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        }
    }

    private BigDecimal findProductPrice(int id) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT price FROM products WHERE id = ?")) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next() ? resultSet.getBigDecimal("price") : null;
            }
        }
    }
}
