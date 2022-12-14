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

public class DepositTransaction extends JPanel {
    private JPanel makeTransaction;
    private JComboBox accountSelect;
    private JComboBox currencySelect;
    private JLabel depositLabel;
    private JButton submitButton;
    private JTextField amount;

    private TransactionType type;
    AccountController accountController = new AccountController();

    public DepositTransaction(TransactionType transactionType, JPanel jPanel, ArrayList<MoneyType> moneyTypes, ArrayList<UserAccount> userAccounts) {
        type = transactionType;

        add(makeTransaction);
        submitButton.setActionCommand("submit");
        if(currencySelect.getItemCount() == 0) {
            for(UserAccount userAccount: userAccounts) {
                accountSelect.addItem(
                        new Tuple(userAccount.accountType.getDisplay(), userAccount.accountId)
                );
                currencySelect.addItem(
                        new Tuple(userAccount.moneyType.getType()+"("+userAccount.moneyType.getSymbol()+")", userAccount.moneyType.getId())
                );
            }
        }
        submitButton.addActionListener(e -> {

            Transaction transaction = new Transaction(
                    new Customer(2,"name"),
                    Double.parseDouble(amount.getText()),
                    TransactionType.Deposit,
                    userAccounts.get(accountSelect.getSelectedIndex()).accountId,
                    moneyTypes.get(currencySelect.getSelectedIndex()).getId()
            );

            accountController.addTransaction(2, transaction);
            try {
                accountController.changeBalance(
                        TransactionType.Deposit,
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

        setVisible(true);
    }
}
