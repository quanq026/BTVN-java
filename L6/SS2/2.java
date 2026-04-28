import java.util.Scanner;

public class QuanLyNguoiDung {
    static Scanner sc = new Scanner(System.in);

    static String hoTen = "";
    static String email = "";
    static String dienThoai = "";
    static String matKhau = "";
    static boolean daNhap = false;

    static final String REGEX_SDT = "^(0)(3[2-9]|5[6-9]|7[0|6-9]|8[0-9]|9[0-9])\\d{7}$";

    static final String REGEX_EMAIL = "^[a-zA-Z0-9._%+\\-]+@[a-zA-Z0-9.\\-]+\\.[a-zA-Z]{2,}$";

    static final String REGEX_MATKHAU =
        "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?]).{8,}$";

    public static void main(String[] args) {
        int luaChon;
        do {
            hienThiMenu();
            System.out.print("Lựa chọn của bạn: ");
            luaChon = sc.nextInt();
            sc.nextLine();
            switch (luaChon) {
                case 1 -> nhapThongTin();
                case 2 -> chuanHoaHoTen();
                case 3 -> kiemTraEmail();
                case 4 -> kiemTraSoDienThoai();
                case 5 -> kiemTraMatKhau();
                case 6 -> System.out.println("Thoát chương trình. Tạm biệt!");
                default -> System.out.println("Lựa chọn không hợp lệ!");
            }
        } while (luaChon != 6);
    }

    static void hienThiMenu() {
        System.out.println("\n****************** QUẢN LÝ NGƯỜI DÙNG ****************");
        System.out.println("1. Nhập thông tin người dùng");
        System.out.println("2. Chuẩn hóa họ tên");
        System.out.println("3. Kiểm tra email hợp lệ");
        System.out.println("4. Kiểm tra số điện thoại hợp lệ");
        System.out.println("5. Kiểm tra mật khẩu hợp lệ");
        System.out.println("6. Thoát");
    }

    static void nhapThongTin() {
        System.out.print("Nhập họ và tên  : ");
        hoTen = sc.nextLine().trim();

        System.out.print("Nhập email       : ");
        email = sc.nextLine().trim();

        System.out.print("Nhập số điện thoại: ");
        dienThoai = sc.nextLine().trim();

        System.out.print("Nhập mật khẩu   : ");
        matKhau = sc.nextLine();

        daNhap = true;
        System.out.println("Nhập thông tin thành công!");
        inThongTin();
    }

    static void chuanHoaHoTen() {
        if (!kiemTraDuLieu()) return;

        String[] words = hoTen.trim().replaceAll("\\s+", " ").split(" ");
        StringBuilder sb = new StringBuilder();

        for (String word : words) {
            if (!word.isEmpty()) {
                sb.append(Character.toUpperCase(word.charAt(0)))
                  .append(word.substring(1).toLowerCase())
                  .append(" ");
            }
        }

        hoTen = sb.toString().trim();
        System.out.println("Họ tên sau khi chuẩn hóa: " + hoTen);
    }

    static void kiemTraEmail() {
        if (!kiemTraDuLieu()) return;

        boolean hopLe = email.matches(REGEX_EMAIL);
        System.out.println("Email     : " + email);
        System.out.println("Kết quả   : " + (hopLe ? "✔ Hợp lệ" : "✘ Không hợp lệ"));

        if (!hopLe) {
            System.out.println("Lý do     : Email phải có dạng example@domain.com");
        }
    }

    static void kiemTraSoDienThoai() {
        if (!kiemTraDuLieu()) return;

        boolean hopLe = dienThoai.matches(REGEX_SDT);
        System.out.println("Số điện thoại: " + dienThoai);
        System.out.println("Kết quả      : " + (hopLe ? "✔ Hợp lệ" : "✘ Không hợp lệ"));

        if (!hopLe) {
            System.out.println("Lý do        : SĐT VN phải 10 chữ số, bắt đầu bằng 03x/05x/07x/08x/09x");
        }
    }

    static void kiemTraMatKhau() {
        if (!kiemTraDuLieu()) return;

        boolean hopLe = matKhau.matches(REGEX_MATKHAU);
        System.out.println("Mật khẩu  : " + matKhau);
        System.out.println("Kết quả   : " + (hopLe ? "✔ Hợp lệ" : "✘ Không hợp lệ"));

        if (!hopLe) {
            System.out.println("Yêu cầu mật khẩu:");
            System.out.println("  " + (matKhau.length() >= 8          ? "✔" : "✘") + " Tối thiểu 8 ký tự (hiện tại: " + matKhau.length() + ")");
            System.out.println("  " + (matKhau.matches(".*[a-z].*")   ? "✔" : "✘") + " Có ít nhất 1 chữ thường (a-z)");
            System.out.println("  " + (matKhau.matches(".*[A-Z].*")   ? "✔" : "✘") + " Có ít nhất 1 chữ hoa (A-Z)");
            System.out.println("  " + (matKhau.matches(".*\\d.*")     ? "✔" : "✘") + " Có ít nhất 1 chữ số (0-9)");
            System.out.println("  " + (matKhau.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?].*") ? "✔" : "✘") + " Có ít nhất 1 ký tự đặc biệt");
        }
    }

    static boolean kiemTraDuLieu() {
        if (!daNhap) {
            System.out.println("Chưa có dữ liệu! Vui lòng nhập thông tin trước (chọn 1).");
            return false;
        }
        return true;
    }

    static void inThongTin() {
        System.out.println("\n--- Thông tin đã nhập ---");
        System.out.println("Họ và tên  : " + hoTen);
        System.out.println("Email      : " + email);
        System.out.println("Điện thoại : " + dienThoai);
        System.out.println("Mật khẩu   : " + matKhau);
    }
}