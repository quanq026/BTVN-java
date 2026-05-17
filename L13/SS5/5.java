import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Exercise5 {
    static class Student {
        private static int nextId = 1;

        private int id;
        private String name;
        private double gpa;

        Student(String name, double gpa) {
            this.id = nextId++;
            this.name = name;
            this.gpa = gpa;
        }

        String getName() {
            return name;
        }

        double getGpa() {
            return gpa;
        }

        String classify() {
            if (gpa >= 8.5) {
                return "Xuat sac";
            } else if (gpa >= 7.0) {
                return "Gioi";
            } else if (gpa >= 5.5) {
                return "Kha";
            }
            return "Trung binh / Yeu";
        }

        @Override
        public String toString() {
            return "ID: " + id + ", Ten: " + name + ", GPA: " + gpa;
        }
    }

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final List<Student> STUDENTS = new ArrayList<>();

    public static void main(String[] args) {
        int choice;
        do {
            showMenu();
            choice = Integer.parseInt(SCANNER.nextLine());
            switch (choice) {
                case 1 -> addStudents();
                case 2 -> showStudents();
                case 3 -> searchStudentByName();
                case 4 -> classifyStudents();
                case 0 -> System.out.println("Thoat chuong trinh.");
                default -> System.out.println("Lua chon khong hop le.");
            }
        } while (choice != 0);
    }

    private static void showMenu() {
        System.out.println("================ MENU ================");
        System.out.println("1. Nhap danh sach sinh vien");
        System.out.println("2. Hien thi danh sach sinh vien");
        System.out.println("3. Tim kiem sinh vien theo ten");
        System.out.println("4. Phan loai sinh vien theo GPA");
        System.out.println("0. Thoat chuong trinh");
        System.out.println("======================================");
        System.out.print("Lua chon cua ban: ");
    }

    private static void addStudents() {
        System.out.print("Nhap so luong sinh vien muon them: ");
        int count = Integer.parseInt(SCANNER.nextLine());
        if (count < 5) {
            System.out.println("Nen nhap it nhat 5 sinh vien.");
        }
        for (int i = 0; i < count; i++) {
            System.out.println("Sinh vien thu " + (i + 1) + ":");
            System.out.print("Nhap ho ten: ");
            String name = SCANNER.nextLine();
            System.out.print("Nhap GPA: ");
            double gpa = Double.parseDouble(SCANNER.nextLine());
            STUDENTS.add(new Student(name, gpa));
        }
    }

    private static void showStudents() {
        if (STUDENTS.isEmpty()) {
            System.out.println("Danh sach sinh vien rong.");
            return;
        }
        for (Student student : STUDENTS) {
            System.out.println(student);
        }
    }

    private static void searchStudentByName() {
        System.out.print("Nhap ten sinh vien can tim: ");
        String keyword = SCANNER.nextLine().toLowerCase();
        boolean found = false;
        for (Student student : STUDENTS) {
            if (student.getName().toLowerCase().contains(keyword)) {
                System.out.println(student);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Khong tim thay sinh vien.");
        }
    }

    private static void classifyStudents() {
        if (STUDENTS.isEmpty()) {
            System.out.println("Danh sach sinh vien rong.");
            return;
        }
        for (Student student : STUDENTS) {
            System.out.println(student + " | Xep loai: " + student.classify());
        }
    }
}
