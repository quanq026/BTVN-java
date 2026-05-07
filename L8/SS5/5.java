import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Exercise5 {
    static class Student {
        private static int countStudent = 0;
        private static final Scanner SCANNER = new Scanner(System.in);

        private int id;
        private String name;
        private double gpa;
        private final double SCORE_FACTOR = 0.25;

        Student() {
            countStudent++;
        }

        Student(int id, String name, double gpa) {
            this();
            this.id = id;
            this.name = name;
            this.gpa = gpa;
        }

        void input() {
            System.out.print("Nhap ma so: ");
            id = Integer.parseInt(SCANNER.nextLine());
            System.out.print("Nhap ho ten: ");
            name = SCANNER.nextLine();
            System.out.print("Nhap diem trung binh: ");
            gpa = Double.parseDouble(SCANNER.nextLine());
        }

        void print() {
            System.out.printf("ID: %d, Name: %s, GPA: %.2f, Quy doi: %.2f%n",
                    id, name, gpa, gpa * SCORE_FACTOR);
        }

        double getGpa() {
            return gpa;
        }

        static int getTotalStudent() {
            return countStudent;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Student> students = new ArrayList<>();

        while (true) {
            System.out.println("1. Nhap danh sach sinh vien");
            System.out.println("2. In danh sach sinh vien");
            System.out.println("3. Tim sinh vien co diem cao nhat");
            System.out.println("4. Hien thi tong so sinh vien da tao");
            System.out.println("5. Thoat");
            System.out.print("Chon: ");

            int choice = Integer.parseInt(scanner.nextLine());

            if (choice == 1) {
                System.out.print("Nhap so sinh vien: ");
                int number = Integer.parseInt(scanner.nextLine());
                for (int i = 0; i < number; i++) {
                    Student student = new Student();
                    System.out.println("Sinh vien thu " + (i + 1) + ":");
                    student.input();
                    students.add(student);
                }
            } else if (choice == 2) {
                if (students.isEmpty()) {
                    System.out.println("Danh sach rong.");
                } else {
                    for (Student student : students) {
                        student.print();
                    }
                }
            } else if (choice == 3) {
                if (students.isEmpty()) {
                    System.out.println("Danh sach rong.");
                } else {
                    Student bestStudent = students.get(0);
                    for (Student student : students) {
                        if (student.getGpa() > bestStudent.getGpa()) {
                            bestStudent = student;
                        }
                    }
                    System.out.println("Sinh vien co diem cao nhat:");
                    bestStudent.print();
                }
            } else if (choice == 4) {
                System.out.println("Tong so sinh vien da tao: " + Student.getTotalStudent());
            } else if (choice == 5) {
                break;
            } else {
                System.out.println("Lua chon khong hop le.");
            }
        }
    }
}
