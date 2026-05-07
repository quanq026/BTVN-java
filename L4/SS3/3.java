import java.util.Scanner;

public class SapXepVaTimKiem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nhập số lượng phần tử của mảng: ");
        int n = sc.nextInt();

        int[] arr = new int[n];

        System.out.println("Nhập các phần tử của mảng:");
        for (int i = 0; i < n; i++) {
            System.out.print("Phần tử thứ " + (i + 1) + ": ");
            arr[i] = sc.nextInt();
        }

        for (int i = 0; i < n - 1; i++) {
            int maxIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] > arr[maxIdx]) {
                    maxIdx = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[maxIdx];
            arr[maxIdx] = temp;
        }

        System.out.println("Mảng sau khi sắp xếp giảm dần:");
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();

        System.out.print("Nhập số cần tìm: ");
        int x = sc.nextInt();

        int viTriTuyenTinh = -1;
        for (int i = 0; i < n; i++) {
            if (arr[i] == x) {
                viTriTuyenTinh = i;
                break;
            }
        }

        int viTriNhiPhan = binarySearchGiamDan(arr, x);

        if (viTriTuyenTinh != -1) {
            System.out.println("Tìm kiếm tuyến tính: Số " + x + " có tại vị trí " + (viTriTuyenTinh + 1));
        } else {
            System.out.println("Tìm kiếm tuyến tính: Không tìm thấy số " + x);
        }

        if (viTriNhiPhan != -1) {
            System.out.println("Tìm kiếm nhị phân: Số " + x + " có tại vị trí " + (viTriNhiPhan + 1));
        } else {
            System.out.println("Tìm kiếm nhị phân: Không tìm thấy số " + x);
        }

        sc.close();
    }

    public static int binarySearchGiamDan(int[] arr, int x) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] == x) {
                return mid;
            }

            if (arr[mid] > x) {
                left = mid + 1;
                right = mid - 1;
            }
        }
        return -1;
    }
}