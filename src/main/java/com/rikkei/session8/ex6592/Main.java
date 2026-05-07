package com.rikkei.session8.ex6592;

public class Main {
    public static void main(String[] args) {
        CurrencyConverter.setRate(25000);
        double usd = CurrencyConverter.toUSD(500000);
        System.out.println("Rate: " + CurrencyConverter.getRate());
        System.out.println("USD: " + CurrencyConverter.formatUSD(usd));
    }
}
