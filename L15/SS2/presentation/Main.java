package presentation;

import business.InvalidCreditException;
import business.SubjectManager;
import model.Subject;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final SubjectManager<Subject> subjectManager = new SubjectManager<>();

    public static void main(String[] args) {
        int choice;

        do {
            showMenu();
            choice = inputInteger("Lựa chọn của bạn: ");

            switch (choice) {
                case 1:
                    displaySubjects(subjectManager.displaySubjects());
                    break;
                case 2:
                    addSubject();
                    break;
                case 3:
                    removeSubject();
                    break;
                case 4:
                    searchSubject();
                    break;
                case 5:
                    filterByCredits();
                    break;
                case 0:
                    System.out.println("Thoát chương trình!");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        } while (choice != 0);
    }

    private static void showMenu() {
        System.out.println("========== MENU QUẢN LÝ MÔN HỌC ==========");
        System.out.println("1. Hiển thị danh sách môn học");
        System.out.println("2. Thêm môn học");
        System.out.println("3. Xóa môn học");
        System.out.println("4. Tìm kiếm môn học theo tên");
        System.out.println("5. Lọc môn học theo tín chỉ");
        System.out.println("0. Thoát");
    }

    private static void addSubject() {
        try {
            System.out.print("Nhập mã môn học: ");
            String code = scanner.nextLine();
            System.out.print("Nhập tên môn học: ");
            String name = scanner.nextLine();
            int credits = inputCredits();
            System.out.print("Nhập ngày bắt đầu (dd/MM/yyyy): ");
            String startDate = scanner.nextLine();

            subjectManager.addSubject(new Subject(code, name, credits, startDate));
            System.out.println("Thêm môn học thành công!");
        } catch (InvalidCreditException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void removeSubject() {
        System.out.print("Nhập mã môn học cần xóa: ");
        String code = scanner.nextLine();

        if (subjectManager.removeSubject(code)) {
            System.out.println("Xóa môn học thành công!");
        } else {
            System.out.println("Không tìm thấy môn học cần xóa!");
        }
    }

    private static void searchSubject() {
        System.out.print("Nhập tên môn học cần tìm: ");
        String name = scanner.nextLine();
        List<Subject> result = subjectManager.searchByName(name);

        if (result.isEmpty()) {
            System.out.println("Không có môn học phù hợp");
        } else {
            displaySubjects(result);
        }
    }

    private static void filterByCredits() {
        List<Subject> result = subjectManager.filterByCredits();

        if (result.isEmpty()) {
            System.out.println("Không có môn học có tín chỉ lớn hơn 3!");
        } else {
            displaySubjects(result);
        }
    }

    private static void displaySubjects(List<Subject> subjects) {
        if (subjects.isEmpty()) {
            System.out.println("Danh sách môn học trống!");
            return;
        }

        for (Subject subject : subjects) {
            System.out.println(subject);
        }
    }

    private static int inputCredits() {
        while (true) {
            try {
                int credits = inputInteger("Nhập số tín chỉ: ");
                subjectManager.validateCredits(credits);
                return credits;
            } catch (InvalidCreditException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static int inputInteger(String message) {
        while (true) {
            try {
                System.out.print(message);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số nguyên hợp lệ!");
            }
        }
    }
}
