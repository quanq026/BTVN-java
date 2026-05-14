package ra.presentation;

import ra.entity.Employee;
import ra.entity.Project;
import ra.entity.ProjectStatus;
import ra.entity.Role;

import java.time.LocalDate;
import java.util.Scanner;

public class ProjectManagement {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final Employee[] EMPLOYEES = new Employee[100];
    private static final Project[] PROJECTS = new Project[100];
    private static int employeeCount = 0;
    private static int projectCount = 0;

    public static void main(String[] args) {
        int choice;
        do {
            showMainMenu();
            choice = Integer.parseInt(SCANNER.nextLine());
            switch (choice) {
                case 1 -> manageEmployees();
                case 2 -> manageProjects();
                case 3 -> System.out.println("Thoat chuong trinh.");
                default -> System.out.println("Lua chon khong hop le.");
            }
        } while (choice != 3);
    }

    private static void showMainMenu() {
        System.out.println("================ QUAN LY DU AN ================");
        System.out.println("1. Quan ly nhan vien");
        System.out.println("2. Quan ly du an");
        System.out.println("3. Thoat");
        System.out.println("===============================================");
        System.out.print("Lua chon cua ban: ");
    }

    private static void manageEmployees() {
        int choice;
        do {
            showEmployeeMenu();
            choice = Integer.parseInt(SCANNER.nextLine());
            switch (choice) {
                case 1 -> addEmployee();
                case 2 -> showEmployees();
                case 3 -> updateEmployee();
                case 4 -> deleteEmployee();
                case 5 -> searchEmployeeByName();
                case 6 -> sortEmployeeBySalaryDesc();
                case 7 -> System.out.println("Quay lai menu chinh.");
                default -> System.out.println("Lua chon khong hop le.");
            }
        } while (choice != 7);
    }

    private static void showEmployeeMenu() {
        System.out.println("================ QUAN LY NHAN VIEN ================");
        System.out.println("1. Them nhan vien");
        System.out.println("2. Hien thi danh sach nhan vien");
        System.out.println("3. Cap nhat thong tin nhan vien");
        System.out.println("4. Xoa nhan vien");
        System.out.println("5. Tim kiem nhan vien theo ten");
        System.out.println("6. Sap xep nhan vien theo luong giam dan");
        System.out.println("7. Thoat");
        System.out.println("===================================================");
        System.out.print("Lua chon cua ban: ");
    }

    private static void addEmployee() {
        Employee employee = new Employee();
        employee.inputData(SCANNER, EMPLOYEES, employeeCount);
        EMPLOYEES[employeeCount++] = employee;
    }

    private static void showEmployees() {
        if (employeeCount == 0) {
            System.out.println("Danh sach nhan vien rong.");
            return;
        }
        for (int i = 0; i < employeeCount; i++) {
            EMPLOYEES[i].displayData();
        }
    }

    private static void updateEmployee() {
        Employee employee = findEmployeeById();
        if (employee == null) {
            System.out.println("Khong tim thay nhan vien.");
            return;
        }
        System.out.print("Nhap ten moi: ");
        employee.setEmployeeName(SCANNER.nextLine());
        employee.setRole(Employee.inputRole(SCANNER));
        System.out.print("Nhap luong moi: ");
        employee.setSalary(Double.parseDouble(SCANNER.nextLine()));
    }

    private static void deleteEmployee() {
        System.out.print("Nhap ma nhan vien can xoa: ");
        String employeeId = SCANNER.nextLine();
        for (Project project : PROJECTS) {
            if (project == null) {
                continue;
            }
            for (Employee employee : project.getEmployees()) {
                if (employee != null && employee.getEmployeeId().equals(employeeId)) {
                    System.out.println("Nhan vien dang tham gia du an, khong the xoa.");
                    return;
                }
            }
        }
        for (int i = 0; i < employeeCount; i++) {
            if (EMPLOYEES[i].getEmployeeId().equals(employeeId)) {
                for (int j = i; j < employeeCount - 1; j++) {
                    EMPLOYEES[j] = EMPLOYEES[j + 1];
                }
                EMPLOYEES[--employeeCount] = null;
                System.out.println("Da xoa nhan vien.");
                return;
            }
        }
        System.out.println("Khong tim thay nhan vien.");
    }

    private static void searchEmployeeByName() {
        System.out.print("Nhap ten can tim: ");
        String keyword = SCANNER.nextLine().toLowerCase();
        for (int i = 0; i < employeeCount; i++) {
            if (EMPLOYEES[i].getEmployeeName().toLowerCase().contains(keyword)) {
                EMPLOYEES[i].displayData();
            }
        }
    }

    private static void sortEmployeeBySalaryDesc() {
        for (int i = 0; i < employeeCount - 1; i++) {
            for (int j = 0; j < employeeCount - i - 1; j++) {
                if (EMPLOYEES[j].getSalary() < EMPLOYEES[j + 1].getSalary()) {
                    Employee temp = EMPLOYEES[j];
                    EMPLOYEES[j] = EMPLOYEES[j + 1];
                    EMPLOYEES[j + 1] = temp;
                }
            }
        }
        showEmployees();
    }

    private static Employee findEmployeeById() {
        System.out.print("Nhap ma nhan vien: ");
        String employeeId = SCANNER.nextLine();
        for (int i = 0; i < employeeCount; i++) {
            if (EMPLOYEES[i].getEmployeeId().equals(employeeId)) {
                return EMPLOYEES[i];
            }
        }
        return null;
    }

    private static void manageProjects() {
        int choice;
        do {
            showProjectMenu();
            choice = Integer.parseInt(SCANNER.nextLine());
            switch (choice) {
                case 1 -> addProject();
                case 2 -> showProjects();
                case 3 -> updateProject();
                case 4 -> deleteProject();
                case 5 -> addEmployeeToProject();
                case 6 -> searchProjectByName();
                case 7 -> countEmployeeByRoleInProject();
                case 8 -> findRunningProjectNearEnd();
                case 9 -> System.out.println("Quay lai menu chinh.");
                default -> System.out.println("Lua chon khong hop le.");
            }
        } while (choice != 9);
    }

    private static void showProjectMenu() {
        System.out.println("==================== QUAN LY DU AN ====================");
        System.out.println("1. Them du an");
        System.out.println("2. Hien thi danh sach du an");
        System.out.println("3. Cap nhat thong tin du an");
        System.out.println("4. Xoa du an (chi khi chua co nhan vien tham gia)");
        System.out.println("5. Them nhan vien vao du an");
        System.out.println("6. Tim du an theo ten");
        System.out.println("7. Thong ke so luong nhan vien theo vai tro trong tung du an");
        System.out.println("8. Tim du an dang chay va gan ket thuc nhat");
        System.out.println("9. Thoat");
        System.out.println("=======================================================");
        System.out.print("Lua chon cua ban: ");
    }

    private static void addProject() {
        Project project = new Project();
        project.inputData(SCANNER, PROJECTS, projectCount, EMPLOYEES, employeeCount);
        PROJECTS[projectCount++] = project;
    }

    private static void showProjects() {
        if (projectCount == 0) {
            System.out.println("Danh sach du an rong.");
            return;
        }
        for (int i = 0; i < projectCount; i++) {
            PROJECTS[i].displayData();
        }
    }

    private static void updateProject() {
        Project project = findProjectById();
        if (project == null) {
            System.out.println("Khong tim thay du an.");
            return;
        }
        project.inputProjectName(SCANNER, PROJECTS, projectCount);
        project.setStatus(Project.inputStatus(SCANNER));
    }

    private static void deleteProject() {
        Project project = findProjectById();
        if (project == null) {
            System.out.println("Khong tim thay du an.");
            return;
        }
        if (project.getEmployeeCount() > 0) {
            System.out.println("Du an da co nhan vien, khong the xoa.");
            return;
        }
        for (int i = 0; i < projectCount; i++) {
            if (PROJECTS[i] == project) {
                for (int j = i; j < projectCount - 1; j++) {
                    PROJECTS[j] = PROJECTS[j + 1];
                }
                PROJECTS[--projectCount] = null;
                System.out.println("Da xoa du an.");
                return;
            }
        }
    }

    private static void addEmployeeToProject() {
        Project project = findProjectById();
        if (project == null) {
            System.out.println("Khong tim thay du an.");
            return;
        }
        project.addEmployees(SCANNER, EMPLOYEES, employeeCount);
    }

    private static void searchProjectByName() {
        System.out.print("Nhap ten du an can tim: ");
        String keyword = SCANNER.nextLine().toLowerCase();
        for (int i = 0; i < projectCount; i++) {
            if (PROJECTS[i].getProjectName().toLowerCase().contains(keyword)) {
                PROJECTS[i].displayData();
            }
        }
    }

    private static void countEmployeeByRoleInProject() {
        for (int i = 0; i < projectCount; i++) {
            System.out.println("Du an: " + PROJECTS[i].getProjectName());
            for (Role role : Role.values()) {
                System.out.println(role + ": " + PROJECTS[i].countEmployeeByRole(role));
            }
        }
    }

    private static void findRunningProjectNearEnd() {
        Project result = null;
        for (int i = 0; i < projectCount; i++) {
            Project project = PROJECTS[i];
            if (project.getStatus() == ProjectStatus.RUNNING
                    && !project.getEndDate().isBefore(LocalDate.now())) {
                if (result == null || project.getEndDate().isBefore(result.getEndDate())) {
                    result = project;
                }
            }
        }
        if (result == null) {
            System.out.println("Khong co du an phu hop.");
        } else {
            result.displayData();
        }
    }

    private static Project findProjectById() {
        System.out.print("Nhap ma du an: ");
        String projectId = SCANNER.nextLine();
        for (int i = 0; i < projectCount; i++) {
            if (PROJECTS[i].getProjectId().equals(projectId)) {
                return PROJECTS[i];
            }
        }
        return null;
    }
}
