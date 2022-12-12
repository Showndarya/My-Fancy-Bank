package Frontend;

import Enums.TransactionType;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class ManageAccountTransactions {
    private JPanel panel1;
    private JButton Withdraw;
    private JButton Deposit;

    public ManageAccountTransactions() {
        Deposit.addActionListener(e -> {
            try {
                DepositTransaction.open(TransactionType.Deposit);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        Withdraw.addActionListener(e -> {
            try {
                DepositTransaction.open(TransactionType.Withdraw);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    public static void open() {
        //JframeSingleton.getInstance().removePanel();
        JframeSingleton.getInstance().addPanel(new ManageAccountTransactions().panel1);
    }

    public static void main(String[] args) {
        open();
    }
}
