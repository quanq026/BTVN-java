package presentation;

import business.TaskManagement;
import model.Task;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final TaskManagement taskManagement = new TaskManagement();

    public static void main(String[] args) {
        int choice;
        do {
            showMenu();
            choice = inputInteger("Lựa chọn của bạn: ");
            try {
                switch (choice) {
                    case 1: addTask(); break;
                    case 2: displayTasks(taskManagement.listTasks()); break;
                    case 3: updateTaskStatus(); break;
                    case 4: deleteTask(); break;
                    case 5: searchTask(); break;
                    case 6: taskManagement.taskStatistics(); break;
                    case 0: System.out.println("Thoát chương trình!"); break;
                    default: System.out.println("Lựa chọn không hợp lệ!");
                }
            } catch (SQLException e) {
                System.out.println("Lỗi thao tác cơ sở dữ liệu: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } while (choice != 0);
    }

    private static void showMenu() {
        System.out.println("====== MENU TO-DO LIST ======");
        System.out.println("1. Thêm công việc");
        System.out.println("2. Liệt kê công việc");
        System.out.println("3. Cập nhật trạng thái");
        System.out.println("4. Xóa công việc");
        System.out.println("5. Tìm kiếm công việc");
        System.out.println("6. Thống kê công việc");
        System.out.println("0. Thoát");
    }

    private static void addTask() throws SQLException {
        String taskName = inputRequiredString("Nhập tên công việc: ");
        String status = inputRequiredString("Nhập trạng thái: ");
        taskManagement.addTask(taskName, status);
        System.out.println("Thêm công việc thành công!");
    }

    private static void updateTaskStatus() throws SQLException {
        int taskId = inputInteger("Nhập ID công việc: ");
        String status = inputRequiredString("Nhập trạng thái mới: ");
        taskManagement.updateTaskStatus(taskId, status);
        System.out.println("Cập nhật trạng thái thành công!");
    }

    private static void deleteTask() throws SQLException {
        int taskId = inputInteger("Nhập ID công việc cần xóa: ");
        taskManagement.deleteTask(taskId);
        System.out.println("Xóa công việc thành công!");
    }

    private static void searchTask() throws SQLException {
        String taskName = inputRequiredString("Nhập tên công việc cần tìm: ");
        displayTasks(taskManagement.searchTaskByName(taskName));
    }

    private static void displayTasks(List<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("Không có công việc phù hợp!");
            return;
        }
        for (Task task : tasks) {
            System.out.println(task);
        }
    }

    private static String inputRequiredString(String message) {
        System.out.print(message);
        String value = scanner.nextLine().trim();
        if (value.isEmpty()) {
            throw new IllegalArgumentException("Không được nhập trống!");
        }
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
}
