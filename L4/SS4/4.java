import java.util.Scanner;

public class SapXepChanLe {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nhập số lượng phần tử của mảng: ");
        int n = sc.nextInt();

        if (n <= 0) {
            System.out.println("Mảng không có phần tử");
            return;
        }

        int[] arr = new int[n];

        System.out.println("Nhập các phần tử của mảng:");
        for (int i = 0; i < n; i++) {
            System.out.print("Phần tử thứ " + (i + 1) + ": ");
            arr[i] = sc.nextInt();
        }

        int[] chan = new int[n];
        int[] le = new int[n];
        int indexChan = 0;
        int indexLe = 0;

        for (int i = 0; i < n; i++) {
            if (arr[i] % 2 == 0) {
                chan[indexChan++] = arr[i];
            } else {
                le[indexLe++] = arr[i];
            }
        }

        int[] ketQua = new int[n];
        int k = 0;

        for (int i = 0; i < indexChan; i++) {
            ketQua[k++] = chan[i];
        }

        for (int i = 0; i < indexLe; i++) {
            ketQua[k++] = le[i];
        }

        System.out.print("Mảng sau khi sắp xếp: ");
        for (int i = 0; i < n; i++) {
            System.out.print(ketQua[i] + " ");
        }
    }
}