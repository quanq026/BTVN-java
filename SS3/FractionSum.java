import java.util.Scanner;

public class FractionSum {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nhap tu 1 (a): ");
        int a = scanner.nextInt();
        System.out.print("Nhap mau 1 (b): ");
        int b = scanner.nextInt();

        System.out.print("Nhap tu 2 (c): ");
        int c = scanner.nextInt();
        System.out.print("Nhap mau 2 (d): ");
        int d = scanner.nextInt();

        if (b == 0 || d == 0) {
            System.out.println("Mau so phai khac 0.");
        } else {
            int numerator = a * d + c * b;
            int denominator = b * d;
            System.out.println("Ket qua: " + numerator + "/" + denominator);
        }

        scanner.close();
    }
}
