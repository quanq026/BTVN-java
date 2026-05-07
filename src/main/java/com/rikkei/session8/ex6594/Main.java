package com.rikkei.session8.ex6594;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
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
