import java.util.Locale;
import java.util.Scanner;

public class BMICalculator {
    private static double readDoubleFlexible(Scanner scanner) {
        String raw = scanner.nextLine().trim().replace(',', '.');
        return Double.parseDouble(raw);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nhap can nang (kg): ");
        double weight = readDoubleFlexible(scanner);

        System.out.print("Nhap chieu cao (m): ");
        double height = readDoubleFlexible(scanner);

        if (height <= 0) {
            System.out.println("Chieu cao phai lon hon 0.");
        } else {
            double bmi = weight / (height * height);
            System.out.printf(Locale.US, "Chi so BMI = %.2f%n", bmi);
        }

        scanner.close();
    }
}
