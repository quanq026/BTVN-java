package ra.entity;

import java.util.Scanner;

public class Employee {
    private String employeeId;
    private String employeeName;
    private Role role;
    private double salary;

    public Employee() {
    }

    public Employee(String employeeId, String employeeName, Role role, double salary) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.role = role;
        this.salary = salary;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public Role getRole() {
        return role;
    }

    public double getSalary() {
        return salary;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void inputData(Scanner scanner, Employee[] arrEmp, int index) {
        while (true) {
            System.out.print("Nhap ma nhan vien (E0001): ");
            String inputId = scanner.nextLine();
            if (!inputId.matches("E\\d{4}")) {
                System.out.println("Ma nhan vien khong hop le.");
                continue;
            }
            boolean duplicate = false;
            for (int i = 0; i < index; i++) {
                if (arrEmp[i] != null && arrEmp[i].employeeId.equals(inputId)) {
                    duplicate = true;
                    break;
                }
            }
            if (!duplicate) {
                employeeId = inputId;
                break;
            }
            System.out.println("Ma nhan vien da ton tai.");
        }

        while (true) {
            System.out.print("Nhap ten nhan vien: ");
            employeeName = scanner.nextLine();
            if (employeeName.length() >= 6 && employeeName.length() <= 30) {
                break;
            }
            System.out.println("Ten phai tu 6 den 30 ky tu.");
        }

        role = inputRole(scanner);

        while (true) {
            System.out.print("Nhap luong: ");
            salary = Double.parseDouble(scanner.nextLine());
            if (salary > 0) {
                break;
            }
            System.out.println("Luong phai lon hon 0.");
        }
    }

    public static Role inputRole(Scanner scanner) {
        while (true) {
            System.out.print("Nhap vai tro (DEV/TESTER/PM/BA): ");
            try {
                return Role.valueOf(scanner.nextLine().toUpperCase());
            } catch (IllegalArgumentException exception) {
                System.out.println("Vai tro khong hop le.");
            }
        }
    }

    public void displayData() {
        System.out.printf("ID: %s | Ten: %s | Vai tro: %s | Luong: %.2f%n",
                employeeId, employeeName, role, salary);
    }
}
