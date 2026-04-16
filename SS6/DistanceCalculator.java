import java.util.Scanner;

public class DistanceCalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nhap van toc: ");
        double vanToc = Double.parseDouble(sc.nextLine().replace(",", "."));

        System.out.print("Nhap thoi gian: ");
        double thoiGian = Double.parseDouble(sc.nextLine().replace(",", "."));

        if (vanToc < 0 || thoiGian < 0) {
            System.out.println("Van toc va thoi gian khong duoc nho hon 0.");
        } else {
            double quangDuong = vanToc * thoiGian;
            System.out.println("Quang duong di duoc = " + quangDuong);
        }
    }
}
