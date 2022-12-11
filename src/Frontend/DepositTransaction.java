package Frontend;

import javax.swing.*;

public class DepositTransaction {
    private JPanel depositPanel;
    private JComboBox accountSelect;
    private JTextField amount;
    private JComboBox currencySelect;
    private JButton backButton;
    private JLabel depositLabel;
    private JPanel accountDetailsPanel;
    private JPanel backPanel;

    public static void open() {
        JframeSingleton.getInstance().removePanel();
        new DepositTransaction().accountDetailsPanel.setVisible(false);
        JframeSingleton.getInstance().addPanel(new DepositTransaction().depositPanel);
    }
}
