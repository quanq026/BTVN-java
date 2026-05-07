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
        static final String WAREHOUSE_CODE = "KHO-01";

        Product() {
            this.id = AUTO_ID++;
        }

        Product(String name, double price) {
            this();
            this.name = name;
            this.price = price;
        }

        void input() {
            input(SCANNER);
        }

        void input(Scanner scanner) {
            System.out.print("Enter product name: ");
            this.name = scanner.nextLine().trim();
            System.out.print("Enter product price: ");
            this.price = Double.parseDouble(scanner.nextLine().trim());
        }

        void print() {
            System.out.printf("Product{id=%d, code='%s', name='%s', price=%.2f}%n", id, WAREHOUSE_CODE, name, price);
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
            System.out.println("1. Add product");
            System.out.println("2. Show products");
            System.out.println("3. Search by price range");
            System.out.println("4. Product count");
            System.out.println("5. Exit");
            System.out.print("Choose: ");

            int choice = Integer.parseInt(scanner.nextLine().trim());
            switch (choice) {
                case 1 -> {
                    Product product = new Product();
                    product.input(scanner);
                    products.add(product);
                }
                case 2 -> products.forEach(Product::print);
                case 3 -> {
                    System.out.print("Min price: ");
                    double min = Double.parseDouble(scanner.nextLine().trim());
                    System.out.print("Max price: ");
                    double max = Double.parseDouble(scanner.nextLine().trim());
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
                        System.out.println("No product in that range.");
                    }
                }
                case 4 -> System.out.println("Total products created: " + Product.getTotalProduct());
                case 5 -> {
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }
}
