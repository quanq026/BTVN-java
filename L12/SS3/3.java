import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Exercise3 {
    interface IPromotion {
        void applyDiscount(double percentage);
    }

    static abstract class Drink implements IPromotion {
        private String id;
        private String name;
        private double price;

        Drink(String id, String name, double price) {
            this.id = id;
            this.name = name;
            this.price = price;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        abstract String prepare();

        @Override
        public void applyDiscount(double percentage) {
            price = price - price * percentage / 100;
        }

        void displayInfo() {
            System.out.printf("ID: %s | Ten mon: %s | Cach pha: %s | Gia: %.2f%n",
                    id, name, prepare(), price);
        }
    }

    static class Coffee extends Drink {
        Coffee(String id, String name, double price) {
            super(id, name, price);
        }

        @Override
        String prepare() {
            return "Pha bang may";
        }
    }

    static class FruitTea extends Drink {
        FruitTea(String id, String name, double price) {
            super(id, name, price);
        }

        @Override
        String prepare() {
            return "Lac voi da va trai cay tuoi";
        }
    }

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final List<Drink> DRINKS = new ArrayList<>();

    public static void main(String[] args) {
        int choice;
        do {
            showMenu();
            choice = Integer.parseInt(SCANNER.nextLine());
            switch (choice) {
                case 1 -> addDrink();
                case 2 -> showDrinks();
                case 3 -> applyDiscount();
                case 4 -> deleteDrink();
                case 5 -> showAveragePrice();
                case 6 -> System.out.println("Thoat chuong trinh.");
                default -> System.out.println("Lua chon khong hop le.");
            }
        } while (choice != 6);
    }

    private static void showMenu() {
        System.out.println("===== COFFEESHOP PRO =====");
        System.out.println("1. Them mon vao menu");
        System.out.println("2. Hien thi menu");
        System.out.println("3. Ap dung ma giam gia");
        System.out.println("4. Xoa mon");
        System.out.println("5. Thong ke gia trung binh");
        System.out.println("6. Thoat");
        System.out.print("Chon: ");
    }

    private static void addDrink() {
        System.out.println("1. Ca phe");
        System.out.println("2. Tra trai cay");
        System.out.print("Chon loai: ");
        int type = Integer.parseInt(SCANNER.nextLine());
        System.out.print("Nhap id: ");
        String id = SCANNER.nextLine();
        System.out.print("Nhap ten mon: ");
        String name = SCANNER.nextLine();
        System.out.print("Nhap gia: ");
        double price = Double.parseDouble(SCANNER.nextLine());

        if (type == 1) {
            DRINKS.add(new Coffee(id, name, price));
        } else if (type == 2) {
            DRINKS.add(new FruitTea(id, name, price));
        } else {
            System.out.println("Loai khong hop le.");
        }
    }

    private static void showDrinks() {
        if (DRINKS.isEmpty()) {
            System.out.println("Menu rong.");
            return;
        }
        for (Drink drink : DRINKS) {
            drink.displayInfo();
        }
    }

    private static void applyDiscount() {
        System.out.print("Nhap phan tram giam gia: ");
        double percentage = Double.parseDouble(SCANNER.nextLine());
        for (Drink drink : DRINKS) {
            drink.applyDiscount(percentage);
        }
        System.out.println("Da ap dung giam gia.");
    }

    private static void deleteDrink() {
        System.out.print("Nhap id mon can xoa: ");
        String id = SCANNER.nextLine();
        Drink drink = findDrinkById(id);
        if (drink == null) {
            System.out.println("Khong tim thay mon.");
            return;
        }
        DRINKS.remove(drink);
        System.out.println("Da xoa mon.");
    }

    private static void showAveragePrice() {
        if (DRINKS.isEmpty()) {
            System.out.println("Menu rong.");
            return;
        }
        double totalPrice = 0;
        for (Drink drink : DRINKS) {
            totalPrice += drink.getPrice();
        }
        System.out.printf("Gia trung binh: %.2f%n", totalPrice / DRINKS.size());
    }

    private static Drink findDrinkById(String id) {
        for (Drink drink : DRINKS) {
            if (drink.getId().equalsIgnoreCase(id)) {
                return drink;
            }
        }
        return null;
    }
}
