package ra.presentation;

import ra.entity.Invoice;
import ra.entity.Product;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class InvoiceManagement {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private static final Product[] PRODUCTS = new Product[100];
    private static final Invoice[] INVOICES = new Invoice[100];
    private static int productCount = 0;
    private static int invoiceCount = 0;

    public static void main(String[] args) {
        int choice;
        do {
            showMainMenu();
            choice = Integer.parseInt(SCANNER.nextLine());
            switch (choice) {
                case 1 -> manageProducts();
                case 2 -> manageInvoices();
                case 3 -> showRevenueReports();
                case 4 -> System.out.println("Thoat chuong trinh.");
                default -> System.out.println("Lua chon khong hop le.");
            }
        } while (choice != 4);
    }

    private static void showMainMenu() {
        System.out.println("================ QUAN LY HOA DON ================");
        System.out.println("1. Quan ly san pham");
        System.out.println("2. Quan ly hoa don");
        System.out.println("3. Bao cao doanh thu");
        System.out.println("4. Thoat");
        System.out.println("=================================================");
        System.out.print("Lua chon cua ban: ");
    }

    private static void manageProducts() {
        int choice;
        do {
            showProductMenu();
            choice = Integer.parseInt(SCANNER.nextLine());
            switch (choice) {
                case 1 -> addProduct();
                case 2 -> showProducts();
                case 3 -> updateProduct();
                case 4 -> deleteProduct();
                case 5 -> searchProductByName();
                case 6 -> System.out.println("Quay lai menu chinh.");
                default -> System.out.println("Lua chon khong hop le.");
            }
        } while (choice != 6);
    }

    private static void showProductMenu() {
        System.out.println("================ QUAN LY SAN PHAM ================");
        System.out.println("1. Them san pham");
        System.out.println("2. Hien thi danh sach san pham");
        System.out.println("3. Cap nhat thong tin san pham");
        System.out.println("4. Xoa san pham (neu chua co trong hoa don nao)");
        System.out.println("5. Tim kiem san pham theo ten");
        System.out.println("6. Thoat");
        System.out.println("==================================================");
        System.out.print("Lua chon cua ban: ");
    }

    private static void addProduct() {
        Product product = new Product();
        product.inputData(SCANNER, PRODUCTS, productCount);
        PRODUCTS[productCount++] = product;
    }

    private static void showProducts() {
        if (productCount == 0) {
            System.out.println("Danh sach san pham rong.");
            return;
        }
        for (int i = 0; i < productCount; i++) {
            PRODUCTS[i].displayData();
        }
    }

    private static void updateProduct() {
        Product product = findProductById();
        if (product == null) {
            System.out.println("Khong tim thay san pham.");
            return;
        }
        product.updateInfo(SCANNER, PRODUCTS, productCount);
    }

    private static void deleteProduct() {
        Product product = findProductById();
        if (product == null) {
            System.out.println("Khong tim thay san pham.");
            return;
        }
        for (int i = 0; i < invoiceCount; i++) {
            if (INVOICES[i].hasProduct(product.getProductId())) {
                System.out.println("San pham da co trong hoa don, khong the xoa.");
                return;
            }
        }
        for (int i = 0; i < productCount; i++) {
            if (PRODUCTS[i] == product) {
                for (int j = i; j < productCount - 1; j++) {
                    PRODUCTS[j] = PRODUCTS[j + 1];
                }
                PRODUCTS[--productCount] = null;
                System.out.println("Da xoa san pham.");
                return;
            }
        }
    }

    private static void searchProductByName() {
        System.out.print("Nhap ten san pham can tim: ");
        String keyword = SCANNER.nextLine().toLowerCase();
        for (int i = 0; i < productCount; i++) {
            if (PRODUCTS[i].getProductName().toLowerCase().contains(keyword)) {
                PRODUCTS[i].displayData();
            }
        }
    }

    private static Product findProductById() {
        System.out.print("Nhap ma san pham: ");
        String productId = SCANNER.nextLine();
        for (int i = 0; i < productCount; i++) {
            if (PRODUCTS[i].getProductId().equalsIgnoreCase(productId)) {
                return PRODUCTS[i];
            }
        }
        return null;
    }

    private static void manageInvoices() {
        int choice;
        do {
            showInvoiceMenu();
            choice = Integer.parseInt(SCANNER.nextLine());
            switch (choice) {
                case 1 -> addInvoice();
                case 2 -> showInvoices();
                case 3 -> updateInvoice();
                case 4 -> deleteInvoice();
                case 5 -> searchInvoiceById();
                case 6 -> searchInvoiceByCustomerName();
                case 7 -> System.out.println("Quay lai menu chinh.");
                default -> System.out.println("Lua chon khong hop le.");
            }
        } while (choice != 7);
    }

    private static void showInvoiceMenu() {
        System.out.println("================ QUAN LY HOA DON ================");
        System.out.println("1. Them hoa don");
        System.out.println("2. Hien thi danh sach hoa don");
        System.out.println("3. Cap nhat thong tin hoa don");
        System.out.println("4. Xoa hoa don");
        System.out.println("5. Tim hoa don theo ma");
        System.out.println("6. Tim hoa don theo ten khach hang");
        System.out.println("7. Thoat");
        System.out.println("=================================================");
        System.out.print("Lua chon cua ban: ");
    }

    private static void addInvoice() {
        Invoice invoice = new Invoice();
        invoice.inputData(SCANNER, PRODUCTS, productCount);
        INVOICES[invoiceCount++] = invoice;
    }

    private static void showInvoices() {
        if (invoiceCount == 0) {
            System.out.println("Danh sach hoa don rong.");
            return;
        }
        for (int i = 0; i < invoiceCount; i++) {
            INVOICES[i].displayData();
        }
    }

    private static void updateInvoice() {
        Invoice invoice = findInvoiceById();
        if (invoice == null) {
            System.out.println("Khong tim thay hoa don.");
            return;
        }
        invoice.inputData(SCANNER, PRODUCTS, productCount);
    }

    private static void deleteInvoice() {
        Invoice invoice = findInvoiceById();
        if (invoice == null) {
            System.out.println("Khong tim thay hoa don.");
            return;
        }
        for (int i = 0; i < invoiceCount; i++) {
            if (INVOICES[i] == invoice) {
                for (int j = i; j < invoiceCount - 1; j++) {
                    INVOICES[j] = INVOICES[j + 1];
                }
                INVOICES[--invoiceCount] = null;
                System.out.println("Da xoa hoa don.");
                return;
            }
        }
    }

    private static void searchInvoiceById() {
        Invoice invoice = findInvoiceById();
        if (invoice == null) {
            System.out.println("Khong tim thay hoa don.");
        } else {
            invoice.displayData();
        }
    }

    private static void searchInvoiceByCustomerName() {
        System.out.print("Nhap ten khach hang can tim: ");
        String keyword = SCANNER.nextLine().toLowerCase();
        for (int i = 0; i < invoiceCount; i++) {
            if (INVOICES[i].getCustomerName().toLowerCase().contains(keyword)) {
                INVOICES[i].displayData();
            }
        }
    }

    private static Invoice findInvoiceById() {
        System.out.print("Nhap ma hoa don: ");
        String invoiceId = SCANNER.nextLine();
        for (int i = 0; i < invoiceCount; i++) {
            if (INVOICES[i].getInvoiceId().equalsIgnoreCase(invoiceId)) {
                return INVOICES[i];
            }
        }
        return null;
    }

    private static void showRevenueReports() {
        int choice;
        do {
            showRevenueMenu();
            choice = Integer.parseInt(SCANNER.nextLine());
            switch (choice) {
                case 1 -> calculateTotalRevenue();
                case 2 -> findMaxInvoice();
                case 3 -> countInvoiceByDateRange();
                case 4 -> revenueByDateRange();
                case 5 -> System.out.println("Quay lai menu chinh.");
                default -> System.out.println("Lua chon khong hop le.");
            }
        } while (choice != 5);
    }

    private static void showRevenueMenu() {
        System.out.println("================ QUAN LY DOANH THU ================");
        System.out.println("1. Tinh tong doanh thu tat ca hoa don");
        System.out.println("2. Tim hoa don co gia tri lon nhat");
        System.out.println("3. Thong ke so hoa don theo khoang ngay (nhap tu - den)");
        System.out.println("4. Thong ke tong doanh thu theo khoang ngay");
        System.out.println("5. Thoat");
        System.out.println("===================================================");
        System.out.print("Lua chon cua ban: ");
    }

    private static void calculateTotalRevenue() {
        double totalRevenue = 0;
        for (int i = 0; i < invoiceCount; i++) {
            totalRevenue += INVOICES[i].getTotalAmount();
        }
        System.out.printf("Tong doanh thu: %.2f%n", totalRevenue);
    }

    private static void findMaxInvoice() {
        if (invoiceCount == 0) {
            System.out.println("Danh sach hoa don rong.");
            return;
        }
        Invoice maxInvoice = INVOICES[0];
        for (int i = 1; i < invoiceCount; i++) {
            if (INVOICES[i].getTotalAmount() > maxInvoice.getTotalAmount()) {
                maxInvoice = INVOICES[i];
            }
        }
        maxInvoice.displayData();
    }

    private static void countInvoiceByDateRange() {
        Date[] dates = inputDateRange();
        int count = 0;
        for (int i = 0; i < invoiceCount; i++) {
            if (isInRange(INVOICES[i].getInvoiceDate(), dates[0], dates[1])) {
                count++;
            }
        }
        System.out.println("So hoa don trong khoang ngay: " + count);
    }

    private static void revenueByDateRange() {
        Date[] dates = inputDateRange();
        double totalRevenue = 0;
        for (int i = 0; i < invoiceCount; i++) {
            if (isInRange(INVOICES[i].getInvoiceDate(), dates[0], dates[1])) {
                totalRevenue += INVOICES[i].getTotalAmount();
            }
        }
        System.out.printf("Doanh thu trong khoang ngay: %.2f%n", totalRevenue);
    }

    private static Date[] inputDateRange() {
        while (true) {
            try {
                System.out.print("Nhap ngay bat dau (yyyy-MM-dd): ");
                Date startDate = DATE_FORMAT.parse(SCANNER.nextLine());
                System.out.print("Nhap ngay ket thuc (yyyy-MM-dd): ");
                Date endDate = DATE_FORMAT.parse(SCANNER.nextLine());
                return new Date[]{startDate, endDate};
            } catch (ParseException exception) {
                System.out.println("Ngay khong hop le.");
            }
        }
    }

    private static boolean isInRange(Date date, Date startDate, Date endDate) {
        return !date.before(startDate) && !date.after(endDate);
    }
}
