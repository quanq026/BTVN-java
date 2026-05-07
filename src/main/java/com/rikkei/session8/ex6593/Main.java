package com.rikkei.session8.ex6593;

public class Main {
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
