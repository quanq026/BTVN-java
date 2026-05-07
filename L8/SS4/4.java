class Exercise4 {
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
            return 2 * (width + height);
        }

        double getWidth() {
            return width;
        }

        double getHeight() {
            return height;
        }

        void printInfo() {
            System.out.printf("Width: %.2f, Height: %.2f, Area: %.2f, Perimeter: %.2f%n",
                    width, height, getArea(), getPerimeter());
        }
    }

    public static void main(String[] args) {
        Rectangle[] rectangles = {
                new Rectangle(3, 4),
                new Rectangle(5, 2),
                new Rectangle(4.5, 3.5)
        };

        Rectangle largest = rectangles[0];
        int countLargest = 0;

        for (Rectangle rectangle : rectangles) {
            rectangle.printInfo();
        }

        for (Rectangle rectangle : rectangles) {
            if (rectangle.getArea() > largest.getArea()) {
                largest = rectangle;
            }
        }

        for (Rectangle rectangle : rectangles) {
            if (Math.abs(rectangle.getArea() - largest.getArea()) < 1e-9) {
                countLargest++;
            }
        }

        if (countLargest > 1) {
            System.out.printf("Co nhieu hinh co dien tich lon nhat: %.2f%n", largest.getArea());
        } else {
            System.out.printf("Hinh co dien tich lon nhat: %.2f%n", largest.getArea());
            largest.printInfo();
        }
    }
}
