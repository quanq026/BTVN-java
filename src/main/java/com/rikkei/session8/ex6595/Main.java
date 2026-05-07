package com.rikkei.session8.ex6595;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
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
