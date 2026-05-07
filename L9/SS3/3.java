class Exercise3 {
    static class Student {
        private static int count = 0;
        private static final double MIN_GPA = 0.0;
        private static final double MAX_GPA = 4.0;

        private final int id;
        private final String fullName;
        private final int age;
        private final double gpa;

        Student(int id, String fullName, int age, double gpa) {
            this.id = id;
            this.fullName = fullName;
            this.age = age;
            this.gpa = gpa;
            count++;
        }

        void printInfo() {
            System.out.printf("ID: %d, Full Name: %s, Age: %d, GPA: %.2f%n", id, fullName, age, gpa);
        }

        static int getCount() {
            return count;
        }

        @SuppressWarnings("unused")
        static double getMinGpa() {
            return MIN_GPA;
        }

        @SuppressWarnings("unused")
        static double getMaxGpa() {
            return MAX_GPA;
        }
    }

    public static void main(String[] args) {
        Student[] students = {
                new Student(1, "Nguyen Van A", 20, 3.2),
                new Student(2, "Tran Thi B", 21, 3.8),
                new Student(3, "Le Van C", 19, 2.9)
        };

        for (Student student : students) {
            student.printInfo();
        }

        System.out.println("Total students: " + Student.getCount());
    }
}
