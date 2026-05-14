package ra.entity;

import java.util.Scanner;

public class Product {
    private String productId;
    private String productName;
    private double price;
    private ProductStatus status;

    public Product() {
    }

    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public double getPrice() {
        return price;
    }

    public void inputData(Scanner scanner, Product[] arrProd, int index) {
        while (true) {
            System.out.print("Nhap ma san pham (C001/S001/A001): ");
            String inputId = scanner.nextLine().toUpperCase();
            if (!inputId.matches("[CSA]\\d{3}")) {
                System.out.println("Ma san pham khong hop le.");
                continue;
            }
            boolean duplicate = false;
            for (int i = 0; i < index; i++) {
                if (arrProd[i] != null && arrProd[i].productId.equals(inputId)) {
                    duplicate = true;
                    break;
                }
            }
            if (!duplicate) {
                productId = inputId;
                break;
            }
            System.out.println("Ma san pham da ton tai.");
        }

        inputProductName(scanner, arrProd, index);

        while (true) {
            System.out.print("Nhap gia ban: ");
            price = Double.parseDouble(scanner.nextLine());
            if (price > 0) {
                break;
            }
            System.out.println("Gia ban phai lon hon 0.");
        }

        status = inputStatus(scanner);
    }

    public void inputProductName(Scanner scanner, Product[] arrProd, int index) {
        while (true) {
            System.out.print("Nhap ten san pham: ");
            String inputName = scanner.nextLine();
            if (inputName.length() < 10 || inputName.length() > 50) {
                System.out.println("Ten phai tu 10 den 50 ky tu.");
                continue;
            }
            boolean duplicate = false;
            for (int i = 0; i < index; i++) {
                if (arrProd[i] != null
                        && arrProd[i] != this
                        && arrProd[i].productName.equalsIgnoreCase(inputName)) {
                    duplicate = true;
                    break;
                }
            }
            if (!duplicate) {
                productName = inputName;
                break;
            }
            System.out.println("Ten san pham da ton tai.");
        }
    }

    public void updateInfo(Scanner scanner, Product[] arrProd, int index) {
        inputProductName(scanner, arrProd, index);
        System.out.print("Nhap gia ban moi: ");
        price = Double.parseDouble(scanner.nextLine());
        status = inputStatus(scanner);
    }

    public static ProductStatus inputStatus(Scanner scanner) {
        while (true) {
            System.out.print("Nhap trang thai (AVAILABLE/OUT_OF_STOCK/STOP_SELLING): ");
            try {
                return ProductStatus.valueOf(scanner.nextLine().toUpperCase());
            } catch (IllegalArgumentException exception) {
                System.out.println("Trang thai khong hop le.");
            }
        }
    }

    public void displayData() {
        System.out.printf("ID: %s | Ten: %s | Gia: %.2f | Trang thai: %s%n",
                productId, productName, price, status);
    }
}
