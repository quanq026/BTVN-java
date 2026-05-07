import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Exercise6 {
    static class Product {
        private static int AUTO_ID = 1;
        private static final Scanner SCANNER = new Scanner(System.in);

        private int id;
        private String name;
        private double price;
        private final String WAREHOUSE_CODE = "KHO-01";

        Product() {
            id = AUTO_ID++;
        }

        Product(String name, double price) {
            this();
            this.name = name;
            this.price = price;
        }

        void input() {
            System.out.print("Nhap ten san pham: ");
            name = SCANNER.nextLine();
            System.out.print("Nhap gia san pham: ");
            price = Double.parseDouble(SCANNER.nextLine());
        }

        void print() {
            System.out.printf("ID: %d, Name: %s, Price: %.2f, Warehouse: %s%n",
                    id, name, price, WAREHOUSE_CODE);
        }

        double getPrice() {
            return price;
        }

        static int getTotalProduct() {
            return AUTO_ID - 1;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Product> products = new ArrayList<>();

        while (true) {
            System.out.println("1. Nhap danh sach san pham");
            System.out.println("2. In danh sach san pham");
            System.out.println("3. Tim san pham theo khoang gia");
            System.out.println("4. Hien thi tong so san pham da tao");
            System.out.println("5. Thoat");
            System.out.print("Chon: ");

            int choice = Integer.parseInt(scanner.nextLine());

            if (choice == 1) {
                System.out.print("Nhap so san pham: ");
                int number = Integer.parseInt(scanner.nextLine());
                for (int i = 0; i < number; i++) {
                    Product product = new Product();
                    System.out.println("San pham thu " + (i + 1) + ":");
                    product.input();
                    products.add(product);
                }
            } else if (choice == 2) {
                if (products.isEmpty()) {
                    System.out.println("Danh sach rong.");
                } else {
                    for (Product product : products) {
                        product.print();
                    }
                }
            } else if (choice == 3) {
                System.out.print("Nhap gia nho nhat: ");
                double min = Double.parseDouble(scanner.nextLine());
                System.out.print("Nhap gia lon nhat: ");
                double max = Double.parseDouble(scanner.nextLine());
                if (min > max) {
                    double temp = min;
                    min = max;
                    max = temp;
                }

                boolean found = false;
                for (Product product : products) {
                    if (product.getPrice() >= min && product.getPrice() <= max) {
                        product.print();
                        found = true;
                    }
                }
                if (!found) {
                    System.out.println("Khong tim thay san pham phu hop.");
                }
            } else if (choice == 4) {
                System.out.println("Tong so san pham da tao: " + Product.getTotalProduct());
            } else if (choice == 5) {
                break;
            } else {
                System.out.println("Lua chon khong hop le.");
            }
        }
    }
}
