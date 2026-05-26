package business;

import model.BankAccount;

public class AtmService {
    private final BankAccount bankAccount;

    public AtmService() {
        bankAccount = new BankAccount(1000000, 50000);
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void withdraw(double amount) throws AtmException {
        if (amount <= 0) {
            throw new AtmException("Lỗi: Số tiền rút phải lớn hơn 0!");
        }

        if (amount > bankAccount.getBalance()) {
            throw new AtmException("Lỗi: Số tiền rút vượt quá số dư!");
        }

        if (bankAccount.getBalance() - amount < bankAccount.getMinimumBalance()) {
            throw new AtmException("Lỗi: Tài khoản phải duy trì số dư tối thiểu 50.000 đồng!");
        }

        bankAccount.setBalance(bankAccount.getBalance() - amount);
    }
}
