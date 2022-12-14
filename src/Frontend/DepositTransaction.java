package Frontend;

import ControllerLayer.AccountController;
import Enums.TransactionType;
import Frontend.component.AccountTransactionsListComponent;
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
            for(MoneyType moneyType: moneyTypes)
                currencySelect.addItem(
                        new Tuple(moneyType.getType()+"("+moneyType.getSymbol()+")", moneyType.getId())
                );

            for(UserAccount userAccount: userAccounts)
                accountSelect.addItem(
                        new Tuple(userAccount.userId+" "+userAccount.accountId, userAccount.accountId)
                );
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
