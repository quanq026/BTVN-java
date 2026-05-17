import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Exercise2 {
    static abstract class Asset {
        private String assetCode;
        private String name;
        private double purchasePrice;
        private int usedYears;

        Asset(String assetCode, String name, double purchasePrice, int usedYears) {
            this.assetCode = assetCode;
            this.name = name;
            this.purchasePrice = purchasePrice;
            this.usedYears = usedYears;
        }

        public String getAssetCode() {
            return assetCode;
        }

        public String getName() {
            return name;
        }

        public double getPurchasePrice() {
            return purchasePrice;
        }

        public int getUsedYears() {
            return usedYears;
        }

        public void setPurchasePrice(double purchasePrice) {
            this.purchasePrice = purchasePrice;
        }

        abstract double getMarketValue();

        void displayInfo() {
            System.out.printf("Ma: %s | Ten: %s | Gia mua: %.2f | Nam su dung: %d | Gia tri hien tai: %.2f%n",
                    assetCode, name, purchasePrice, usedYears, getMarketValue());
        }
    }

    static class Computer extends Asset {
        private String ram;
        private String cpu;

        Computer(String assetCode, String name, double purchasePrice, int usedYears,
                 String ram, String cpu) {
            super(assetCode, name, purchasePrice, usedYears);
            this.ram = ram;
            this.cpu = cpu;
        }

        @Override
        double getMarketValue() {
            return getPurchasePrice() * Math.pow(0.8, getUsedYears());
        }

        @Override
        void displayInfo() {
            super.displayInfo();
            System.out.println("Loai: May tinh | RAM: " + ram + " | CPU: " + cpu);
        }
    }

    static class NetworkDevice extends Asset {
        private int numberOfPorts;

        NetworkDevice(String assetCode, String name, double purchasePrice,
                      int usedYears, int numberOfPorts) {
            super(assetCode, name, purchasePrice, usedYears);
            this.numberOfPorts = numberOfPorts;
        }

        @Override
        double getMarketValue() {
            return getPurchasePrice() * Math.pow(0.9, getUsedYears());
        }

        @Override
        void displayInfo() {
            super.displayInfo();
            System.out.println("Loai: Thiet bi mang | So cong: " + numberOfPorts);
        }
    }

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final List<Asset> ASSETS = new ArrayList<>();

    public static void main(String[] args) {
        int choice;
        do {
            showMenu();
            choice = Integer.parseInt(SCANNER.nextLine());
            switch (choice) {
                case 1 -> addAsset();
                case 2 -> showReport();
                case 3 -> searchAssetMenu();
                case 4 -> updatePurchasePrice();
                case 5 -> System.out.println("Thoat chuong trinh.");
                default -> System.out.println("Lua chon khong hop le.");
            }
        } while (choice != 5);
    }

    private static void showMenu() {
        System.out.println("===== TECHASSET MANAGEMENT =====");
        System.out.println("1. Nhap tai san");
        System.out.println("2. Xuat bao cao");
        System.out.println("3. Tim kiem");
        System.out.println("4. Sua gia mua");
        System.out.println("5. Thoat");
        System.out.print("Chon: ");
    }

    private static void addAsset() {
        System.out.println("1. May tinh");
        System.out.println("2. Thiet bi mang");
        System.out.print("Chon loai: ");
        int type = Integer.parseInt(SCANNER.nextLine());
        System.out.print("Nhap ma tai san: ");
        String assetCode = SCANNER.nextLine();
        System.out.print("Nhap ten tai san: ");
        String name = SCANNER.nextLine();
        System.out.print("Nhap gia mua: ");
        double purchasePrice = Double.parseDouble(SCANNER.nextLine());
        System.out.print("Nhap so nam su dung: ");
        int usedYears = Integer.parseInt(SCANNER.nextLine());

        if (type == 1) {
            System.out.print("Nhap RAM: ");
            String ram = SCANNER.nextLine();
            System.out.print("Nhap CPU: ");
            String cpu = SCANNER.nextLine();
            ASSETS.add(new Computer(assetCode, name, purchasePrice, usedYears, ram, cpu));
        } else if (type == 2) {
            System.out.print("Nhap so cong: ");
            int numberOfPorts = Integer.parseInt(SCANNER.nextLine());
            ASSETS.add(new NetworkDevice(assetCode, name, purchasePrice, usedYears, numberOfPorts));
        } else {
            System.out.println("Loai khong hop le.");
        }
    }

    private static void showReport() {
        if (ASSETS.isEmpty()) {
            System.out.println("Danh sach rong.");
            return;
        }
        for (Asset asset : ASSETS) {
            showValue(asset);
            System.out.println();
        }
    }

    private static void showValue(Asset asset) {
        asset.displayInfo();
    }

    private static void searchAssetMenu() {
        System.out.println("1. Tim theo ma");
        System.out.println("2. Tim theo gia mua lon hon");
        System.out.print("Chon: ");
        int choice = Integer.parseInt(SCANNER.nextLine());
        if (choice == 1) {
            System.out.print("Nhap ma tai san: ");
            Asset asset = searchAsset(SCANNER.nextLine());
            if (asset == null) {
                System.out.println("Khong tim thay tai san.");
            } else {
                asset.displayInfo();
            }
        } else if (choice == 2) {
            System.out.print("Nhap gia toi thieu: ");
            List<Asset> results = searchAsset(Double.parseDouble(SCANNER.nextLine()));
            for (Asset asset : results) {
                asset.displayInfo();
            }
        }
    }

    private static Asset searchAsset(String assetCode) {
        for (Asset asset : ASSETS) {
            if (asset.getAssetCode().equalsIgnoreCase(assetCode)) {
                return asset;
            }
        }
        return null;
    }

    private static List<Asset> searchAsset(double minPrice) {
        List<Asset> results = new ArrayList<>();
        for (Asset asset : ASSETS) {
            if (asset.getPurchasePrice() > minPrice) {
                results.add(asset);
            }
        }
        return results;
    }

    private static void updatePurchasePrice() {
        System.out.print("Nhap ma tai san: ");
        Asset asset = searchAsset(SCANNER.nextLine());
        if (asset == null) {
            System.out.println("Khong tim thay tai san.");
            return;
        }
        System.out.print("Nhap gia mua moi: ");
        asset.setPurchasePrice(Double.parseDouble(SCANNER.nextLine()));
    }
}
