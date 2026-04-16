import java.util.Scanner;

public class BMICalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nhap can nang: ");
        double canNang = Double.parseDouble(sc.nextLine().replace(",", "."));

        System.out.print("Nhap chieu cao: ");
        double chieuCao = Double.parseDouble(sc.nextLine().replace(",", "."));

        if (chieuCao <= 0) {
            System.out.println("Chieu cao phai lon hon 0.");
        } else {
            double bmi = canNang / (chieuCao * chieuCao);
            System.out.printf("Chi so BMI = %.2f%n", bmi);
        }
    }
}
