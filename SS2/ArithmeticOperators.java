import java.util.Scanner;

public class ArithmeticOperators {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nhap so thu nhat (firstNumber): ");
        int firstNumber = scanner.nextInt();

        System.out.print("Nhap so thu hai (secondNumber): ");
        int secondNumber = scanner.nextInt();

        int sum = firstNumber + secondNumber;
        int difference = firstNumber - secondNumber;
        int product = firstNumber * secondNumber;

        System.out.println("\n--- Ket qua ---");
        System.out.println("firstNumber = " + firstNumber);
        System.out.println("secondNumber = " + secondNumber);
        System.out.println("Tong = " + sum);
        System.out.println("Hieu = " + difference);
        System.out.println("Tich = " + product);

        if (secondNumber != 0) {
            int quotient = firstNumber / secondNumber;
            int remainder = firstNumber % secondNumber;
            System.out.println("Thuong = " + quotient);
            System.out.println("Phan du = " + remainder);
        } else {
            System.out.println("Thuong = khong xac dinh (chia cho 0)");
            System.out.println("Phan du = khong xac dinh (chia cho 0)");
        }

        scanner.close();
    }
}
