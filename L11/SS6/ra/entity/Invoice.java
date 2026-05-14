package ra.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Invoice {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    private String invoiceId;
    private String customerName;
    private Date invoiceDate;
    private InvoiceDetail[] invoiceDetails = new InvoiceDetail[100];
    private int detailCount = 0;
    private double totalAmount;

    public Invoice() {
        DATE_FORMAT.setLenient(false);
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public boolean hasProduct(String productId) {
        for (int i = 0; i < detailCount; i++) {
            if (invoiceDetails[i].getProduct().getProductId().equalsIgnoreCase(productId)) {
                return true;
            }
        }
        return false;
    }

    public void inputData(Scanner scanner, Product[] arrProd, int prodIndex) {
        while (true) {
            System.out.print("Nhap ma hoa don (HD0001): ");
            invoiceId = scanner.nextLine().toUpperCase();
            if (invoiceId.matches("HD\\d{4}")) {
                break;
            }
            System.out.println("Ma hoa don khong hop le.");
        }

        System.out.print("Nhap ten khach hang: ");
        customerName = scanner.nextLine();

        while (true) {
            try {
                System.out.print("Nhap ngay lap hoa don (yyyy-MM-dd): ");
                invoiceDate = DATE_FORMAT.parse(scanner.nextLine());
                break;
            } catch (ParseException exception) {
                System.out.println("Ngay khong hop le.");
            }
        }

        System.out.print("Nhap so san pham trong hoa don: ");
        int count = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < count; i++) {
            InvoiceDetail detail = new InvoiceDetail();
            detail.inputData(scanner, arrProd, prodIndex);
            invoiceDetails[detailCount++] = detail;
        }
        calculateTotalAmount();
    }

    public void calculateTotalAmount() {
        totalAmount = 0;
        for (int i = 0; i < detailCount; i++) {
            totalAmount += invoiceDetails[i].getSubTotal();
        }
    }

    public void displayData() {
        System.out.printf("ID: %s | Khach hang: %s | Ngay: %s | Tong tien: %.2f%n",
                invoiceId, customerName, DATE_FORMAT.format(invoiceDate), totalAmount);
        for (int i = 0; i < detailCount; i++) {
            invoiceDetails[i].displayData();
        }
    }
}
