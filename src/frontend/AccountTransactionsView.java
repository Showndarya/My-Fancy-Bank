package frontend;

import controller_layer.AccountController;
import enums.TransactionType;
import frontend.component.AccountTransactionsListComponent;
import frontend.component.DepositTransaction;
import frontend.component.WithdrawTransaction;
import dto.UserAccount;
import utilities.FancyBank;
import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class AccountTransactionsView extends JPanel {
    private JPanel accountTransactionsView;
    private JButton deposit;
    private JButton withdraw;
    private JPanel list;
    AccountController accountController = new AccountController();

    public static void main(String[] args) throws SQLException {
        JFrame frame = new JFrame("Account Transactions");
        frame.setContentPane(new AccountTransactionsView());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public AccountTransactionsView() {
        ArrayList<UserAccount> userAccounts = new ArrayList<>();
        add(accountTransactionsView);

        list.add(AccountTransactionsListComponent.getInstance());
        try {
            userAccounts = accountController.getAccountsByIdWithBalance(FancyBank.getInstance().getUserId());
        } catch (SQLException e) {

        }

        deposit.setActionCommand("deposit");
        withdraw.setActionCommand("withdraw");
        ArrayList<UserAccount> finalUserAccounts = userAccounts;
        deposit.addActionListener(actionEvent -> {
            list.remove(0);
            list.removeAll();
            list.add(new DepositTransaction(TransactionType.Deposit, list, finalUserAccounts));
            AccountTransactionsListComponent.getInstance().reloadTable();
            list.validate();
            list.repaint();
        });
        withdraw.addActionListener(actionEvent -> {
            list.remove(0);
            list.removeAll();
            list.add(new WithdrawTransaction(TransactionType.Withdraw, list, finalUserAccounts));
            AccountTransactionsListComponent.getInstance().reloadTable();
            list.validate();
            list.repaint();
        });

        setVisible(true);
    }

}
