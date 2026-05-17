import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

class Exercise3 {
    interface Manage<T> {
        void add(T item);

        void update(int index, T item);

        void delete(int index);

        void display();
    }

    static class Invoice {
        private String invoiceCode;
        private double amount;

        Invoice(String invoiceCode, double amount) {
            this.invoiceCode = invoiceCode;
            this.amount = amount;
        }

        @Override
        public String toString() {
            return "Ma hoa don: " + invoiceCode + ", So tien: " + amount;
        }
    }

    static class InvoiceManager implements Manage<Invoice> {
        private final List<Invoice> invoices = new ArrayList<>();

        @Override
        public void add(Invoice item) {
            invoices.add(item);
        }

        @Override
        public void update(int index, Invoice item) {
            invoices.set(index, item);
        }

        @Override
        public void delete(int index) {
            invoices.remove(index);
        }

        @Override
        public void display() {
            if (invoices.isEmpty()) {
                System.out.println("Danh sach hoa don rong.");
                return;
            }
            for (int i = 0; i < invoices.size(); i++) {
                System.out.println((i + 1) + ". ID : " + (i + 1) + " , " + invoices.get(i));
            }
        }

        int size() {
            return invoices.size();
        }
    }

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final InvoiceManager MANAGER = new InvoiceManager();

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        int choice;
        do {
            showMenu();
            choice = Integer.parseInt(SCANNER.nextLine());
            switch (choice) {
                case 1 -> addInvoice();
                case 2 -> updateInvoice();
                case 3 -> deleteInvoice();
                case 4 -> MANAGER.display();
                case 5 -> System.out.println("Thoat chuong trinh.");
                default -> System.out.println("Lua chon khong hop le.");
            }
        } while (choice != 5);
    }

    private static void showMenu() {
        System.out.println("*************** MENU QUAN LY HOA DON ***************");
        System.out.println("1. Them hoa don");
        System.out.println("2. Sua hoa don");
        System.out.println("3. Xoa hoa don");
        System.out.println("4. Hien thi danh sach hoa don");
        System.out.println("5. Thoat");
        System.out.print("Lua chon cua ban: ");
    }

    private static Invoice inputInvoice() {
        String invoiceCode = inputNotEmpty("Nhap ma hoa don: ");
        double amount = inputPositiveDouble("Nhap so tien: ");
        return new Invoice(invoiceCode, amount);
    }

    private static void addInvoice() {
        MANAGER.add(inputInvoice());
        System.out.println("Hoa don da duoc them thanh cong.");
    }

    private static void updateInvoice() {
        MANAGER.display();
        int index = inputIndex("Nhap id hoa don can sua: ");
        if (index == -1) {
            return;
        }
        MANAGER.update(index, inputInvoice());
        System.out.println("Hoa don da duoc sua thanh cong.");
    }

    private static void deleteInvoice() {
        MANAGER.display();
        int index = inputIndex("Nhap id hoa don can xoa: ");
        if (index == -1) {
            return;
        }
        MANAGER.delete(index);
        System.out.println("Hoa don da duoc xoa thanh cong.");
    }

    private static int inputIndex(String message) {
        System.out.print(message);
        int index = Integer.parseInt(SCANNER.nextLine()) - 1;
        if (index < 0 || index >= MANAGER.size()) {
            System.out.println("Khong tim thay hoa don nao co id = " + (index + 1));
            return -1;
        }
        return index;
    }

    private static String inputNotEmpty(String message) {
        while (true) {
            System.out.print(message);
            String value = SCANNER.nextLine();
            if (!value.isBlank()) {
                return value;
            }
            System.out.println("Vui long ko de trong !");
        }
    }

    private static double inputPositiveDouble(String message) {
        while (true) {
            System.out.print(message);
            double value = Double.parseDouble(SCANNER.nextLine());
            if (value >= 0) {
                return value;
            }
            System.out.println("Vui long nhap so thuc >= 0 !");
        }
    }
}
