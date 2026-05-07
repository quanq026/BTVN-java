class Exercise1 {
    static class Rectangle {
        private final double width;
        private final double height;

        Rectangle(double width, double height) {
            this.width = width;
            this.height = height;
        }

        double getArea() {
            return width * height;
        }

        double getPerimeter() {
            return (width + height) * 2;
        }

        void printInfo() {
            System.out.printf("Rectangle(width=%.2f, height=%.2f, area=%.2f, perimeter=%.2f)%n",
                    width, height, getArea(), getPerimeter());
        }
    }

    public static void main(String[] args) {
        Rectangle rectangle = new Rectangle(3, 4);
        rectangle.printInfo();
        System.out.printf("Area: %.2f%n", rectangle.getArea());
        System.out.printf("Perimeter: %.2f%n", rectangle.getPerimeter());
    }
}
