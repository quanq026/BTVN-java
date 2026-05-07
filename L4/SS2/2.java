import java.util.Scanner;

public class TongChanLeMang2Chieu {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nhập số hàng của mảng: ");
        int hang = sc.nextInt();
        System.out.print("Nhập số cột của mảng: ");
        int cot = sc.nextInt();

        int[][] arr = new int[hang][cot];

        System.out.println("Nhập các phần tử của mảng:");
        for (int i = 0; i < hang; i++) {
            for (int j = 0; j < cot; j++) {
                System.out.print("Phần tử [" + i + "][" + j + "]: ");
                arr[i][j] = sc.nextInt();
            }
        }

        int tongChan = 0;
        int tongLe = 0;

        for (int i = 0; i < hang; i++) {
            for (int j = 0; j < cot; j++) {
                if (arr[i][j] % 2 == 0) {
                    tongChan += arr[i][j];
                } else {
                    tongLe += arr[i][j];
                }
            }
        }

        System.out.println("Tổng các số chẵn: " + tongChan);
        System.out.println("Tổng các số lẻ: " + tongLe);
    }
}