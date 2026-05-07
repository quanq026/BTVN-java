import java.util.Scanner;

public class RectangleCalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nhap chieu rong: ");
        float chieuRong = Float.parseFloat(sc.nextLine().replace(",", "."));

        System.out.print("Nhap chieu dai: ");
        float chieuDai = Float.parseFloat(sc.nextLine().replace(",", "."));

        float dienTich = chieuRong * chieuDai;
        float chuVi = (chieuRong + chieuDai) * 2;

        System.out.println("Dien tich hinh chu nhat = " + dienTich);
        System.out.println("Chu vi hinh chu nhat = " + chuVi);
    }
}
