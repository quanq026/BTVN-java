package presentation;

import business.StudentStatistics;
import model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        List<Student> students = createStudents();
        StudentStatistics statistics = new StudentStatistics();
        Map<String, Long> result = statistics.countStudentsByMajor(students);

        System.out.println("Thống kê số lượng sinh viên theo chuyên ngành:");
        for (Map.Entry<String, Long> entry : result.entrySet()) {
            System.out.println("Chuyên ngành: " + entry.getKey() + " - Số lượng: " + entry.getValue());
        }
    }

    private static List<Student> createStudents() {
        List<Student> students = new ArrayList<>();
        students.add(new Student("An", "IT", 8.5));
        students.add(new Student("Bình", "Marketing", 7.8));
        students.add(new Student("Chi", "IT", 9.0));
        students.add(new Student("Dũng", "Business", 6.9));
        students.add(new Student("Hà", "IT", 8.0));
        students.add(new Student("Lan", "Marketing", 7.2));
        students.add(new Student("Minh", "Design", 8.7));
        students.add(new Student("Nam", "Business", 7.4));
        students.add(new Student("Oanh", "IT", 9.2));
        students.add(new Student("Phúc", "Design", 6.8));
        return students;
    }
}
