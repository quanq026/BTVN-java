package presentation;

import business.Management;
import model.Assignment;
import model.Employee;
import model.Project;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Management management = new Management();

    public static void main(String[] args) {
        int choice;
        do {
            showMenu();
            choice = inputInteger("Lựa chọn của bạn: ");
            try {
                switch (choice) {
                    case 1: addEmployee(); break;
                    case 2: addProject(); break;
                    case 3: assignEmployee(); break;
                    case 4: display(management.listEmployeesAndProjects()); break;
                    case 5: updateSalary(); break;
                    case 0: System.out.println("Thoát chương trình!"); break;
                    default: System.out.println("Lựa chọn không hợp lệ!");
                }
            } catch (SQLException e) {
                System.out.println("Lỗi thao tác cơ sở dữ liệu: " + e.getMessage());
            }
        } while (choice != 0);
    }

    private static void showMenu() {
        System.out.println("====== MENU NHÂN VIÊN - DỰ ÁN ======");
        System.out.println("1. Thêm nhân viên");
        System.out.println("2. Thêm dự án");
        System.out.println("3. Gán nhân viên vào dự án");
        System.out.println("4. Hiển thị nhân viên và dự án");
        System.out.println("5. Cập nhật lương nhân viên");
        System.out.println("0. Thoát");
    }

    private static void addEmployee() throws SQLException {
        String name = inputRequiredString("Nhập tên nhân viên: ");
        String department = inputRequiredString("Nhập phòng ban: ");
        BigDecimal salary = inputBigDecimal("Nhập lương: ");
        System.out.println(management.addEmployee(new Employee(0, name, department, salary)) ? "Thêm nhân viên thành công!" : "Tên nhân viên đã tồn tại!");
    }

    private static void addProject() throws SQLException {
        String name = inputRequiredString("Nhập tên dự án: ");
        BigDecimal budget = inputBigDecimal("Nhập ngân sách: ");
        System.out.println(management.addProject(new Project(0, name, budget)) ? "Thêm dự án thành công!" : "Tên dự án đã tồn tại!");
    }

    private static void assignEmployee() throws SQLException {
        int employeeId = inputInteger("Nhập ID nhân viên: ");
        int projectId = inputInteger("Nhập ID dự án: ");
        String role = inputRequiredString("Nhập vai trò: ");
        System.out.println(management.assignEmployeeToProject(new Assignment(employeeId, projectId, role)) ? "Gán nhân viên thành công!" : "Nhân viên hoặc dự án không tồn tại!");
    }

    private static void updateSalary() throws SQLException {
        int employeeId = inputInteger("Nhập ID nhân viên: ");
        BigDecimal salary = inputBigDecimal("Nhập lương mới: ");
        System.out.println(management.updateEmployeeSalary(employeeId, salary) ? "Cập nhật lương thành công!" : "Không tìm thấy nhân viên!");
    }

    private static void display(List<String> values) {
        if (values.isEmpty()) {
            System.out.println("Không có dữ liệu!");
            return;
        }
        values.forEach(System.out::println);
    }

    private static String inputRequiredString(String message) {
        System.out.print(message);
        String value = scanner.nextLine().trim();
        if (value.isEmpty()) throw new IllegalArgumentException("Không được nhập trống!");
        return value;
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

    private static BigDecimal inputBigDecimal(String message) {
        while (true) {
            try {
                System.out.print(message);
                return new BigDecimal(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số hợp lệ!");
            }
        }
    }
}
