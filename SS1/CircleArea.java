import java.util.Scanner;
import java.util.Locale;

public class CircleArea {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nhap ban kinh: ");
        double radius = scanner.nextDouble();

        double area = Math.PI * radius * radius;

        System.out.printf(Locale.US, "Dien tich: %.2f%n", area);

        scanner.close();
    }
}
