import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Exercise4 {
    interface Manage<T> {
        void add(T item);

        void update(int index, T item);

        void delete(int index);

        void display();
    }

    static class Order {
        private String orderCode;
        private String customerName;

        Order(String orderCode, String customerName) {
            this.orderCode = orderCode;
            this.customerName = customerName;
        }

        @Override
        public String toString() {
            return "Ma don hang: " + orderCode + ", Ten khach hang: " + customerName;
        }
    }

    static class OrderManager implements Manage<Order> {
        private final List<Order> orders = new ArrayList<>();

        @Override
        public void add(Order item) {
            orders.add(item);
        }

        @Override
        public void update(int index, Order item) {
            orders.set(index, item);
        }

        @Override
        public void delete(int index) {
            orders.remove(index);
        }

        @Override
        public void display() {
            if (orders.isEmpty()) {
                System.out.println("Danh sach don hang rong.");
                return;
            }
            for (int i = 0; i < orders.size(); i++) {
                System.out.println((i + 1) + ". " + orders.get(i));
            }
        }

        int size() {
            return orders.size();
        }
    }

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final OrderManager MANAGER = new OrderManager();

    public static void main(String[] args) {
        int choice;
        do {
            showMenu();
            choice = Integer.parseInt(SCANNER.nextLine());
            switch (choice) {
                case 1 -> addOrder();
                case 2 -> updateOrder();
                case 3 -> deleteOrder();
                case 4 -> MANAGER.display();
                case 5 -> System.out.println("Thoat chuong trinh.");
                default -> System.out.println("Lua chon khong hop le.");
            }
        } while (choice != 5);
    }

    private static void showMenu() {
        System.out.println("*************** MENU QUAN LY DON HANG ***************");
        System.out.println("1. Them don hang");
        System.out.println("2. Sua don hang");
        System.out.println("3. Xoa don hang");
        System.out.println("4. Hien thi danh sach don hang");
        System.out.println("5. Thoat");
        System.out.print("Lua chon cua ban: ");
    }

    private static Order inputOrder() {
        String orderCode = inputNotEmpty("Nhap ma don hang: ");
        String customerName = inputNotEmpty("Nhap ten khach hang: ");
        return new Order(orderCode, customerName);
    }

    private static void addOrder() {
        MANAGER.add(inputOrder());
        System.out.println("Don hang da duoc them thanh cong.");
    }

    private static void updateOrder() {
        MANAGER.display();
        int index = inputIndex("Nhap ma don hang can sua: ");
        if (index == -1) {
            return;
        }
        MANAGER.update(index, inputOrder());
        System.out.println("Don hang da duoc sua thanh cong.");
    }

    private static void deleteOrder() {
        MANAGER.display();
        int index = inputIndex("Nhap ma don hang can xoa: ");
        if (index == -1) {
            return;
        }
        MANAGER.delete(index);
        System.out.println("Don hang da duoc xoa thanh cong.");
    }

    private static int inputIndex(String message) {
        System.out.print(message);
        int index = Integer.parseInt(SCANNER.nextLine()) - 1;
        if (index < 0 || index >= MANAGER.size()) {
            System.out.println("Vi tri khong hop le.");
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
}
