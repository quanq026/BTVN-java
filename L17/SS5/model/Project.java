package model;

import java.math.BigDecimal;

public class Project {
    private int id;
    private String name;
    private BigDecimal budget;

    public Project() {
    }

    public Project(int id, String name, BigDecimal budget) {
        this.id = id;
        this.name = name;
        this.budget = budget;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getBudget() {
        return budget;
    }
}
