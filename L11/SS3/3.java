class Exercise3 {
    interface BonusEligible {
        double calculateBonus();
    }

    static abstract class Employee {
        protected int id;
        protected String name;

        Employee(int id, String name) {
            this.id = id;
            this.name = name;
        }

        abstract double calculateSalary();

        void showInfo() {
            System.out.println("ID: " + id + ", Name: " + name);
        }
    }

    static class FullTimeEmployee extends Employee implements BonusEligible {
        private final double baseSalary;

        FullTimeEmployee(int id, String name, double baseSalary) {
            super(id, name);
            this.baseSalary = baseSalary;
        }

        @Override
        double calculateSalary() {
            return baseSalary;
        }

        @Override
        public double calculateBonus() {
            return baseSalary * 0.1;
        }
    }

    static class PartTimeEmployee extends Employee {
        private final int workingHour;
        private final double salaryPerHour;

        PartTimeEmployee(int id, String name, int workingHour, double salaryPerHour) {
            super(id, name);
            this.workingHour = workingHour;
            this.salaryPerHour = salaryPerHour;
        }

        @Override
        double calculateSalary() {
            return workingHour * salaryPerHour;
        }
    }

    public static void main(String[] args) {
        Employee[] employees = {
                new FullTimeEmployee(1, "Nguyen Van A", 15000000),
                new PartTimeEmployee(2, "Tran Thi B", 80, 50000)
        };

        for (Employee employee : employees) {
            employee.showInfo();
            System.out.printf("Luong: %.2f%n", employee.calculateSalary());
            if (employee instanceof BonusEligible bonusEligible) {
                System.out.printf("Thuong: %.2f%n", bonusEligible.calculateBonus());
            }
            System.out.println();
        }
    }
}
