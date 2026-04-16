import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n;

        while (true) {
            if (!sc.hasNextInt()) {
                System.out.println("Số nhập vào không hợp lệ");
                sc.next();
                continue;
            }

            n = sc.nextInt();
            if (n > 0) {
                break;
            }

            System.out.println("Số nhập vào không hợp lệ");
        }

        System.out.println(inDanhSachArmstrong(n));
    }

    public static String inDanhSachArmstrong(int n) {
        String ketQua = "";

        for (int i = 0; i <= n; i++) {
            if (laSoArmstrong(i)) {
                if (!ketQua.isEmpty()) {
                    ketQua += " ";
                }
                ketQua += i;
            }
        }

        return ketQua;
    }

    public static boolean laSoArmstrong(int n) {
        if (n == 0) {
            return true;
        }

        int soChuSo = demChuSo(n);
        int tam = n;
        int tong = 0;

        while (tam > 0) {
            int chuSo = tam % 10;
            tong += (int) Math.pow(chuSo, soChuSo);
            tam /= 10;
        }

        return tong == n;
    }

    public static int demChuSo(int n) {
        int dem = 0;

        while (n > 0) {
            dem++;
            n /= 10;
        }

        return dem;
    }
}
