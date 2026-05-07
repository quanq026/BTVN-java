import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        if (n <= 0) {
            System.out.println("Số nhập vào không hợp lệ.");
        } else {
            int tong = 0;

            for (int i = 1; i <= n; i++) {
                tong += i;
            }

            System.out.println("Tổng các số từ 1 đến " + n + " là: " + tong);
        }
    }
}
