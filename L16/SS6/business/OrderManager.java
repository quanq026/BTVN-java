package business;

import model.Order;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OrderManager {
    private final List<Order> orders = new ArrayList<>();

    public OrderManager() {
        orders.add(new Order(1, "Nguyễn Văn A", LocalDate.of(2025, 3, 15), LocalDate.of(2025, 3, 17)));
        orders.add(new Order(2, "Trần Thị B", LocalDate.of(2025, 3, 16), null));
        orders.add(new Order(3, "Lê Văn C", LocalDate.of(2025, 3, 18), LocalDate.of(2025, 3, 20)));
        orders.add(new Order(4, "Phạm Thị D", LocalDate.of(2025, 3, 21), LocalDate.of(2025, 3, 24)));
        orders.add(new Order(5, "Hoàng Văn E", LocalDate.of(2025, 3, 22), null));
    }

    public List<Order> getDeliveredOrders() {
        return orders.stream()
                .filter(order -> order.getDeliveryDate().isPresent())
                .collect(Collectors.toList());
    }

    public List<Order> getUndeliveredOrders() {
        return orders.stream()
                .filter(order -> !order.getDeliveryDate().isPresent())
                .collect(Collectors.toList());
    }

    public long countDeliveredOrdersBetween(LocalDate fromDate, LocalDate toDate) {
        return orders.stream()
                .filter(order -> order.getDeliveryDate().isPresent())
                .filter(order -> {
                    LocalDate deliveryDate = order.getDeliveryDate().get();
                    return !deliveryDate.isBefore(fromDate) && !deliveryDate.isAfter(toDate);
                })
                .count();
    }
}
