package business;

import model.PrimeNumber;

public class PrimeService {
    public PrimeNumber createPrimeNumber(int value) {
        if (value <= 0) {
            throw new IllegalArgumentException("Số nhập vào không hợp lệ để kiểm tra số nguyên tố!");
        }

        return new PrimeNumber(value);
    }

    public boolean isPrime(PrimeNumber primeNumber) {
        int number = primeNumber.getValue();

        if (number < 2) {
            return false;
        }

        for (int i = 2; i * i <= number; i++) {
            if (number % i == 0) {
                return false;
            }
        }

        return true;
    }
}
