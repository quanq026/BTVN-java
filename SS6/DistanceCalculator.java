import java.util.Locale;
import java.util.Scanner;

public class DistanceCalculator {
    private static double readDoubleFlexible(Scanner scanner) {
        String raw = scanner.nextLine().trim().replace(',', '.');
        return Double.parseDouble(raw);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nhap van toc (km/h): ");
        double speed = readDoubleFlexible(scanner);

        System.out.print("Nhap thoi gian di (gio): ");
        double time = readDoubleFlexible(scanner);

        if (speed < 0 || time < 0) {
            System.out.println("Van toc va thoi gian khong duoc nho hon 0.");
        } else {
            double distance = speed * time;
            if (distance == Math.floor(distance)) {
                System.out.printf(Locale.US, "Quang duong = %.0f (km)%n", distance);
            } else {
                System.out.printf(Locale.US, "Quang duong = %.2f (km)%n", distance);
            }
        }

        scanner.close();
    }
}
