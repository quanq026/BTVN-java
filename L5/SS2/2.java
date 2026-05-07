public class StringPerformanceTest {
    public static void main(String[] args) {
        int iterations = 1_000_000;

        long start = System.currentTimeMillis();
        String str = "Hello";
        for (int i = 0; i < iterations; i++) {
            str += " World";
        }
        System.out.println("Thời gian thực thi với String: " + (System.currentTimeMillis() - start) + " ms");

        start = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder("Hello");
        for (int i = 0; i < iterations; i++) {
            sb.append(" World");
        }
        System.out.println("Thời gian thực thi với StringBuilder: " + (System.currentTimeMillis() - start) + " ms");

        start = System.currentTimeMillis();
        StringBuffer sbf = new StringBuffer("Hello");
        for (int i = 0; i < iterations; i++) {
            sbf.append(" World");
        }
        System.out.println("Thời gian thực thi với StringBuffer: " + (System.currentTimeMillis() - start) + " ms");

        System.out.println("\nNhận xét:");
        System.out.println("- String: Không hiệu quả cho phép nối chuỗi nhiều lần do tạo ra nhiều đối tượng mới.");
        System.out.println("- StringBuilder: Hiệu quả và nhanh chóng, thích hợp cho nhiều thao tác nối chuỗi trong một luồng.");
        System.out.println("- StringBuffer: Tương tự như StringBuilder nhưng an toàn với đa luồng, có thể chậm hơn một chút do đồng bộ hóa.");
    }
}