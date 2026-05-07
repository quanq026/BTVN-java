import java.util.Scanner;

public class PasswordValidator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập mật khẩu: ");
        String password = scanner.nextLine();

        boolean isValid = password.length() >= 8
                && password.matches(".*[A-Z].*")
                && password.matches(".*[a-z].*")
                && password.matches(".*[0-9].*")
                && password.matches(".*[@#$!%].*");

        System.out.println(isValid ? "Mật khẩu hợp lệ" : "Mật khẩu không hợp lệ");

        scanner.close();
    }
}