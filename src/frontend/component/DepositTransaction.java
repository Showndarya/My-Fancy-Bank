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
import javax.xml.crypto.dsig.keyinfo.KeyValue;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * for handling money deposit in different currencies
 */
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
    ArrayList<MoneyType> moneyTypesForAccount = new ArrayList<>();

    public DepositTransaction(TransactionType transactionType, JPanel jPanel, ArrayList<UserAccount> userAccounts) {
        type = transactionType;

        makeTransaction.setMaximumSize(new Dimension(400, 300));
        makeTransaction.setMinimumSize(new Dimension(400, 300));
        makeTransaction.setPreferredSize(new Dimension(400, 300));
        add(makeTransaction);
        submitButton.setActionCommand("submit");
        if (currencySelect.getItemCount() == 0) {
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
        submitButton.addActionListener(e -> {
            try {
                double parsedAmount=Double.parseDouble(amount.getText());
                if(parsedAmount<0) {
                    JOptionPane.showMessageDialog(null,"Amount should be greater than 0.","Error",1);
                }
                Tuple item = (Tuple) accountSelect.getSelectedItem();
                Transaction transaction = new Transaction(
                        new Customer(FancyBank.getInstance().getUserId(), "name"),
                        Double.parseDouble(amount.getText()),
                        TransactionType.Deposit,
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

                try {
                    double amountWithFee = Double.parseDouble(amount.getText()) - 25;
                    accountController.changeBalance(
                            TransactionType.Deposit,
                            item.getValue(),
                            amountWithFee,
                            moneyTypesForAccount.get(currencySelect.getSelectedIndex()).getId()
                    );
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                AccountTransactionsListComponent.getInstance().reloadTable();
                jPanel.remove(0);
                jPanel.add(AccountTransactionsListComponent.getInstance());
                jPanel.validate();
                jPanel.repaint();
            }
            catch(Exception exp) {
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
            }

            @Override
            public void popupMenuCanceled(PopupMenuEvent e) {

            }
        });
    }

}
