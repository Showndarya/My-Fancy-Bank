package frontend;

import business_logic_layer.interfaces.LoanTransactionService;
import business_logic_layer.impl.LoanTransactionServiceImpl;
import frontend.component.AddLoanComponent;
import frontend.component.LoanTransactionComponent;
import models.transaction.LoanTransaction;
import models.users.Customer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoanListView extends JPanel{
    private JPanel loanListView;
    private JPanel topPanel;
    private JButton addButton;
    private JButton deleteButton;
    private JPanel contentPanel;
    private Customer customer;

    private LoanTransactionService loanTransactionService;

    // create the list view for customer's loan
    public LoanListView(Customer customer) {
        this.customer = customer;
        loanTransactionService = new LoanTransactionServiceImpl();
        add(loanListView);
        contentPanel.add(LoanTransactionComponent.getInstance(customer));
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

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoanTransaction loanTransaction = LoanTransactionComponent.getInstance(customer).getLoanTransaction();
                if (loanTransaction == null) {
                    JOptionPane.showMessageDialog(contentPanel, "Please select a data", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                loanTransactionService.deleteLoan(customer, loanTransaction);
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
