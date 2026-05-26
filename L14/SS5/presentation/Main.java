package presentation;

import business.BankException;
import business.BankService;
import model.BankAccount;

import java.util.Locale;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final BankService bankService = new BankService();

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        int choice;

        do {
            showMenu();
            choice = inputInteger("Lựa chọn của bạn: ");

            switch (choice) {
                case 1:
                    displayAccounts();
                    break;
                case 2:
                    deposit();
                    break;
                case 3:
                    withdraw();
                    break;
                case 4:
                    transfer();
                    break;
                case 0:
                    System.out.println("Thoát chương trình!");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        } while (choice != 0);
    }

    private static void showMenu() {
        System.out.println("========== MENU QUẢN LÝ GIAO DỊCH ==========");
        System.out.println("1. Xem danh sách tài khoản");
        System.out.println("2. Gửi tiền");
        System.out.println("3. Rút tiền");
        System.out.println("4. Chuyển tiền");
        System.out.println("0. Thoát");
    }

    private static void displayAccounts() {
        for (BankAccount bankAccount : bankService.getBankAccounts()) {
            System.out.println(bankAccount);
        }
    }

    private static void deposit() {
        try {
            String accountId = inputString("Nhập số tài khoản: ");
            double amount = inputDouble("Nhập số tiền cần gửi: ");
            bankService.deposit(accountId, amount);
            System.out.println("Gửi tiền thành công!");
        } catch (BankException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void withdraw() {
        try {
            String accountId = inputString("Nhập số tài khoản: ");
            double amount = inputDouble("Nhập số tiền cần rút: ");
            bankService.withdraw(accountId, amount);
            System.out.println("Rút tiền thành công!");
        } catch (BankException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void transfer() {
        try {
            String fromAccountId = inputString("Nhập tài khoản nguồn: ");
            String toAccountId = inputString("Nhập tài khoản đích: ");
            double amount = inputDouble("Nhập số tiền cần chuyển: ");
            bankService.transfer(fromAccountId, toAccountId, amount);
            System.out.println("Chuyển tiền thành công!");
        } catch (BankException e) {
            System.out.println(e.getMessage());
        }
    }

    private static String inputString(String message) {
        System.out.print(message);
        return scanner.nextLine().trim();
    }

    private static int inputInteger(String message) {
        while (true) {
            try {
                System.out.print(message);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: Vui lòng nhập một số hợp lệ!");
            }
        }
    }

    private static double inputDouble(String message) {
        while (true) {
            try {
                System.out.print(message);
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: Vui lòng nhập một số hợp lệ!");
            }
        }
    }
}
