import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        System.out.println(docSo(n));
    }

    public static String docSo(int n) {
        if (n < 100 || n > 999) {
            return "Số nhập vào không hợp lệ";
        }

        int tram = n / 100;
        int chuc = (n / 10) % 10;
        int donVi = n % 10;

        String ketQua = docChuSo(tram) + " trăm";

        if (chuc == 0) {
            if (donVi != 0) {
                ketQua += " lẻ " + docDonVi(donVi, chuc);
            }
        } else if (chuc == 1) {
            ketQua += " mười";
            if (donVi != 0) {
                ketQua += " " + docDonVi(donVi, chuc);
            }
        } else {
            ketQua += " " + docChuSo(chuc) + " mươi";
            if (donVi != 0) {
                ketQua += " " + docDonVi(donVi, chuc);
            }
        }

        return vietHoaChuCaiDau(ketQua);
    }

    public static String docChuSo(int n) {
        switch (n) {
            case 1:
                return "một";
            case 2:
                return "hai";
            case 3:
                return "ba";
            case 4:
                return "bốn";
            case 5:
                return "năm";
            case 6:
                return "sáu";
            case 7:
                return "bảy";
            case 8:
                return "tám";
            case 9:
                return "chín";
            default:
                return "";
        }
    }

    public static String docDonVi(int donVi, int chuc) {
        if (donVi == 5 && chuc != 0) {
            return "lăm";
        }
        return docChuSo(donVi);
    }

    public static String vietHoaChuCaiDau(String s) {
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }
}
