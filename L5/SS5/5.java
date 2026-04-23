import java.util.Scanner;

public class StringManager {
    static Scanner scanner = new Scanner(System.in);
    static String currentString = "";

    public static void main(String[] args) {
        while (true) {
            printMenu();
            System.out.print("Chọn: ");
            int choice = Integer.parseInt(scanner.nextLine().trim());

            switch (choice) {
                case 1 -> nhapChuoi();
                case 2 -> demKyTu();
                case 3 -> daoNguoc();
                case 4 -> kiemTraPalindrome();
                case 5 -> chuanHoa();
                case 6 -> {
                    System.out.println("Tạm biệt!");
                    return;
                }
                default -> System.out.println("Lựa chọn không hợp lệ.");
            }
        }
    }

    static void printMenu() {
        System.out.println("*************************** MENU ***************************");
        System.out.println("1. Nhập chuỗi");
        System.out.println("2. Đếm số ký tự thường, hoa, số, đặc biệt");
        System.out.println("3. Đảo ngược chuỗi");
        System.out.println("4. Kiểm tra Palindrome");
        System.out.println("5. Chuẩn hóa chuỗi (xóa khoảng trắng dư thừa, viết hoa chữ cái đầu)");
        System.out.println("6. Thoát");
        System.out.println("************************************************************");
    }

    static void nhapChuoi() {
        System.out.print("Nhập chuỗi: ");
        currentString = scanner.nextLine();
    }

    static void demKyTu() {
        int lower = 0, upper = 0, digit = 0, special = 0;
        for (char c : currentString.toCharArray()) {
            if (Character.isLowerCase(c)) lower++;
            else if (Character.isUpperCase(c)) upper++;
            else if (Character.isDigit(c)) digit++;
            else if (c != ' ') special++;
        }
        System.out.println("Số ký tự thường: " + lower);
        System.out.println("Số ký tự hoa: " + upper);
        System.out.println("Số chữ số: " + digit);
        System.out.println("Số ký tự đặc biệt: " + special);
    }

    static void daoNguoc() {
        System.out.println("Chuỗi đảo ngược: " + new StringBuilder(currentString).reverse());
    }

    static void kiemTraPalindrome() {
        String cleaned = currentString.replaceAll("\\s+", "").toLowerCase();
        String reversed = new StringBuilder(cleaned).reverse().toString();
        if (cleaned.equals(reversed)) {
            System.out.println("Chuỗi là Palindrome.");
        } else {
            System.out.println("Chuỗi không phải Palindrome.");
        }
    }

    static void chuanHoa() {
        String trimmed = currentString.trim().replaceAll("\\s+", " ");
        String result = trimmed.isEmpty() ? "" : Character.toUpperCase(trimmed.charAt(0)) + trimmed.substring(1);
        System.out.println("Chuỗi sau khi chuẩn hóa: " + result);
    }
}