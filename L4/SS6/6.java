import java.util.Scanner;

public class SalaryManager {
    private double[] salaries;
    private int n;
    private Scanner scanner;

    public SalaryManager() {
        scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        SalaryManager manager = new SalaryManager();
        manager.inputData();
        manager.menu();
    }

    private void inputData() {
        System.out.println("--- QUẢN LÝ LƯƠNG NHÂN VIÊN ---");
        System.out.print("Nhập số lượng nhân viên: ");
        n = scanner.nextInt();

        salaries = new double[n];
        for (int i = 0; i < n; i++) {
            System.out.print("Nhập lương nhân viên " + (i + 1) + ": ");
            salaries[i] = scanner.nextDouble();
        }
        System.out.println();
    }

    private void menu() {
        int choice;
        do {
            System.out.println("--- QUẢN LÝ LƯƠNG NHÂN VIÊN ---");
            System.out.println("1. Xem danh sách lương");
            System.out.println("2. Sắp xếp lương");
            System.out.println("3. Tìm kiếm lương");
            System.out.println("4. Thống kê lương");
            System.out.println("5. Thoát");
            System.out.print("Lựa chọn của bạn: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    displaySalaries();
                    break;
                case 2:
                    sortSalaries();
                    break;
                case 3:
                    searchSalary();
                    break;
                case 4:
                    statisticsSalary();
                    break;
                case 5:
                    System.out.println("Thoát chương trình.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }
            System.out.println();
        } while (choice != 5);
    }

    private void displaySalaries() {
        System.out.println("--- DANH SÁCH LƯƠNG ---");
        for (int i = 0; i < n; i++) {
            System.out.println("Nhân viên " + (i + 1) + ": " + salaries[i]);
        }
    }

    private void sortSalaries() {
        System.out.println("Chọn cách sắp xếp:");
        System.out.println("1. Tăng dần");
        System.out.println("2. Giảm dần");
        System.out.print("Lựa chọn của bạn: ");
        int sortChoice = scanner.nextInt();

        if (sortChoice == 1) {
            bubbleSortAscending();
            System.out.println("Đã sắp xếp lương tăng dần.");
        } else if (sortChoice == 2) {
            bubbleSortDescending();
            System.out.println("Đã sắp xếp lương giảm dần.");
        } else {
            System.out.println("Lựa chọn không hợp lệ.");
        }
    }

    private void bubbleSortAscending() {
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (salaries[j] > salaries[j + 1]) {
                    double temp = salaries[j];
                    salaries[j] = salaries[j + 1];
                    salaries[j + 1] = temp;
                }
            }
        }
    }

    private void bubbleSortDescending() {
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (salaries[j] < salaries[j + 1]) {
                    double temp = salaries[j];
                    salaries[j] = salaries[j + 1];
                    salaries[j + 1] = temp;
                }
            }
        }
    }

    private void searchSalary() {
        System.out.print("Nhập lương cần tìm: ");
        double target = scanner.nextDouble();

        int index = linearSearch(target);

        if (index != -1) {
            System.out.println("Tìm thấy lương " + target + " tại vị trí " + (index + 1));
        } else {
            System.out.println("Không tìm thấy lương " + target);
        }
    }

    private int linearSearch(double target) {
        for (int i = 0; i < n; i++) {
            if (salaries[i] == target) {
                return i;
            }
        }
        return -1;
    }

    private void statisticsSalary() {
        double total = calculateTotal();
        double average = total / n;
        double max = findMax();
        double min = findMin();
        int countAboveAverage = countAboveAverage(average);

        System.out.println("--- THỐNG KÊ LƯƠNG ---");
        System.out.println("Tổng lương: " + total);
        System.out.println("Lương trung bình: " + average);
        System.out.println("Lương cao nhất: " + max);
        System.out.println("Lương thấp nhất: " + min);
        System.out.println("Số nhân viên có lương trên trung bình: " + countAboveAverage);
    }

    private double calculateTotal() {
        double total = 0;
        for (int i = 0; i < n; i++) {
            total += salaries[i];
        }
        return total;
    }

    private double findMax() {
        double max = salaries[0];
        for (int i = 1; i < n; i++) {
            if (salaries[i] > max) {
                max = salaries[i];
            }
        }
        return max;
    }

    private double findMin() {
        double min = salaries[0];
        for (int i = 1; i < n; i++) {
            if (salaries[i] < min) {
                min = salaries[i];
            }
        }
        return min;
    }

    private int countAboveAverage(double average) {
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (salaries[i] > average) {
                count++;
            }
        }
        return count;
    }
}