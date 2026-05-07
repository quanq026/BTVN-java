import java.util.Scanner;

public class QuanLyBienSoXe {
    static Scanner sc = new Scanner(System.in);

    static final int MAX = 100;
    static String[] dsBienSo = new String[MAX];
    static int soBienSo = 0;

    static final String REGEX_BIEN_SO = "^\\d{2}[A-Z]{1,2}-\\d{3}\\.\\d{2}$";

    public static void main(String[] args) {
        int luaChon;
        do {
            hienThiMenu();
            System.out.print("Lựa chọn của bạn: ");
            luaChon = sc.nextInt();
            sc.nextLine();
            switch (luaChon) {
                case 1 -> themBienSo();
                case 2 -> hienThiDanhSach();
                case 3 -> timKiemBienSo();
                case 4 -> timTheoMaTinh();
                case 5 -> sapXepTangDan();
                case 6 -> System.out.println("Thoát chương trình. Tạm biệt!");
                default -> System.out.println("Lựa chọn không hợp lệ!");
            }
        } while (luaChon != 6);
    }

    static void hienThiMenu() {
        System.out.println("\n****************** QUẢN LÝ BIỂN SỐ XE ****************");
        System.out.println("1. Thêm các biển số xe");
        System.out.println("2. Hiển thị danh sách biển số xe");
        System.out.println("3. Tìm kiếm biển số xe");
        System.out.println("4. Tìm biển số xe theo mã tỉnh");
        System.out.println("5. Sắp xếp biển số xe tăng dần");
        System.out.println("6. Thoát");
    }

    static void themBienSo() {
        if (soBienSo >= MAX) {
            System.out.println("Danh sách đã đầy (" + MAX + " biển số)!");
            return;
        }

        System.out.print("Nhập số lượng biển số cần thêm: ");
        int n = sc.nextInt();
        sc.nextLine();

        int themDuoc = 0;
        for (int i = 0; i < n; i++) {
            if (soBienSo >= MAX) {
                System.out.println("Danh sách đã đầy, không thể thêm!");
                break;
            }
            System.out.print("Nhập biển số " + (i + 1) + " (VD: 30F-123.45): ");
            String bs = sc.nextLine().trim().toUpperCase();

            if (!bs.matches(REGEX_BIEN_SO)) {
                System.out.println("  ✘ Định dạng không hợp lệ! Bỏ qua biển số này.");
                System.out.println("    Định dạng đúng: <2 chữ số><1-2 chữ hoa>-<3 chữ số>.<2 chữ số>");
                System.out.println("    Ví dụ: 30F-123.45 hoặc 51A1-234.56");
                i--;
                continue;
            }

            if (daToTai(bs)) {
                System.out.println("  ✘ Biển số \"" + bs + "\" đã tồn tại trong danh sách!");
                i--;
                continue;
            }

            dsBienSo[soBienSo++] = bs;
            System.out.println("  ✔ Đã thêm: " + bs);
            themDuoc++;
        }
        System.out.println("Thêm thành công " + themDuoc + " biển số. Tổng hiện tại: " + soBienSo);
    }

    static void hienThiDanhSach() {
        if (!kiemTraDuLieu()) return;

        System.out.println("\n" + "-".repeat(40));
        System.out.printf("%-5s %-15s %-10s %-10s%n", "STT", "Biển số", "Mã tỉnh", "Series");
        System.out.println("-".repeat(40));

        for (int i = 0; i < soBienSo; i++) {
            String bs = dsBienSo[i];
            System.out.printf("%-5d %-15s %-10s %-10s%n",
                    (i + 1), bs, layMaTinh(bs), laySeries(bs));
        }
        System.out.println("-".repeat(40));
        System.out.println("Tổng: " + soBienSo + " biển số");
    }

    static void timKiemBienSo() {
        if (!kiemTraDuLieu()) return;

        System.out.print("Nhập biển số cần tìm: ");
        String tuKhoa = sc.nextLine().trim().toUpperCase();

        int viTri = -1;
        for (int i = 0; i < soBienSo; i++) {
            if (dsBienSo[i].equals(tuKhoa)) {
                viTri = i;
                break;
            }
        }

        if (viTri == -1) {
            System.out.println("✘ Không tìm thấy biển số \"" + tuKhoa + "\".");
        } else {
            System.out.println("✔ Tìm thấy tại vị trí " + (viTri + 1) + ":");
            System.out.println("  Biển số : " + dsBienSo[viTri]);
            System.out.println("  Mã tỉnh : " + layMaTinh(dsBienSo[viTri]));
            System.out.println("  Series  : " + laySeries(dsBienSo[viTri]));
        }
    }

    static void timTheoMaTinh() {
        if (!kiemTraDuLieu()) return;

        System.out.print("Nhập mã tỉnh cần tìm (VD: 29, 30, 51): ");
        String maTinh = sc.nextLine().trim();

        if (!maTinh.matches("\\d{2}")) {
            System.out.println("Mã tỉnh không hợp lệ! Phải là 2 chữ số (VD: 29, 30, 51).");
            return;
        }

        System.out.println("\nBiển số xe thuộc mã tỉnh [" + maTinh + "]:");
        System.out.println("-".repeat(30));

        int dem = 0;
        for (int i = 0; i < soBienSo; i++) {
            if (layMaTinh(dsBienSo[i]).equals(maTinh)) {
                System.out.println("  " + (dem + 1) + ". " + dsBienSo[i]);
                dem++;
            }
        }

        System.out.println("-".repeat(30));
        if (dem == 0) {
            System.out.println("Không tìm thấy biển số nào thuộc mã tỉnh " + maTinh + ".");
        } else {
            System.out.println("Tổng: " + dem + " biển số.");
        }
    }

    static void sapXepTangDan() {
        if (!kiemTraDuLieu()) return;

        for (int i = 1; i < soBienSo; i++) {
            String key = dsBienSo[i];
            int j = i - 1;
            while (j >= 0 && dsBienSo[j].compareTo(key) > 0) {
                dsBienSo[j + 1] = dsBienSo[j];
                j--;
            }
            dsBienSo[j + 1] = key;
        }

        System.out.println("✔ Đã sắp xếp biển số xe tăng dần.");
        hienThiDanhSach();
    }

    static String layMaTinh(String bienSo) {
        return bienSo.substring(0, 2);
    }

    static String laySeries(String bienSo) {
        int gach = bienSo.indexOf('-');
        return bienSo.substring(2, gach);
    }

    static boolean daToTai(String bienSo) {
        for (int i = 0; i < soBienSo; i++) {
            if (dsBienSo[i].equals(bienSo)) return true;
        }
        return false;
    }

    static boolean kiemTraDuLieu() {
        if (soBienSo == 0) {
            System.out.println("Danh sách trống! Vui lòng thêm biển số trước (chọn 1).");
            return false;
        }
        return true;
    }
}