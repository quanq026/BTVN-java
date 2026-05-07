class Exercise1 {
    static class Student {
        private final int id;
        private final String name;
        private final int age;

        Student(int id, String name, int age) {
            this.id = id;
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return "Student{id=" + id + ", name='" + name + "', age=" + age + "}";
        }
    }

    public static void main(String[] args) {
        Student student = new Student(1, "Nguyen Van A", 20);
        System.out.println(student);
    }
}
