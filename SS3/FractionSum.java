import java.util.Scanner;

public class FractionSum {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nhap tu so thu nhat: ");
        int a = sc.nextInt();
        System.out.print("Nhap mau so thu nhat: ");
        int b = sc.nextInt();

        System.out.print("Nhap tu so thu hai: ");
        int c = sc.nextInt();
        System.out.print("Nhap mau so thu hai: ");
        int d = sc.nextInt();

        if (b == 0 || d == 0) {
            System.out.println("Mau so phai khac 0.");
        } else {
            int tu = a * d + c * b;
            int mau = b * d;
            System.out.println("Tong hai phan so la: " + tu + "/" + mau);
        }
    }
}
