package frontend;

import controller_layer.AccountController;
import controller_layer.TransactionController;
import enums.TransactionType;
import frontend.component.AccountTransactionsListComponent;
import frontend.component.DepositTransaction;
import frontend.component.WithdrawTransaction;
import models.transaction.MoneyType;
import dto.UserAccount;
import utilities.Tuple;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class AccountTransactionsView extends JPanel {
    private JPanel accountTransactionsView;
    private JButton deposit;
    private JButton withdraw;
    private JPanel list;
    private JComboBox filter;
    TransactionController controller = new TransactionController();
    AccountController accountController = new AccountController();

    public static void main(String[] args) throws SQLException {
        JFrame frame = new JFrame("Account Transactions");
        frame.setContentPane(new AccountTransactionsView());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public AccountTransactionsView() {
        ArrayList<MoneyType> moneyTypes = new ArrayList<>();
        ArrayList<UserAccount> userAccounts = new ArrayList<>();
        add(accountTransactionsView);

        list.add(AccountTransactionsListComponent.getInstance());
        try {
            moneyTypes = controller.getAllmoneyTypes();
            userAccounts = accountController.getAccountsByIdWithBalance(2);
        } catch(SQLException e) {

        }

        deposit.setActionCommand("deposit");
        withdraw.setActionCommand("withdraw");
        ArrayList<MoneyType> finalMoneyTypes = moneyTypes;
        ArrayList<UserAccount> finalUserAccounts = userAccounts;
        deposit.addActionListener(actionEvent -> {
            list.remove(0);
            list.removeAll();
            list.add(new DepositTransaction(TransactionType.Deposit, list, finalMoneyTypes, finalUserAccounts));
            AccountTransactionsListComponent.getInstance().reloadTable();
            list.validate();
            list.repaint();
        });
        withdraw.addActionListener(actionEvent -> {
            list.remove(0);
            list.removeAll();
            list.add(new WithdrawTransaction(TransactionType.Withdraw, list, finalMoneyTypes, finalUserAccounts));
            AccountTransactionsListComponent.getInstance().reloadTable();
            list.validate();
            list.repaint();
        });

        setVisible(true);
    }
}
