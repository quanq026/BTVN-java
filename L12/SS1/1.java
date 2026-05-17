import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Exercise1 {
    interface ICapability {
        void checkPerformance();
    }

    static abstract class Staff implements ICapability {
        private String id;
        private String name;
        private double baseSalary;

        Staff(String id, String name, double baseSalary) {
            this.id = id;
            this.name = name;
            this.baseSalary = baseSalary;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public double getBaseSalary() {
            return baseSalary;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setBaseSalary(double baseSalary) {
            this.baseSalary = baseSalary;
        }

        abstract double calculateTotalSalary();

        void displayInfo() {
            System.out.printf("ID: %s | Ten: %s | Luong co ban: %.2f | Luong thuc nhan: %.2f%n",
                    id, name, baseSalary, calculateTotalSalary());
        }
    }

    static class Lecturer extends Staff {
        private int teachingHours;

        Lecturer(String id, String name, double baseSalary, int teachingHours) {
            super(id, name, baseSalary);
            this.teachingHours = teachingHours;
        }

        public void setTeachingHours(int teachingHours) {
            this.teachingHours = teachingHours;
        }

        @Override
        double calculateTotalSalary() {
            return getBaseSalary() + teachingHours * 200000;
        }

        @Override
        void displayInfo() {
            super.displayInfo();
            System.out.println("Loai: Giang vien | Gio day: " + teachingHours);
        }

        @Override
        public void checkPerformance() {
            if (teachingHours >= 40) {
                System.out.println("Danh gia: Tot");
            } else {
                System.out.println("Danh gia: Can cai thien");
            }
        }
    }

    static class AdminStaff extends Staff {
        private double bonus;

        AdminStaff(String id, String name, double baseSalary, double bonus) {
            super(id, name, baseSalary);
            this.bonus = bonus;
        }

        public void setBonus(double bonus) {
            this.bonus = bonus;
        }

        @Override
        double calculateTotalSalary() {
            return getBaseSalary() + bonus;
        }

        @Override
        void displayInfo() {
            super.displayInfo();
            System.out.println("Loai: Nhan vien hanh chinh | Thuong: " + bonus);
        }

        @Override
        public void checkPerformance() {
            if (bonus > 0) {
                System.out.println("Danh gia: Hoan thanh tot");
            } else {
                System.out.println("Danh gia: Hoan thanh");
            }
        }
    }

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final List<Staff> STAFFS = new ArrayList<>();

    public static void main(String[] args) {
        int choice;
        do {
            showMenu();
            choice = Integer.parseInt(SCANNER.nextLine());
            switch (choice) {
                case 1 -> addStaff();
                case 2 -> showStaffs();
                case 3 -> updateStaff();
                case 4 -> deleteStaff();
                case 5 -> System.out.println("Thoat chuong trinh.");
                default -> System.out.println("Lua chon khong hop le.");
            }
        } while (choice != 5);
    }

    private static void showMenu() {
        System.out.println("===== EDUCAREER STAFF MANAGEMENT =====");
        System.out.println("1. Them moi");
        System.out.println("2. Hien thi danh sach");
        System.out.println("3. Cap nhat");
        System.out.println("4. Xoa");
        System.out.println("5. Thoat");
        System.out.print("Chon: ");
    }

    private static void addStaff() {
        System.out.println("1. Giang vien");
        System.out.println("2. Nhan vien hanh chinh");
        System.out.print("Chon loai: ");
        int type = Integer.parseInt(SCANNER.nextLine());
        System.out.print("Nhap id: ");
        String id = SCANNER.nextLine();
        System.out.print("Nhap ten: ");
        String name = SCANNER.nextLine();
        System.out.print("Nhap luong co ban: ");
        double baseSalary = Double.parseDouble(SCANNER.nextLine());

        if (type == 1) {
            System.out.print("Nhap so gio day: ");
            int teachingHours = Integer.parseInt(SCANNER.nextLine());
            STAFFS.add(new Lecturer(id, name, baseSalary, teachingHours));
        } else if (type == 2) {
            System.out.print("Nhap tien thuong: ");
            double bonus = Double.parseDouble(SCANNER.nextLine());
            STAFFS.add(new AdminStaff(id, name, baseSalary, bonus));
        } else {
            System.out.println("Loai khong hop le.");
        }
    }

    private static void showStaffs() {
        if (STAFFS.isEmpty()) {
            System.out.println("Danh sach rong.");
            return;
        }
        for (Staff staff : STAFFS) {
            staff.displayInfo();
            staff.checkPerformance();
            System.out.println();
        }
    }

    private static void updateStaff() {
        Staff staff = findStaffById();
        if (staff == null) {
            System.out.println("Khong tim thay nhan su.");
            return;
        }
        System.out.print("Nhap ten moi: ");
        staff.setName(SCANNER.nextLine());
        System.out.print("Nhap luong co ban moi: ");
        staff.setBaseSalary(Double.parseDouble(SCANNER.nextLine()));
        if (staff instanceof Lecturer lecturer) {
            System.out.print("Nhap so gio day moi: ");
            lecturer.setTeachingHours(Integer.parseInt(SCANNER.nextLine()));
        } else if (staff instanceof AdminStaff adminStaff) {
            System.out.print("Nhap tien thuong moi: ");
            adminStaff.setBonus(Double.parseDouble(SCANNER.nextLine()));
        }
    }

    private static void deleteStaff() {
        Staff staff = findStaffById();
        if (staff == null) {
            System.out.println("Khong tim thay nhan su.");
            return;
        }
        STAFFS.remove(staff);
        System.out.println("Da xoa nhan su.");
    }

    private static Staff findStaffById() {
        System.out.print("Nhap id: ");
        String id = SCANNER.nextLine();
        for (Staff staff : STAFFS) {
            if (staff.getId().equalsIgnoreCase(id)) {
                return staff;
            }
        }
        return null;
    }
}
