package presentation;

import business.PrimeService;
import model.PrimeNumber;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PrimeService primeService = new PrimeService();

        try {
            System.out.print("Nhập một số nguyên: ");
            int value = Integer.parseInt(scanner.nextLine().trim());
            PrimeNumber primeNumber = primeService.createPrimeNumber(value);

            if (primeService.isPrime(primeNumber)) {
                System.out.println(value + " là số nguyên tố");
            } else {
                System.out.println(value + " không phải là số nguyên tố");
            }
        } catch (NumberFormatException e) {
            System.out.println("Lỗi: Vui lòng nhập số nguyên hợp lệ!");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
