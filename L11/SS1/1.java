import java.util.Locale;

class Exercise1 {
    interface Drawable {
        void draw();
    }

    static abstract class Shape {
        protected String name;

        Shape(String name) {
            this.name = name;
        }

        abstract double getArea();

        abstract double getPerimeter();

        void displayInfo() {
            System.out.println("Ten hinh: " + name);
        }
    }

    static class Rectangle extends Shape implements Drawable {
        private final double width;
        private final double height;

        Rectangle(double width, double height) {
            super("Rectangle");
            this.width = width;
            this.height = height;
        }

        @Override
        double getArea() {
            return width * height;
        }

        @Override
        double getPerimeter() {
            return (width + height) * 2;
        }

        @Override
        public void draw() {
            System.out.println("Da ve hinh chu nhat");
        }
    }

    static class Circle extends Shape implements Drawable {
        private final double radius;

        Circle(double radius) {
            super("Circle");
            this.radius = radius;
        }

        @Override
        double getArea() {
            return Math.PI * radius * radius;
        }

        @Override
        double getPerimeter() {
            return 2 * Math.PI * radius;
        }

        @Override
        public void draw() {
            System.out.println("Da ve hinh tron");
        }
    }

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);

        Shape[] shapes = {
                new Rectangle(4, 5),
                new Circle(3)
        };

        for (Shape shape : shapes) {
            shape.displayInfo();
            System.out.printf("Dien tich: %.2f%n", shape.getArea());
            System.out.printf("Chu vi: %.2f%n", shape.getPerimeter());
            if (shape instanceof Drawable drawable) {
                drawable.draw();
            }
            System.out.println();
        }
    }
}
