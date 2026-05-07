package com.rikkei.session8.ex6592;

public final class CurrencyConverter {
    private static double rate = 25000.0;

    private CurrencyConverter() {
    }

    public static void setRate(double newRate) {
        if (newRate <= 0) {
            throw new IllegalArgumentException("Rate must be positive");
        }
        rate = newRate;
    }

    public static double getRate() {
        return rate;
    }

    public static double toUSD(int vnd) {
        return vnd / rate;
    }

    public static String formatUSD(double usd) {
        return String.format("$%,.2f", usd);
    }
}
