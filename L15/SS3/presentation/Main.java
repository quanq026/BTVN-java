package presentation;

import business.OrderManager;
import business.ProductManager;
import business.StoreException;
import model.Order;
import model.Product;

import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ProductManager productManager = new ProductManager();
    private static final OrderManager orderManager = new OrderManager();

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        int choice;

        do {
            showMenu();
            choice = inputInteger("Lựa chọn của bạn: ");

            switch (choice) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    removeProduct();
                    break;
                case 3:
                    displayProducts();
                    break;
                case 4:
                    createOrder();
                    break;
                case 5:
                    addProductToOrder();
                    break;
                case 6:
                    displayOrder();
                    break;
                case 0:
                    System.out.println("Thoát chương trình!");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        } while (choice != 0);
    }

    private static void showMenu() {
        System.out.println("================= MENU =================");
        System.out.println("1. Thêm sản phẩm");
        System.out.println("2. Xóa sản phẩm");
        System.out.println("3. Hiển thị sản phẩm");
        System.out.println("4. Tạo đơn hàng");
        System.out.println("5. Thêm sản phẩm vào đơn hàng");
        System.out.println("6. Hiển thị đơn hàng");
        System.out.println("0. Thoát");
        System.out.println("========================================");
    }

    private static void addProduct() {
        try {
            int id = inputInteger("Nhập mã sản phẩm: ");
            System.out.print("Nhập tên sản phẩm: ");
            String name = scanner.nextLine();
            double price = inputDouble("Nhập giá sản phẩm: ");
            productManager.addProduct(new Product(id, name, price));
            System.out.println("Thêm sản phẩm thành công!");
        } catch (StoreException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void removeProduct() {
        try {
            int id = inputInteger("Nhập mã sản phẩm cần xóa: ");
            productManager.removeProduct(id);
            System.out.println("Xóa sản phẩm thành công!");
        } catch (StoreException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void displayProducts() {
        if (productManager.getProducts().isEmpty()) {
            System.out.println("Danh sách sản phẩm trống!");
            return;
        }

        for (Product product : productManager.getProducts()) {
            System.out.println(product);
        }
    }

    private static void createOrder() {
        try {
            int orderId = inputInteger("Nhập mã đơn hàng: ");
            orderManager.createOrder(orderId);
            System.out.println("Tạo đơn hàng thành công!");
        } catch (StoreException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void addProductToOrder() {
        try {
            int orderId = inputInteger("Nhập mã đơn hàng: ");
            int productId = inputInteger("Nhập mã sản phẩm: ");
            Product product = productManager.findById(productId);
            orderManager.addProductToOrder(orderId, product);
            System.out.println("Thêm sản phẩm vào đơn hàng thành công!");
        } catch (StoreException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void displayOrder() {
        if (orderManager.getOrders().isEmpty()) {
            System.out.println("Danh sách đơn hàng trống!");
            return;
        }

        for (Map.Entry<String, Order> entry : orderManager.getOrders().entrySet()) {
            System.out.println(entry.getValue());
        }
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

    private static double inputDouble(String message) {
        while (true) {
            try {
                System.out.print(message);
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số hợp lệ!");
            }
        }
    }
}
