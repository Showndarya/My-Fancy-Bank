package Frontend;

import Frontend.component.LoanTransactionComponent;
import Models.Users.Customer;

import javax.swing.*;

public class LoanListView extends JPanel{
    private JPanel loanListView;
    private JPanel topPanel;
    private JButton addButton;
    private JButton deleteButton;
    private JPanel contentPanel;

    public LoanListView(Customer customer) {
        add(loanListView);
        contentPanel.add(new LoanTransactionComponent(customer));
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("LoanListView");
        Customer customer = new Customer(1, "name");
        frame.setContentPane(new LoanListView(customer));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
