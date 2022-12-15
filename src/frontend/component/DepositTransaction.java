package frontend.component;

import controller_layer.AccountController;
import enums.TransactionType;
import models.transaction.MoneyType;
import models.transaction.Transaction;
import models.users.Customer;
import utilities.FancyBank;
import utilities.Tuple;
import dto.UserAccount;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class DepositTransaction extends JPanel {
    private JPanel makeTransaction;
    private JComboBox accountSelect;
    private JComboBox currencySelect;
    private JLabel depositLabel;
    private JButton submitButton;
    private JTextField amount;
    private JButton cancelButton;

    private TransactionType type;
    AccountController accountController = new AccountController();

    public DepositTransaction(TransactionType transactionType, JPanel jPanel, ArrayList<MoneyType> moneyTypes, ArrayList<UserAccount> userAccounts) {
        type = transactionType;

        makeTransaction.setMaximumSize(new Dimension(400, 300));
        makeTransaction.setMinimumSize(new Dimension(400, 300));
        makeTransaction.setPreferredSize(new Dimension(400, 300));
        add(makeTransaction);
        submitButton.setActionCommand("submit");
        if(currencySelect.getItemCount() == 0) {
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
        submitButton.addActionListener(e -> {

            Transaction transaction = new Transaction(
                    new Customer(2, "name"),
                    Double.parseDouble(amount.getText()),
                    TransactionType.Deposit,
                    userAccounts.get(accountSelect.getSelectedIndex()).accountId,
                    moneyTypes.get(currencySelect.getSelectedIndex()).getId()
            );

            accountController.addTransaction(2, transaction);
            try {
                double amountWithFee = Double.parseDouble(amount.getText())-25;
                accountController.changeBalance(
                        TransactionType.Deposit,
                        userAccounts.get(accountSelect.getSelectedIndex()).accountId,
                        amountWithFee,
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
        });
        setVisible(true);
    }
}
