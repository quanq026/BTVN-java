import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        if (n < 0) {
            n = -n;
        }

        int tong = 0;

        if (n == 0) {
            tong = 0;
        } else {
            while (n > 0) {
                tong += n % 10;
                n = n / 10;
            }
        }

        System.out.println("Tổng các chữ số là: " + tong);
    }
}
