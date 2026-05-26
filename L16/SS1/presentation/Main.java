package presentation;

import business.ProductManager;
import model.Product;

import java.util.Collection;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ProductManager productManager = new ProductManager();

    public static void main(String[] args) {
        int choice;

        do {
            showMenu();
            choice = inputInteger("Enter your choice: ");

            switch (choice) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    editProduct();
                    break;
                case 3:
                    deleteProduct();
                    break;
                case 4:
                    displayProducts(productManager.getAllProducts());
                    break;
                case 5:
                    System.out.println("Products with price greater than 100:");
                    displayProducts(productManager.filterProductsGreaterThan100());
                    break;
                case 6:
                    System.out.println("Total value of products: " + productManager.calculateTotalValue());
                    break;
                case 0:
                    System.out.println("Exit program.");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }

    private static void showMenu() {
        System.out.println("--- Product Management System ---");
        System.out.println("1. Add Product");
        System.out.println("2. Edit Product");
        System.out.println("3. Delete Product");
        System.out.println("4. Display Products");
        System.out.println("5. Filter Products (Price > 100)");
        System.out.println("6. Total Value of Products");
        System.out.println("0. Exit");
    }

    private static void addProduct() {
        int id = inputInteger("Enter Product ID: ");
        System.out.print("Enter Product Name: ");
        String name = scanner.nextLine();
        double price = inputDouble("Enter Product Price: ");

        if (productManager.addProduct(new Product(id, name, price))) {
            System.out.println("Product added successfully.");
        } else {
            System.out.println("Product ID already exists.");
        }
    }

    private static void editProduct() {
        int id = inputInteger("Enter Product ID to edit: ");
        System.out.print("Enter new Product Name: ");
        String name = scanner.nextLine();
        double price = inputDouble("Enter new Product Price: ");

        if (productManager.updateProduct(id, name, price)) {
            System.out.println("Product updated successfully.");
        } else {
            System.out.println("Product not found.");
        }
    }

    private static void deleteProduct() {
        int id = inputInteger("Enter Product ID to delete: ");

        if (productManager.deleteProduct(id)) {
            System.out.println("Product deleted successfully.");
        } else {
            System.out.println("Product not found.");
        }
    }

    private static void displayProducts(Collection<Product> products) {
        if (products.isEmpty()) {
            System.out.println("No products found.");
            return;
        }

        for (Product product : products) {
            System.out.println(product);
        }
    }

    private static int inputInteger(String message) {
        while (true) {
            try {
                System.out.print(message);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid integer.");
            }
        }
    }

    private static double inputDouble(String message) {
        while (true) {
            try {
                System.out.print(message);
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
}
