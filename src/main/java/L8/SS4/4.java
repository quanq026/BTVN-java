class Exercise4 {
    static final class Rectangle {
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

        @Override
        public String toString() {
            return String.format("Rectangle(width=%.2f, height=%.2f, area=%.2f, perimeter=%.2f)",
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
        boolean tie = false;

        for (Rectangle rectangle : rectangles) {
            System.out.println(rectangle);
            if (rectangle.getArea() > largest.getArea()) {
                largest = rectangle;
                tie = false;
            } else if (Math.abs(rectangle.getArea() - largest.getArea()) < 1e-9 && rectangle != largest) {
                tie = true;
            }
        }

        if (tie) {
            System.out.println("There are multiple rectangles with the same largest area: " + largest.getArea());
        } else {
            System.out.println("Largest area: " + largest.getArea() + " -> " + largest);
        }
    }
}
