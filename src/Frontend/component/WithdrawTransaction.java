package Frontend.component;

import ControllerLayer.AccountController;
import Enums.TransactionType;
import Models.MoneyType;
import Models.Transaction.Transaction;
import Models.Users.Customer;
import Utilities.Tuple;
import dto.UserAccount;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class WithdrawTransaction extends JPanel{
    private JLabel withdrawLabel;
    private JComboBox accountSelect;
    private JComboBox currencySelect;
    private JButton submitButton;
    private JPanel withdrawTransactionPanel;
    private JTextField amount;
    private JLabel accountBalance;
    private JLabel accountBalanceValue;
    private JButton cancelButton;
    private TransactionType type;
    AccountController accountController = new AccountController();

    public WithdrawTransaction(TransactionType transactionType, JPanel jPanel, ArrayList<MoneyType> moneyTypes, ArrayList<UserAccount> userAccounts) {
        type = transactionType;

        add(withdrawTransactionPanel);
        submitButton.setActionCommand("submit");
        if(accountSelect.getItemCount() == 0) {
            for(UserAccount userAccount: userAccounts) {
                accountSelect.addItem(
                        new Tuple(userAccount.accountType.getDisplay(), userAccount.accountId)
                );
            }

        }
        currencySelect.removeAllItems();
        for(UserAccount userAccount: userAccounts)
            if(userAccount.accountId==userAccounts.get(accountSelect.getSelectedIndex()).accountId)
                currencySelect.addItem(
                        new Tuple(userAccount.moneyType.getType()+"("+userAccount.moneyType.getSymbol()+")", userAccount.moneyType.getId())
                );
        setBalance(moneyTypes, userAccounts);
        submitButton.addActionListener(e -> {
            Transaction transaction = new Transaction(
                    new Customer(2,"name"),
                    Double.parseDouble(amount.getText()),
                    TransactionType.Withdraw,
                    userAccounts.get(accountSelect.getSelectedIndex()).accountId,
                    moneyTypes.get(currencySelect.getSelectedIndex()).getId()
            );

            accountController.addTransaction(2, transaction);
            try {
                accountController.changeBalance(
                        TransactionType.Withdraw,
                        userAccounts.get(accountSelect.getSelectedIndex()).accountId,
                        Double.parseDouble(amount.getText()),
                        moneyTypes.get(currencySelect.getSelectedIndex()).getId()
                );
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

            AccountTransactionsListComponent.getInstance().reloadTable();
            jPanel.remove(0);
            jPanel.add(AccountTransactionsListComponent.getInstance());
            jPanel.validate();
            jPanel.repaint();
        });

        cancelButton.addActionListener(e -> {
            AccountTransactionsListComponent.getInstance().reloadTable();
            jPanel.remove(0);
            jPanel.add(AccountTransactionsListComponent.getInstance());
            jPanel.validate();
            jPanel.repaint();
        });

        setVisible(true);
        accountSelect.addActionListener(e -> {
            currencySelect.removeAllItems();
            ArrayList<MoneyType> moneyTypesForAccount = new ArrayList<>();
            for(UserAccount userAccount: userAccounts)
                if(userAccount.accountId==userAccounts.get(accountSelect.getSelectedIndex()).accountId) {
                    currencySelect.addItem(
                            new Tuple(userAccount.moneyType.getType() + "(" + userAccount.moneyType.getSymbol() + ")", userAccount.moneyType.getId())
                    );
                    moneyTypesForAccount.add(userAccount.moneyType);
                }

            setBalance(moneyTypesForAccount, userAccounts);
        });
    }

    private void setBalance(ArrayList<MoneyType> moneyTypes, ArrayList<UserAccount> userAccounts) {
        double value = 0;
        try {
            value = accountController.getBalance(userAccounts.get(accountSelect.getSelectedIndex()).accountId,moneyTypes.get(currencySelect.getSelectedIndex()).getId());
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        if(value<0) {
            accountBalanceValue.setText("Account-Currency combination does not exist");
            submitButton.setEnabled(false);
        }
        else {
            accountBalanceValue.setText(String.valueOf(value));
            submitButton.setEnabled(true);
        }
    }
}
