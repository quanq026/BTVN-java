package presentation;

import business.PhoneNumberManager;
import model.InvalidPhone;
import model.PhoneValidationResult;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PhoneNumberManager phoneNumberManager = new PhoneNumberManager();

        System.out.print("Nhập các số điện thoại, phân cách bằng dấu phẩy: ");
        PhoneValidationResult result = phoneNumberManager.checkPhones(scanner.nextLine());

        System.out.println("Số điện thoại hợp lệ:");
        if (result.getValidPhones().isEmpty()) {
            System.out.println("- Không có");
        } else {
            for (String phone : result.getValidPhones()) {
                System.out.println("- " + phone);
            }
        }

        System.out.println();
        System.out.println("Số điện thoại không hợp lệ:");
        if (result.getInvalidPhones().isEmpty()) {
            System.out.println("- Không có");
        } else {
            for (InvalidPhone invalidPhone : result.getInvalidPhones()) {
                System.out.println("- " + invalidPhone.getPhone() + " : " + invalidPhone.getReason());
            }
        }
    }
}
