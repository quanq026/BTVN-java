package presentation;

import business.OrderManager;
import model.Customer;
import model.Order;
import model.Product;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final OrderManager orderManager = new OrderManager();

    public static void main(String[] args) {
        int choice;
        do {
            showMenu();
            choice = inputInteger("Lựa chọn của bạn: ");
            try {
                switch (choice) {
                    case 1: addProduct(); break;
                    case 2: updateCustomer(); break;
                    case 3: createOrder(); break;
                    case 4: displayList(orderManager.listAllOrders()); break;
                    case 5: displayList(orderManager.getOrdersByCustomer(inputInteger("Nhập ID khách hàng: "))); break;
                    case 0: System.out.println("Thoát chương trình!"); break;
                    default: System.out.println("Lựa chọn không hợp lệ!");
                }
            } catch (SQLException e) {
                System.out.println("Lỗi thao tác cơ sở dữ liệu: " + e.getMessage());
            }
        } while (choice != 0);
    }

    private static void showMenu() {
        System.out.println("====== MENU QUẢN LÝ ĐƠN HÀNG ======");
        System.out.println("1. Thêm sản phẩm mới");
        System.out.println("2. Cập nhật khách hàng");
        System.out.println("3. Tạo đơn hàng mới");
        System.out.println("4. Hiển thị đơn hàng");
        System.out.println("5. Tìm đơn hàng theo khách hàng");
        System.out.println("0. Thoát");
    }

    private static void addProduct() throws SQLException {
        String name = inputRequiredString("Nhập tên sản phẩm: ");
        BigDecimal price = inputBigDecimal("Nhập giá sản phẩm: ");
        System.out.println(orderManager.addProduct(new Product(0, name, price)) ? "Thêm sản phẩm thành công!" : "Tên sản phẩm đã tồn tại!");
    }

    private static void updateCustomer() throws SQLException {
        int id = inputInteger("Nhập ID khách hàng: ");
        String name = inputRequiredString("Nhập tên khách hàng: ");
        String email = inputRequiredString("Nhập email: ");
        System.out.println(orderManager.updateCustomer(id, new Customer(id, name, email)) ? "Cập nhật khách hàng thành công!" : "Không tìm thấy khách hàng!");
    }

    private static void createOrder() throws SQLException {
        int customerId = inputInteger("Nhập ID khách hàng: ");
        int productId = inputInteger("Nhập ID sản phẩm: ");
        int quantity = inputInteger("Nhập số lượng: ");
        Order order = new Order(0, customerId, productId, quantity, LocalDate.now(), BigDecimal.ZERO);
        System.out.println(orderManager.createOrder(order) ? "Tạo đơn hàng thành công!" : "Khách hàng hoặc sản phẩm không tồn tại!");
    }

    private static void displayList(List<String> values) {
        if (values.isEmpty()) {
            System.out.println("Không có dữ liệu phù hợp!");
            return;
        }
        values.forEach(System.out::println);
    }

    private static String inputRequiredString(String message) {
        System.out.print(message);
        String value = scanner.nextLine().trim();
        if (value.isEmpty()) {
            throw new IllegalArgumentException("Không được nhập trống!");
        }
        return value;
    }

    private static int inputInteger(String message) {
        while (true) {
            try {
                System.out.print(message);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số nguyên hợp lệ!");
            }
        }
    }

    private static BigDecimal inputBigDecimal(String message) {
        while (true) {
            try {
                System.out.print(message);
                return new BigDecimal(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số hợp lệ!");
            }
        }
    }
}
