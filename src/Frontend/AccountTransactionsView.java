package Frontend;

import Enums.TransactionType;
import Frontend.component.AccountTransactionsListComponent;
import javax.swing.*;
import java.sql.SQLException;

public class AccountTransactionsView extends JPanel {
    private JPanel accountTransactionsView;
    private JButton deposit;
    private JButton withdraw;
    private JPanel list;


    public static void main(String[] args) throws SQLException {
        JFrame frame = new JFrame("Account Transactions");
        frame.setContentPane(new AccountTransactionsView());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public AccountTransactionsView() {
        add(accountTransactionsView);

        list.add(AccountTransactionsListComponent.getInstance());
        deposit.setActionCommand("deposit");
        withdraw.setActionCommand("withdraw");
        deposit.addActionListener(actionEvent -> {
            list.remove(0);
            list.add(new MakeTransaction(TransactionType.Deposit, list));
            AccountTransactionsListComponent.getInstance().reloadTable();
            list.validate();
            list.repaint();
        });

        withdraw.addActionListener(actionEvent -> {
            list.remove(0);
            list.add(new MakeTransaction(TransactionType.Withdraw, list));
            AccountTransactionsListComponent.getInstance().reloadTable();
            list.validate();
            list.repaint();
        });
    }
}
