import java.util.Scanner;

public class QuanLyDiemSinhVien {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n;
        double[] diem;

        System.out.print("Nhập số lượng sinh viên: ");
        n = sc.nextInt();
        diem = new double[n];

        System.out.println("Nhập điểm sinh viên:");
        for (int i = 0; i < n; i++) {
            System.out.print("Nhập điểm sinh viên thứ " + (i + 1) + ": ");
            diem[i] = sc.nextDouble();
        }

        int luaChon;
        do {
            System.out.println("\n--- QUẢN LÝ ĐIỂM SINH VIÊN ---");
            System.out.println("1. Xem tất cả điểm");
            System.out.println("2. Sắp xếp điểm");
            System.out.println("3. Tìm kiếm điểm");
            System.out.println("4. Thống kê điểm");
            System.out.println("5. Thoát");
            System.out.print("Lựa chọn của bạn: ");
            luaChon = sc.nextInt();

            switch (luaChon) {
                case 1:
                    xemTatCaDiem(diem);
                    break;
                case 2:
                    sapXepDiem(diem, sc);
                    break;
                case 3:
                    timKiemDiem(diem, sc);
                    break;
                case 4:
                    thongKeDiem(diem);
                    break;
                case 5:
                    System.out.println("Thoát chương trình.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        } while (luaChon != 5);

        sc.close();
    }

    public static void xemTatCaDiem(double[] diem) {
        System.out.println("Danh sách điểm:");
        for (int i = 0; i < diem.length; i++) {
            System.out.println("Sinh viên " + (i + 1) + ": " + diem[i]);
        }
    }

    public static void sapXepDiem(double[] diem, Scanner sc) {
        System.out.println("Chọn cách sắp xếp:");
        System.out.println("1. Tăng dần");
        System.out.println("2. Giảm dần");
        int cach = sc.nextInt();

        if (cach == 1) {
            for (int i = 0; i < diem.length - 1; i++) {
                for (int j = 0; j < diem.length - i - 1; j++) {
                    if (diem[j] > diem[j + 1]) {
                        double temp = diem[j];
                        diem[j] = diem[j + 1];
                        diem[j + 1] = temp;
                    }
                }
            }
            System.out.println("Đã sắp xếp tăng dần.");
        } else if (cach == 2) {
            for (int i = 0; i < diem.length - 1; i++) {
                for (int j = 0; j < diem.length - i - 1; j++) {
                    if (diem[j] < diem[j + 1]) {
                        double temp = diem[j];
                        diem[j] = diem[j + 1];
                        diem[j + 1] = temp;
                    }
                }
            }
            System.out.println("Đã sắp xếp giảm dần.");
        } else {
            System.out.println("Lựa chọn không hợp lệ!");
        }
    }

    public static void timKiemDiem(double[] diem, Scanner sc) {
        System.out.print("Nhập điểm cần tìm: ");
        double x = sc.nextDouble();

        boolean timThay = false;
        System.out.print("Tìm kiếm tuyến tính: ");
        for (int i = 0; i < diem.length; i++) {
            if (diem[i] == x) {
                System.out.println("Tìm thấy tại vị trí " + (i + 1));
                timThay = true;
                break;
            }
        }
        if (!timThay) {
            System.out.println("Không tìm thấy");
        }

        System.out.print("Tìm kiếm nhị phân (mảng tăng dần): ");
        int left = 0, right = diem.length - 1;
        timThay = false;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (diem[mid] == x) {
                System.out.println("Tìm thấy tại vị trí " + (mid + 1));
                timThay = true;
                break;
            } else if (diem[mid] < x) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        if (!timThay) {
            System.out.println("Không tìm thấy");
        }
    }

    public static void thongKeDiem(double[] diem) {
        double tong = 0;
        double max = diem[0];
        double min = diem[0];
        int demTrenTB = 0;

        for (double d : diem) {
            tong += d;
            if (d > max) max = d;
            if (d < min) min = d;
        }

        double trungBinh = tong / diem.length;

        for (double d : diem) {
            if (d > trungBinh) demTrenTB++;
        }

        System.out.println("Điểm trung bình: " + trungBinh);
        System.out.println("Điểm cao nhất: " + max);
        System.out.println("Điểm thấp nhất: " + min);
        System.out.println("Số sinh viên có điểm trên trung bình: " + demTrenTB);
    }
}