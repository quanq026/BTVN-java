class Exercise2 {
    static class Shape {
        double area() {
            return 0;
        }
    }

    static class Rectangle extends Shape {
        private final double width;
        private final double height;

        Rectangle(double width, double height) {
            this.width = width;
            this.height = height;
        }

        @Override
        double area() {
            return width * height;
        }
    }

    static class Circle extends Shape {
        private final double radius;

        Circle(double radius) {
            this.radius = radius;
        }

        @Override
        double area() {
            return Math.PI * radius * radius;
        }
    }

    public static void main(String[] args) {
        Shape rectangle = new Rectangle(2, 3);
        Shape circle = new Circle(1);

        System.out.printf("Dien tich hinh chu nhat: %.2f%n", rectangle.area());
        System.out.printf("Dien tich hinh tron: %.2f%n", circle.area());
    }
}
