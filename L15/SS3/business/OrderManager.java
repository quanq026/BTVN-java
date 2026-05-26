package business;

import model.Order;
import model.Product;

import java.util.LinkedHashMap;
import java.util.Map;

public class OrderManager {
    private final Map<String, Order> orders = new LinkedHashMap<>();

    public void createOrder(int orderId) throws StoreException {
        String key = String.valueOf(orderId);

        if (orders.containsKey(key)) {
            throw new StoreException("Lỗi: Mã đơn hàng đã tồn tại!");
        }

        orders.put(key, new Order(orderId));
    }

    public void addProductToOrder(int orderId, Product product) throws StoreException {
        Order order = findOrder(orderId);

        if (product == null) {
            throw new StoreException("Lỗi: Không tìm thấy sản phẩm!");
        }

        order.addProduct(product);
    }

    public Order findOrder(int orderId) throws StoreException {
        Order order = orders.get(String.valueOf(orderId));

        if (order == null) {
            throw new StoreException("Lỗi: Không tìm thấy đơn hàng!");
        }

        return order;
    }

    public Map<String, Order> getOrders() {
        return orders;
    }
}
