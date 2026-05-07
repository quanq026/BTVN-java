package com.rikkei.session8.ex6595;

import java.util.Scanner;

public class Product {
    private static int AUTO_ID = 1;
    private static final Scanner SCANNER = new Scanner(System.in);

    private int id;
    private String name;
    private double price;
    public static final String WAREHOUSE_CODE = "KHO-01";

    public Product() {
        this.id = AUTO_ID++;
    }

    public Product(String name, double price) {
        this();
        this.name = name;
        this.price = price;
    }

    public void input() {
        input(SCANNER);
    }

    public void input(Scanner scanner) {
        System.out.print("Enter product name: ");
        this.name = scanner.nextLine().trim();
        System.out.print("Enter product price: ");
        this.price = Double.parseDouble(scanner.nextLine().trim());
    }

    public void print() {
        System.out.printf("Product{id=%d, code='%s', name='%s', price=%.2f}%n", id, WAREHOUSE_CODE, name, price);
    }

    public double getPrice() {
        return price;
    }

    public static int getTotalProduct() {
        return AUTO_ID - 1;
    }
}
