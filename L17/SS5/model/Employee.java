package model;

import java.math.BigDecimal;

public class Employee {
    private int id;
    private String name;
    private String department;
    private BigDecimal salary;

    public Employee() {
    }

    public Employee(int id, String name, String department, BigDecimal salary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public BigDecimal getSalary() {
        return salary;
    }
}
