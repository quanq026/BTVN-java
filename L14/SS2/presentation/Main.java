package presentation;

import business.StringNumberConverter;
import model.ConversionResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> values = new ArrayList<>();

        try {
            System.out.print("Nhập số lượng chuỗi: ");
            int count = Integer.parseInt(scanner.nextLine().trim());

            if (count <= 0) {
                System.out.println("Lỗi: Số lượng chuỗi phải lớn hơn 0!");
                return;
            }

            for (int i = 0; i < count; i++) {
                System.out.print("Nhập chuỗi thứ " + (i + 1) + ": ");
                values.add(scanner.nextLine());
            }

            StringNumberConverter converter = new StringNumberConverter();
            ConversionResult result = converter.convert(values);

            System.out.println("Số chuỗi hợp lệ: " + result.getValidCount());
            System.out.println("Số chuỗi không hợp lệ: " + result.getInvalidCount());
            System.out.println("Danh sách số nguyên hợp lệ: " + result.getValidNumbers());
        } catch (NumberFormatException e) {
            System.out.println("Lỗi: Vui lòng nhập số nguyên hợp lệ!");
        }
    }
}
