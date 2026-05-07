package com.rikkei.session8.ex6594;

import java.util.Scanner;

public class Student {
    private static int countStudent = 0;
    private static final Scanner SCANNER = new Scanner(System.in);

    private int id;
    private String name;
    private double gpa;
    public static final double SCORE_FACTOR = 0.25;

    public Student() {
        countStudent++;
    }

    public Student(int id, String name, double gpa) {
        this();
        this.id = id;
        this.name = name;
        this.gpa = gpa;
    }

    public void input() {
        input(SCANNER);
    }

    public void input(Scanner scanner) {
        System.out.print("Enter id: ");
        this.id = Integer.parseInt(scanner.nextLine().trim());
        System.out.print("Enter name: ");
        this.name = scanner.nextLine().trim();
        System.out.print("Enter gpa: ");
        this.gpa = Double.parseDouble(scanner.nextLine().trim());
    }

    public void print() {
        System.out.printf("Student{id=%d, name='%s', gpa=%.2f, factor=%.2f}%n", id, name, gpa, SCORE_FACTOR);
    }

    public double getGpa() {
        return gpa;
    }

    public static int getTotalStudent() {
        return countStudent;
    }
}
