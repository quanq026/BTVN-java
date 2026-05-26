package presentation;

import business.OrderManager;
import model.Order;

import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        OrderManager orderManager = new OrderManager();
        LocalDate fromDate = LocalDate.of(2025, 3, 17);
        LocalDate toDate = LocalDate.of(2025, 3, 23);

        System.out.println("Đơn hàng đã giao:");
        printOrders(orderManager.getDeliveredOrders());

        System.out.println();
        System.out.println("Đơn hàng chưa giao:");
        printOrders(orderManager.getUndeliveredOrders());

        System.out.println();
        System.out.println("Số đơn hàng đã giao từ 2025-03-17 đến 2025-03-23: "
                + orderManager.countDeliveredOrdersBetween(fromDate, toDate));
    }

    private static void printOrders(List<Order> orders) {
        System.out.println("ID | Tên KH | Ngày đặt | Ngày giao");
        for (Order order : orders) {
            System.out.println(order.toDisplayString());
        }
    }
}
