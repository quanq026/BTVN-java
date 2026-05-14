package ra.entity;

import java.util.Scanner;

public class InvoiceDetail {
    private Product product;
    private int quantity;
    private double subTotal;

    public InvoiceDetail() {
    }

    public Product getProduct() {
        return product;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void inputData(Scanner scanner, Product[] arrProd, int prodIndex) {
        if (prodIndex == 0) {
            System.out.println("Chua co san pham.");
            return;
        }

        while (true) {
            System.out.print("Nhap ma san pham: ");
            String productId = scanner.nextLine();
            product = findProduct(arrProd, prodIndex, productId);
            if (product != null) {
                break;
            }
            System.out.println("Khong tim thay san pham.");
        }

        while (true) {
            System.out.print("Nhap so luong mua: ");
            quantity = Integer.parseInt(scanner.nextLine());
            if (quantity > 0) {
                break;
            }
            System.out.println("So luong phai lon hon 0.");
        }

        subTotal = product.getPrice() * quantity;
    }

    private Product findProduct(Product[] arrProd, int prodIndex, String productId) {
        for (int i = 0; i < prodIndex; i++) {
            if (arrProd[i].getProductId().equalsIgnoreCase(productId)) {
                return arrProd[i];
            }
        }
        return null;
    }

    public void displayData() {
        System.out.printf("San pham: %s | So luong: %d | Thanh tien: %.2f%n",
                product.getProductName(), quantity, subTotal);
    }
}
