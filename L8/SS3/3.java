class Exercise3 {
    static final class CurrencyConverter {
        private static double rate = 25000.0;

        private CurrencyConverter() {
        }

        static void setRate(double newRate) {
            if (newRate <= 0) {
                throw new IllegalArgumentException("Rate must be positive");
            }
            rate = newRate;
        }

        static double getRate() {
            return rate;
        }

        static double toUSD(int vnd) {
            return vnd / rate;
        }

        static String formatUSD(double usd) {
            return String.format("$%,.2f", usd);
        }
    }

    public static void main(String[] args) {
        CurrencyConverter.setRate(25000);
        double usd = CurrencyConverter.toUSD(500000);
        System.out.println("Rate: " + CurrencyConverter.getRate());
        System.out.println("USD: " + CurrencyConverter.formatUSD(usd));
    }
}
