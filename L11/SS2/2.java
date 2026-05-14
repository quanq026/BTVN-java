import java.util.Locale;

class Exercise2 {
    interface Refundable {
        void refund();
    }

    static abstract class Payment {
        protected double amount;

        Payment(double amount) {
            this.amount = amount;
        }

        abstract void pay();

        void printAmount() {
            System.out.printf("So tien: %.2f%n", amount);
        }
    }

    static class CashPayment extends Payment {
        CashPayment(double amount) {
            super(amount);
        }

        @Override
        void pay() {
            System.out.println("Thanh toan bang tien mat");
            printAmount();
        }
    }

    static class CreditCardPayment extends Payment implements Refundable {
        CreditCardPayment(double amount) {
            super(amount);
        }

        @Override
        void pay() {
            System.out.println("Thanh toan bang the tin dung");
            printAmount();
        }

        @Override
        public void refund() {
            System.out.println("Hoan tien ve the tin dung");
        }
    }

    static class EWalletPayment extends Payment implements Refundable {
        EWalletPayment(double amount) {
            super(amount);
        }

        @Override
        void pay() {
            System.out.println("Thanh toan bang vi dien tu");
            printAmount();
        }

        @Override
        public void refund() {
            System.out.println("Hoan tien ve vi dien tu");
        }
    }

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);

        Payment[] payments = {
                new CashPayment(500000),
                new CreditCardPayment(1200000),
                new EWalletPayment(300000)
        };

        for (Payment payment : payments) {
            payment.pay();
            if (payment instanceof Refundable refundable) {
                refundable.refund();
            }
            System.out.println();
        }
    }
}
