package Frontend;

import Frontend.component.AddLoanComponent;
import Frontend.component.AddStockComponent;
import Frontend.component.LoanTransactionComponent;
import Models.Users.Customer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoanListView extends JPanel{
    private JPanel loanListView;
    private JPanel topPanel;
    private JButton addButton;
    private JButton deleteButton;
    private JPanel contentPanel;

    // create the list view for customer's loan
    public LoanListView(Customer customer) {
        add(loanListView);
        contentPanel.add(new LoanTransactionComponent(customer));
        addButton.setActionCommand("add");
        deleteButton.setActionCommand("delete");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                contentPanel.remove(0);
                contentPanel.add(new AddLoanComponent(contentPanel, customer));
                LoanTransactionComponent.getInstance(customer).reloadTable(customer);
                contentPanel.validate();
                contentPanel.repaint();
            }
        });
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
