import java.util.Arrays;
import java.util.ArrayList;
import java.util.Scanner;

public class StudentManager {
    static Scanner scanner = new Scanner(System.in);
    static ArrayList<String> students = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            printMenu();
            System.out.print("Chọn: ");
            int choice = Integer.parseInt(scanner.nextLine().trim());

            switch (choice) {
                case 1 -> themSinhVien();
                case 2 -> hienThiDanhSach();
                case 3 -> timKiem();
                case 4 -> demTheoKyTu();
                case 5 -> sapXep();
                case 6 -> {
                    System.out.println("Tạm biệt!");
                    return;
                }
                default -> System.out.println("Lựa chọn không hợp lệ.");
            }
        }
    }

    static void printMenu() {
        System.out.println("========== QUẢN LÝ TÊN SINH VIÊN ==========");
        System.out.println("       1. Thêm tên sinh viên");
        System.out.println("2. Hiển thị danh sách");
        System.out.println("3. Tìm tên sinh viên chứa từ khóa");
        System.out.println("4. Đếm số sinh viên có tên bắt đầu bằng chữ cái nhập vào");
        System.out.println("5. Sắp xếp danh sách tên theo thứ tự A-Z");
        System.out.println("6. Thoát");
        System.out.println("============================================");
    }

    static void themSinhVien() {
        System.out.print("Nhập tên sinh viên: ");
        String name = scanner.nextLine();
        students.add(name);
        System.out.println("Đã thêm: " + name);
    }

    static void hienThiDanhSach() {
        System.out.println("Danh sách sinh viên:");
        for (int i = 0; i < students.size(); i++) {
            System.out.println("       " + (i + 1) + ". " + students.get(i));
        }
    }

    static void timKiem() {
        System.out.print("Nhập từ khóa: ");
        String keyword = scanner.nextLine();
        System.out.println("Kết quả tìm kiếm:");
        for (String name : students) {
            if (name.toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println("  - " + name);
            }
        }
    }

    static void demTheoKyTu() {
        System.out.print("Nhập chữ cái: ");
        String letter = scanner.nextLine().trim();
        long count = students.stream()
                .filter(name -> name.toLowerCase().startsWith(letter.toLowerCase()))
                .count();
        System.out.println("Số sinh viên có tên bắt đầu bằng '" + letter + "': " + count);
    }

    static void sapXep() {
        String[] arr = students.toArray(new String[0]);
        Arrays.sort(arr, String.CASE_INSENSITIVE_ORDER);
        students = new ArrayList<>(Arrays.asList(arr));
        System.out.println("Danh sách đã được sắp xếp A-Z.");
    }
}