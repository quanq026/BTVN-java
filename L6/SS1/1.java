import java.util.Scanner;

public class QuanLyDiemSV {
    static Scanner sc = new Scanner(System.in);
    static double[] diem;
    static String[] tenSV;
    static int soSV = 0;

    public static void main(String[] args) {
        int luaChon;
        do {
            hienThiMenu();
            System.out.print("Lựa chọn của bạn: ");
            luaChon = sc.nextInt();
            switch (luaChon) {
                case 1 -> nhapDanhSach();
                case 2 -> inDanhSach();
                case 3 -> tinhDiemTrungBinh();
                case 4 -> timDiemCaoNhatThapNhat();
                case 5 -> demDatTruot();
                case 6 -> sapXepTangDan();
                case 7 -> thongKeGioiXuatSac();
                case 8 -> System.out.println("Thoát chương trình. Tạm biệt!");
                default -> System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }
        } while (luaChon != 8);
    }

    static void hienThiMenu() {
        System.out.println("\n******************QUẢN LÝ ĐIỂM SV*****************");
        System.out.println("1. Nhập danh sách điểm sinh viên");
        System.out.println("2. In danh sách điểm");
        System.out.println("3. Tính điểm trung bình của các sinh viên");
        System.out.println("4. Tìm điểm cao nhất và thấp nhất");
        System.out.println("5. Đếm số lượng sinh viên đạt và trượt");
        System.out.println("6. Sắp xếp điểm tăng dần");
        System.out.println("7. Thống kê số lượng sinh viên giỏi và xuất sắc");
        System.out.println("8. Thoát");
    }

    static void nhapDanhSach() {
        System.out.print("Nhập số lượng sinh viên: ");
        soSV = sc.nextInt();
        sc.nextLine();
        diem = new double[soSV];
        tenSV = new String[soSV];

        for (int i = 0; i < soSV; i++) {
            System.out.print("Nhập tên sinh viên " + (i + 1) + ": ");
            tenSV[i] = sc.nextLine();
            System.out.print("Nhập điểm sinh viên " + (i + 1) + " (0 - 10): ");
            diem[i] = sc.nextDouble();
            sc.nextLine();

            while (diem[i] < 0 || diem[i] > 10) {
                System.out.print("Điểm không hợp lệ! Nhập lại (0 - 10): ");
                diem[i] = sc.nextDouble();
                sc.nextLine();
            }
        }
        System.out.println("Nhập danh sách thành công!");
    }

    static void inDanhSach() {
        if (!kiemTraDuLieu()) return;
        System.out.println("\n" + "-".repeat(45));
        System.out.printf("%-5s %-20s %-10s %-10s%n", "STT", "Họ tên", "Điểm", "Kết quả");
        System.out.println("-".repeat(45));
        for (int i = 0; i < soSV; i++) {
            String ketQua = diem[i] >= 5 ? "Đạt" : "Trượt";
            System.out.printf("%-5d %-20s %-10.1f %-10s%n", (i + 1), tenSV[i], diem[i], ketQua);
        }
        System.out.println("-".repeat(45));
    }

    static void tinhDiemTrungBinh() {
        if (!kiemTraDuLieu()) return;
        double tong = 0;
        for (double d : diem) tong += d;
        double trungBinh = tong / soSV;
        System.out.printf("Điểm trung bình của lớp: %.2f%n", trungBinh);
    }

    static void timDiemCaoNhatThapNhat() {
        if (!kiemTraDuLieu()) return;
        double max = diem[0], min = diem[0];
        int iMax = 0, iMin = 0;

        for (int i = 1; i < soSV; i++) {
            if (diem[i] > max) { max = diem[i]; iMax = i; }
            if (diem[i] < min) { min = diem[i]; iMin = i; }
        }

        System.out.printf("Điểm cao nhất: %.1f - %s%n", max, tenSV[iMax]);
        System.out.printf("Điểm thấp nhất: %.1f - %s%n", min, tenSV[iMin]);
    }

    static void demDatTruot() {
        if (!kiemTraDuLieu()) return;
        int dat = 0, truot = 0;
        for (double d : diem) {
            if (d >= 5) dat++;
            else truot++;
        }
        System.out.println("Số sinh viên đạt  : " + dat);
        System.out.println("Số sinh viên trượt: " + truot);
    }

    static void sapXepTangDan() {
        if (!kiemTraDuLieu()) return;

        double[] diemCopy = diem.clone();
        String[] tenCopy = tenSV.clone();

        for (int i = 0; i < soSV - 1; i++) {
            for (int j = 0; j < soSV - i - 1; j++) {
                if (diemCopy[j] > diemCopy[j + 1]) {
                    double tmpD = diemCopy[j]; diemCopy[j] = diemCopy[j + 1]; diemCopy[j + 1] = tmpD;
                    String tmpT = tenCopy[j]; tenCopy[j] = tenCopy[j + 1]; tenCopy[j + 1] = tmpT;
                }
            }
        }

        System.out.println("\nDanh sách điểm sắp xếp tăng dần:");
        System.out.println("-".repeat(35));
        System.out.printf("%-5s %-20s %-10s%n", "STT", "Họ tên", "Điểm");
        System.out.println("-".repeat(35));
        for (int i = 0; i < soSV; i++) {
            System.out.printf("%-5d %-20s %-10.1f%n", (i + 1), tenCopy[i], diemCopy[i]);
        }
        System.out.println("-".repeat(35));
    }

    static void thongKeGioiXuatSac() {
        if (!kiemTraDuLieu()) return;
        int soLuong = 0;
        System.out.println("\nDanh sách sinh viên Giỏi & Xuất sắc (điểm >= 8):");
        System.out.println("-".repeat(35));
        for (int i = 0; i < soSV; i++) {
            if (diem[i] >= 8) {
                System.out.printf("%-20s %.1f%n", tenSV[i], diem[i]);
                soLuong++;
            }
        }
        System.out.println("-".repeat(35));
        System.out.println("Tổng số sinh viên Giỏi & Xuất sắc: " + soLuong);
    }

    static boolean kiemTraDuLieu() {
        if (soSV == 0 || diem == null) {
            System.out.println("Chưa có dữ liệu! Vui lòng nhập danh sách trước (chọn 1).");
            return false;
        }
        return true;
    }
}