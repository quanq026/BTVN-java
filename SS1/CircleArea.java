import java.util.Scanner;

public class CircleArea {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nhap ban kinh hinh tron: ");
        double banKinh = Double.parseDouble(sc.nextLine().replace(",", "."));

        double dienTich = Math.PI * banKinh * banKinh;

        System.out.printf("Dien tich hinh tron la: %.2f%n", dienTich);
    }
}
