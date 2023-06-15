import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

class Bank {
    private Map<String, BankAccount> accounts;

    public Bank() {
        this.accounts = new HashMap<>();
    }

    public void createAccount(String accountNumber, String accountHolder) {
        BankAccount account = new BankAccount(accountNumber, accountHolder);
        accounts.put(accountNumber, account);
        System.out.println("Account created successfully.");
    }

    public BankAccount getAccount(String accountNumber) {
        return accounts.get(accountNumber);
    }
}