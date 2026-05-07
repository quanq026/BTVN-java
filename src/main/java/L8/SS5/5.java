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
        static final double SCORE_FACTOR = 0.25;

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
            input(SCANNER);
        }

        void input(Scanner scanner) {
            System.out.print("Enter id: ");
            this.id = Integer.parseInt(scanner.nextLine().trim());
            System.out.print("Enter name: ");
            this.name = scanner.nextLine().trim();
            System.out.print("Enter gpa: ");
            this.gpa = Double.parseDouble(scanner.nextLine().trim());
        }

        void print() {
            System.out.printf("Student{id=%d, name='%s', gpa=%.2f, factor=%.2f}%n", id, name, gpa, SCORE_FACTOR);
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
            System.out.println("1. Add student");
            System.out.println("2. Print students");
            System.out.println("3. Find highest GPA student");
            System.out.println("4. Total students created");
            System.out.println("5. Exit");
            System.out.print("Choose: ");

            int choice = Integer.parseInt(scanner.nextLine().trim());
            switch (choice) {
                case 1 -> {
                    Student student = new Student();
                    student.input(scanner);
                    students.add(student);
                }
                case 2 -> students.forEach(Student::print);
                case 3 -> {
                    if (students.isEmpty()) {
                        System.out.println("No students.");
                    } else {
                        Student best = students.get(0);
                        for (Student student : students) {
                            if (student.getGpa() > best.getGpa()) {
                                best = student;
                            }
                        }
                        System.out.println("Highest GPA student:");
                        best.print();
                    }
                }
                case 4 -> System.out.println("Total students created: " + Student.getTotalStudent());
                case 5 -> {
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }
}
