package frontend.component;

import controller_layer.AccountController;
import dto.UserAccount;
import enums.TransactionType;
import models.transaction.MoneyType;
import models.transaction.Transaction;
import models.users.Customer;
import utilities.FancyBank;
import utilities.Tuple;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class AccountTransfer extends JPanel {
    private JPanel accountTransferPanel;
    private JComboBox accountSelect;
    private JTextField accountNumber;
    private JTextField swiftCode;
    private JTextField transferAmount;
    private JButton transferButton;
    private JButton cancelButton;
    private JLabel accountBalanceValue;
    private JComboBox currencySelect;
    AccountController accountController = new AccountController();

    public AccountTransfer(JPanel jPanel, ArrayList<MoneyType> moneyTypes, ArrayList<UserAccount> userAccounts) {
        accountTransferPanel.setMaximumSize(new Dimension(600, 600));
        accountTransferPanel.setMinimumSize(new Dimension(600, 600));
        accountTransferPanel.setPreferredSize(new Dimension(600, 600));

        add(accountTransferPanel);
        transferButton.setActionCommand("transfer");
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
        if(userAccounts.size()>0) setBalance(moneyTypes, userAccounts);

        transferButton.addActionListener(e -> {
            Transaction transaction = new Transaction(
                    new Customer(FancyBank.getInstance().getUserId(),"name"),
                    Double.parseDouble(transferAmount.getText()),
                    TransactionType.Withdraw,
                    userAccounts.get(accountSelect.getSelectedIndex()).accountId,
                    moneyTypes.get(currencySelect.getSelectedIndex()).getId()
            );

            accountController.addTransaction(FancyBank.getInstance().getUserId(), transaction);
            try {
                double amountWithFee = Double.parseDouble(transferAmount.getText())+25;
                accountController.changeBalance(
                        TransactionType.Withdraw,
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
            transferButton.setEnabled(false);
        }
        else {
            accountBalanceValue.setText(String.valueOf(value));
            transferButton.setEnabled(true);
        }
    }
}
