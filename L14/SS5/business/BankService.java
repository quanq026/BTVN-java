package business;

import model.BankAccount;

import java.util.ArrayList;
import java.util.List;

public class BankService {
    private final List<BankAccount> bankAccounts = new ArrayList<>();

    public BankService() {
        bankAccounts.add(new BankAccount("ACC001", 1000000));
        bankAccounts.add(new BankAccount("ACC002", 500000));
        bankAccounts.add(new BankAccount("ACC003", 200000));
    }

    public List<BankAccount> getBankAccounts() {
        return bankAccounts;
    }

    public BankAccount findById(String accountId) {
        for (BankAccount bankAccount : bankAccounts) {
            if (bankAccount.getAccountId().equalsIgnoreCase(accountId)) {
                return bankAccount;
            }
        }

        return null;
    }

    public void deposit(String accountId, double amount) throws BankException {
        validateAmount(amount);
        BankAccount bankAccount = findExistingAccount(accountId);
        bankAccount.setBalance(bankAccount.getBalance() + amount);
    }

    public void withdraw(String accountId, double amount) throws BankException {
        validateAmount(amount);
        BankAccount bankAccount = findExistingAccount(accountId);

        if (amount > bankAccount.getBalance()) {
            throw new BankException("Lỗi: Số dư không đủ để rút tiền!");
        }

        bankAccount.setBalance(bankAccount.getBalance() - amount);
    }

    public void transfer(String fromAccountId, String toAccountId, double amount) throws BankException {
        validateAmount(amount);
        BankAccount fromAccount = findExistingAccount(fromAccountId);
        BankAccount toAccount = findById(toAccountId);

        if (toAccount == null) {
            throw new BankException("Lỗi: Tài khoản đích không tồn tại!");
        }

        if (amount > fromAccount.getBalance()) {
            throw new BankException("Lỗi: Số dư không đủ để chuyển tiền!");
        }

        fromAccount.setBalance(fromAccount.getBalance() - amount);
        toAccount.setBalance(toAccount.getBalance() + amount);
    }

    private BankAccount findExistingAccount(String accountId) throws BankException {
        BankAccount bankAccount = findById(accountId);

        if (bankAccount == null) {
            throw new BankException("Lỗi: Không tìm thấy tài khoản!");
        }

        return bankAccount;
    }

    private void validateAmount(double amount) throws BankException {
        if (amount <= 0) {
            throw new BankException("Lỗi: Số tiền không hợp lệ!");
        }
    }
}
