package ra.entity;

import java.time.LocalDate;
import java.util.Scanner;

public class Project {
    private String projectId;
    private String projectName;
    private LocalDate startDate;
    private LocalDate endDate;
    private Employee[] employees = new Employee[100];
    private int employeeCount = 0;
    private ProjectStatus status;

    public Project() {
    }

    public String getProjectId() {
        return projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public int getEmployeeCount() {
        return employeeCount;
    }

    public Employee[] getEmployees() {
        return employees;
    }

    public ProjectStatus getStatus() {
        return status;
    }

    public void inputData(Scanner scanner, Project[] arrProject, int index,
                          Employee[] arrEmp, int empIndex) {
        while (true) {
            System.out.print("Nhap ma du an (P0001): ");
            String inputId = scanner.nextLine();
            if (!inputId.matches("P\\d{4}")) {
                System.out.println("Ma du an khong hop le.");
                continue;
            }
            boolean duplicate = false;
            for (int i = 0; i < index; i++) {
                if (arrProject[i] != null && arrProject[i].projectId.equals(inputId)) {
                    duplicate = true;
                    break;
                }
            }
            if (!duplicate) {
                projectId = inputId;
                break;
            }
            System.out.println("Ma du an da ton tai.");
        }

        inputProjectName(scanner, arrProject, index);

        while (true) {
            System.out.print("Nhap ngay bat dau (yyyy-MM-dd): ");
            startDate = LocalDate.parse(scanner.nextLine());
            System.out.print("Nhap ngay ket thuc (yyyy-MM-dd): ");
            endDate = LocalDate.parse(scanner.nextLine());
            if (!endDate.isBefore(startDate)) {
                break;
            }
            System.out.println("Ngay ket thuc phai >= ngay bat dau.");
        }

        status = inputStatus(scanner);
        addEmployees(scanner, arrEmp, empIndex);
    }

    public void inputProjectName(Scanner scanner, Project[] arrProject, int index) {
        while (true) {
            System.out.print("Nhap ten du an: ");
            String inputName = scanner.nextLine();
            if (inputName.length() < 10 || inputName.length() > 50) {
                System.out.println("Ten phai tu 10 den 50 ky tu.");
                continue;
            }
            boolean duplicate = false;
            for (int i = 0; i < index; i++) {
                if (arrProject[i] != null
                        && arrProject[i] != this
                        && arrProject[i].projectName.equalsIgnoreCase(inputName)) {
                    duplicate = true;
                    break;
                }
            }
            if (!duplicate) {
                projectName = inputName;
                break;
            }
            System.out.println("Ten du an da ton tai.");
        }
    }

    public static ProjectStatus inputStatus(Scanner scanner) {
        while (true) {
            System.out.print("Nhap trang thai (PLANNING/RUNNING/FINISHED): ");
            try {
                return ProjectStatus.valueOf(scanner.nextLine().toUpperCase());
            } catch (IllegalArgumentException exception) {
                System.out.println("Trang thai khong hop le.");
            }
        }
    }

    public void setStatus(ProjectStatus status) {
        this.status = status;
    }

    public void addEmployees(Scanner scanner, Employee[] arrEmp, int empIndex) {
        if (empIndex == 0) {
            return;
        }
        System.out.print("Nhap so nhan vien muon gan: ");
        int count = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < count; i++) {
            System.out.print("Nhap ma nhan vien: ");
            String employeeId = scanner.nextLine();
            Employee employee = findEmployee(arrEmp, empIndex, employeeId);
            if (employee != null && !hasEmployee(employeeId)) {
                employees[employeeCount++] = employee;
            } else {
                System.out.println("Khong tim thay hoac da ton tai.");
            }
        }
    }

    private boolean hasEmployee(String employeeId) {
        for (int i = 0; i < employeeCount; i++) {
            if (employees[i].getEmployeeId().equals(employeeId)) {
                return true;
            }
        }
        return false;
    }

    private Employee findEmployee(Employee[] arrEmp, int empIndex, String employeeId) {
        for (int i = 0; i < empIndex; i++) {
            if (arrEmp[i].getEmployeeId().equals(employeeId)) {
                return arrEmp[i];
            }
        }
        return null;
    }

    public int countEmployeeByRole(Role role) {
        int count = 0;
        for (int i = 0; i < employeeCount; i++) {
            if (employees[i].getRole() == role) {
                count++;
            }
        }
        return count;
    }

    public void displayData() {
        System.out.printf("ID: %s | Ten: %s | %s -> %s | Trang thai: %s%n",
                projectId, projectName, startDate, endDate, status);
        System.out.println("So nhan vien: " + employeeCount);
        for (int i = 0; i < employeeCount; i++) {
            System.out.println("- " + employees[i].getEmployeeName()
                    + " (" + employees[i].getRole() + ")");
        }
    }
}
