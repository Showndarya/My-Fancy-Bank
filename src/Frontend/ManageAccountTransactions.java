package Frontend;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManageAccountTransactions {
    private JPanel panel1;
    private JButton Withdraw;
    private JButton Deposit;

    public ManageAccountTransactions() {
        Deposit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DepositTransaction.open();
            }
        });
    }

    public static void main(String[] args) {
        //JframeSingleton.getInstance().removePanel();
        JframeSingleton.getInstance().addPanel(new ManageAccountTransactions().panel1);
    }
}
