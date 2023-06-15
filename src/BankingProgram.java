import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class BankingProgram {
    private static Bank bank;

    public static void main(String[] args) {
        bank = new Bank();

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Banking Program");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);

        JLabel accountNumberLabel = new JLabel("Account Number:");
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(accountNumberLabel, constraints);

        JTextField accountNumberField = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 0;
        panel.add(accountNumberField, constraints);

        JLabel accountHolderLabel = new JLabel("Account Holder:");
        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(accountHolderLabel, constraints);

        JTextField accountHolderField = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 1;
        panel.add(accountHolderField, constraints);

        JButton createAccountButton = new JButton("Create Account");
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        panel.add(createAccountButton, constraints);

        JLabel depositLabel = new JLabel("Deposit Amount:");
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 1;
        panel.add(depositLabel, constraints);

        JTextField depositField = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 3;
        panel.add(depositField, constraints);

        JButton depositButton = new JButton("Deposit");
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 2;
        panel.add(depositButton, constraints);

        JLabel withdrawLabel = new JLabel("Withdraw Amount:");
        constraints.gridx = 0;
        constraints.gridy = 5;
        constraints.gridwidth = 1;
        panel.add(withdrawLabel, constraints);

        JTextField withdrawField = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 5;
        panel.add(withdrawField, constraints);

        JButton withdrawButton = new JButton("Withdraw");
        constraints.gridx = 0;
        constraints.gridy = 6;
        constraints.gridwidth = 2;
        panel.add(withdrawButton, constraints);

        JLabel balanceLabel = new JLabel("Account Balance:");
        constraints.gridx = 0;
        constraints.gridy = 7;
        constraints.gridwidth = 1;
        panel.add(balanceLabel, constraints);

        JTextField balanceField = new JTextField(20);
        balanceField.setEditable(false);
        constraints.gridx = 1;
        constraints.gridy = 7;
        panel.add(balanceField, constraints);

        JLabel recipientLabel = new JLabel("Recipient Account Number:");
        constraints.gridx = 0;
        constraints.gridy = 8;
        constraints.gridwidth = 1;
        panel.add(recipientLabel, constraints);

        JTextField recipientField = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 8;
        panel.add(recipientField, constraints);

        JLabel sendAmountLabel = new JLabel("Amount to Send:");
        constraints.gridx = 0;
        constraints.gridy = 9;
        constraints.gridwidth = 1;
        panel.add(sendAmountLabel, constraints);

        JTextField sendAmountField = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 9;
        panel.add(sendAmountField, constraints);

        JButton sendMoneyButton = new JButton("Send Money");
        constraints.gridx = 0;
        constraints.gridy = 10;
        constraints.gridwidth = 2;
        panel.add(sendMoneyButton, constraints);

        frame.add(panel);
        frame.pack();
        frame.setVisible(true);

        createAccountButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String accountNumber = accountNumberField.getText();
                String accountHolder = accountHolderField.getText();
                bank.createAccount(accountNumber, accountHolder);
                accountNumberField.setText("");
                accountHolderField.setText("");
            }
        });

        depositButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String accountNumber = accountNumberField.getText();
                double amount = Double.parseDouble(depositField.getText());
                BankAccount account = bank.getAccount(accountNumber);
                if (account != null) {
                    account.deposit(amount);
                    balanceField.setText(Double.toString(account.getBalance()));
                    depositField.setText("");
                } else {
                    JOptionPane.showMessageDialog(frame, "Account not found.");
                }
            }
        });

        withdrawButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String accountNumber = accountNumberField.getText();
                double amount = Double.parseDouble(withdrawField.getText());
                BankAccount account = bank.getAccount(accountNumber);
                if (account != null) {
                    account.withdraw(amount);
                    balanceField.setText(Double.toString(account.getBalance()));
                    withdrawField.setText("");
                } else {
                    JOptionPane.showMessageDialog(frame, "Account not found.");
                }
            }
        });

        sendMoneyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String accountNumber = accountNumberField.getText();
                double amount = Double.parseDouble(sendAmountField.getText());
                String recipientAccountNumber = recipientField.getText();

                BankAccount senderAccount = bank.getAccount(accountNumber);
                BankAccount recipientAccount = bank.getAccount(recipientAccountNumber);

                if (senderAccount != null && recipientAccount != null) {
                    senderAccount.sendMoney(recipientAccount, amount);
                    balanceField.setText(Double.toString(senderAccount.getBalance()));
                    sendAmountField.setText("");
                    recipientField.setText("");
                } else {
                    JOptionPane.showMessageDialog(frame, "Account(s) not found.");
                }
            }
        });
    }
}