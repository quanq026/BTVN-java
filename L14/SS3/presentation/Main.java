package presentation;

import business.AtmException;
import business.AtmService;

import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner scanner = new Scanner(System.in);
        AtmService atmService = new AtmService();

        System.out.println("Số dư hiện tại: " + formatMoney(atmService.getBankAccount().getBalance()) + " đồng");
        System.out.print("Nhập số tiền muốn rút: ");

        try {
            double amount = Double.parseDouble(scanner.nextLine().trim());
            atmService.withdraw(amount);

            System.out.println("Số tiền đã rút: " + formatMoney(amount) + " đồng");
            System.out.println("Số dư còn lại: " + formatMoney(atmService.getBankAccount().getBalance()) + " đồng");
        } catch (NumberFormatException e) {
            System.out.println("Lỗi: Vui lòng nhập một số hợp lệ!");
        } catch (AtmException e) {
            System.out.println(e.getMessage());
        }
    }

    private static String formatMoney(double money) {
        return String.format("%.0f", money);
    }
}
