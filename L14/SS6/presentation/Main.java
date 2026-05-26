package presentation;

import business.CartException;
import business.ProductCatalog;
import business.ShoppingCart;
import model.CartItem;
import model.Product;

import java.util.Locale;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ProductCatalog productCatalog = new ProductCatalog();
    private static final ShoppingCart shoppingCart = new ShoppingCart();

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        int choice;

        do {
            showMenu();
            choice = inputInteger("Lựa chọn của bạn: ", "Lỗi: Vui lòng nhập một số hợp lệ!");

            switch (choice) {
                case 1:
                    displayProducts();
                    break;
                case 2:
                    addProductToCart();
                    break;
                case 3:
                    removeProductFromCart();
                    break;
                case 4:
                    displayCart();
                    break;
                case 5:
                    checkout();
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
        System.out.println("============== MENU ==============");
        System.out.println("1. Xem danh sách sản phẩm");
        System.out.println("2. Thêm sản phẩm vào giỏ hàng");
        System.out.println("3. Xóa sản phẩm khỏi giỏ hàng");
        System.out.println("4. Xem giỏ hàng");
        System.out.println("5. Thanh toán");
        System.out.println("0. Thoát");
        System.out.println("==================================");
    }

    private static void displayProducts() {
        for (Product product : productCatalog.getProducts()) {
            System.out.println(product);
        }
    }

    private static void addProductToCart() {
        try {
            String productId = inputString("Nhập mã sản phẩm: ");
            int quantity = inputInteger("Nhập số lượng: ", "Lỗi: Số lượng không hợp lệ!");
            Product product = productCatalog.findById(productId);
            shoppingCart.addToCart(product, quantity);
            System.out.println("Thêm sản phẩm vào giỏ hàng thành công!");
        } catch (CartException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void removeProductFromCart() {
        try {
            String productId = inputString("Nhập mã sản phẩm cần xóa: ");
            shoppingCart.removeFromCart(productId);
            System.out.println("Xóa sản phẩm khỏi giỏ hàng thành công!");
        } catch (CartException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void displayCart() {
        if (shoppingCart.getCartItems().isEmpty()) {
            System.out.println("Giỏ hàng đang trống!");
            return;
        }

        for (CartItem cartItem : shoppingCart.getCartItems()) {
            System.out.println(cartItem);
        }
    }

    private static void checkout() {
        displayCart();
        System.out.println("Tổng tiền cần thanh toán: " + String.format("%.0f", shoppingCart.checkout()));
    }

    private static String inputString(String message) {
        System.out.print(message);
        return scanner.nextLine().trim();
    }

    private static int inputInteger(String message, String errorMessage) {
        while (true) {
            try {
                System.out.print(message);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println(errorMessage);
            }
        }
    }
}
