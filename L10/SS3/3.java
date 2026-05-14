import java.util.Locale;

class Exercise3 {
    static class Computer {
        double calculatePrice(double basePrice) {
            System.out.println("[Using basePrice only]");
            return basePrice;
        }

        double calculatePrice(double basePrice, double tax) {
            System.out.println("[Using basePrice + tax]");
            return basePrice + basePrice * tax / 100;
        }

        double calculatePrice(double basePrice, double tax, double discount) {
            System.out.println("[Using basePrice + tax + discount]");
            double priceAfterTax = basePrice + basePrice * tax / 100;
            return priceAfterTax - discount;
        }
    }

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);

        Computer computer = new Computer();

        double price1 = computer.calculatePrice(1000);
        System.out.printf("Final Price = %.1f%n%n", price1);

        double price2 = computer.calculatePrice(1000, 10);
        System.out.printf("Final Price = %.1f%n%n", price2);

        double price3 = computer.calculatePrice(1000, 10, 50);
        System.out.printf("Final Price = %.1f%n", price3);
    }
}
