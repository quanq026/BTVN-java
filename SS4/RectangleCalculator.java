import java.util.Locale;
import java.util.Scanner;

public class RectangleCalculator {
    private static float readFloatFlexible(Scanner scanner) {
        String raw = scanner.nextLine().trim().replace(',', '.');
        return Float.parseFloat(raw);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nhap width: ");
        float width = readFloatFlexible(scanner);

        System.out.print("Nhap height: ");
        float height = readFloatFlexible(scanner);

        float area = width * height;
        float perimeter = 2 * (width + height);

        System.out.printf(Locale.US, "Dien tich : %.2f%n", area);
        System.out.printf(Locale.US, "Chu vi : %.2f%n", perimeter);

        scanner.close();
    }
}
