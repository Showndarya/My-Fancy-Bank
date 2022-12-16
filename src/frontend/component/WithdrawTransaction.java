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
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * for handling money withdraw in different currencies
 */
public class WithdrawTransaction extends JPanel {
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
    ArrayList<MoneyType> moneyTypesForAccount = new ArrayList<>();

    public WithdrawTransaction(TransactionType transactionType, JPanel jPanel, ArrayList<UserAccount> userAccounts) {
        type = transactionType;

        withdrawTransactionPanel.setMaximumSize(new Dimension(400, 300));
        withdrawTransactionPanel.setMinimumSize(new Dimension(400, 300));
        withdrawTransactionPanel.setPreferredSize(new Dimension(400, 300));

        add(withdrawTransactionPanel);
        submitButton.setActionCommand("submit");
        if (accountSelect.getItemCount() == 0) {
            Map<Object, List<UserAccount>> groupedAccounts = userAccounts.stream().collect(Collectors.groupingBy(x -> x.accountId));
            for (Object key : groupedAccounts.keySet())
                accountSelect.addItem(
                        new Tuple(groupedAccounts.get(key).get(0).accountType.getDisplay(), (int) key)
                );
        }
        currencySelect.removeAllItems();
        for (UserAccount userAccount : userAccounts) {
            Tuple item = (Tuple) accountSelect.getSelectedItem();
            if (userAccount.accountId == item.getValue()) {
                currencySelect.addItem(
                        new Tuple(userAccount.moneyType.getType() + "(" + userAccount.moneyType.getSymbol() + ")", userAccount.moneyType.getId())
                );
                moneyTypesForAccount.add(userAccount.moneyType);
            }
        }

        if (userAccounts.size() > 0) {
            Tuple item = (Tuple) accountSelect.getSelectedItem();
            setBalance(moneyTypesForAccount, item.getValue());
        }
        submitButton.addActionListener(e -> {
            try{
                double parsedAmount=Double.parseDouble(amount.getText());
                if(parsedAmount<0) {
                    JOptionPane.showMessageDialog(null,"Amount should be greater than 0.","Error",1);
                }
                Tuple item = (Tuple) accountSelect.getSelectedItem();

                try {
                    double amountWithFee = Double.parseDouble(amount.getText()) + 25;
                    Boolean isSuccess = accountController.changeBalance(
                            TransactionType.Withdraw,
                            item.getValue(),
                            amountWithFee,
                            moneyTypesForAccount.get(currencySelect.getSelectedIndex()).getId()
                    );

                    if(!isSuccess)
                        JOptionPane.showMessageDialog(null,"Transaction failed. Insufficient balance.","Error",1);
                    else {
                        Transaction transaction = new Transaction(
                                new Customer(FancyBank.getInstance().getUserId(), "name"),
                                Double.parseDouble(amount.getText()),
                                TransactionType.Withdraw,
                                item.getValue(),
                                moneyTypesForAccount.get(currencySelect.getSelectedIndex()).getId()
                        );

                        accountController.addTransaction(FancyBank.getInstance().getUserId(), transaction);

                        transaction = new Transaction(
                                new Customer(FancyBank.getInstance().getUserId(), "name"),
                                25.00,
                                TransactionType.TransactionFee,
                                item.getValue(),
                                moneyTypesForAccount.get(currencySelect.getSelectedIndex()).getId()
                        );

                        accountController.addTransaction(FancyBank.getInstance().getUserId(), transaction);


                        AccountTransactionsListComponent.getInstance().reloadTable();
                        jPanel.remove(0);
                        jPanel.add(AccountTransactionsListComponent.getInstance());
                        jPanel.validate();
                        jPanel.repaint();
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
            catch (Exception exp){
                JOptionPane.showMessageDialog(null,"Enter valid amount","Error",1);
            }
        });

        cancelButton.addActionListener(e -> {
            AccountTransactionsListComponent.getInstance().reloadTable();
            jPanel.remove(0);
            jPanel.add(AccountTransactionsListComponent.getInstance());
            jPanel.validate();
            jPanel.repaint();
        });

        setVisible(true);

        accountSelect.addPopupMenuListener(new PopupMenuListener() {
            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {

            }

            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
                currencySelect.removeAllItems();
                moneyTypesForAccount = new ArrayList<>();
                Tuple item = (Tuple) accountSelect.getSelectedItem();
                for (UserAccount userAccount : userAccounts)
                    if (userAccount.accountId == item.getValue()) {
                        currencySelect.addItem(
                                new Tuple(userAccount.moneyType.getType() + "(" + userAccount.moneyType.getSymbol() + ")", userAccount.moneyType.getId())
                        );
                        moneyTypesForAccount.add(userAccount.moneyType);
                    }
                setBalance(moneyTypesForAccount, item.getValue());
            }

            @Override
            public void popupMenuCanceled(PopupMenuEvent e) {

            }
        });

        currencySelect.addPopupMenuListener(new PopupMenuListener() {
            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {

            }

            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
                Tuple account = (Tuple) accountSelect.getSelectedItem();
                setBalance(moneyTypesForAccount, account.getValue());
            }

            @Override
            public void popupMenuCanceled(PopupMenuEvent e) {

            }
        });


    }

    private void setBalance(ArrayList<MoneyType> moneyTypes, int accountId) {
        double value = 0;
        try {
            value = accountController.getBalance(accountId, moneyTypes.get(currencySelect.getSelectedIndex()).getId());
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        if (value < 0) {
            accountBalanceValue.setText("Account-Currency combination does not exist");
            submitButton.setEnabled(false);
        } else {
            accountBalanceValue.setText(String.valueOf(value));
            submitButton.setEnabled(true);
        }
    }

}
