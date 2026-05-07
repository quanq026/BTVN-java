class Exercise3 {
    static final class CurrencyConverter {
        private static double rate = 25000.0;

        private CurrencyConverter() {
        }

        static void setRate(double newRate) {
            if (newRate <= 0) {
                System.out.println("Ty gia khong hop le");
                return;
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
            return String.format("%.2f USD", usd);
        }
    }

    public static void main(String[] args) {
        int vnd = 500000;
        CurrencyConverter.setRate(25000.0);
        double usd = CurrencyConverter.toUSD(vnd);

        System.out.println("Ty gia hien hanh: " + CurrencyConverter.getRate());
        System.out.println(vnd + " VND = " + CurrencyConverter.formatUSD(usd));
    }
}
