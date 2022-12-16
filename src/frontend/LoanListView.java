package frontend;

import business_logic_layer.impl.AccountServiceImpl;
import business_logic_layer.impl.CustomerServiceImpl;
import business_logic_layer.interfaces.AccountService;
import business_logic_layer.interfaces.CustomerService;
import business_logic_layer.interfaces.LoanTransactionService;
import business_logic_layer.impl.LoanTransactionServiceImpl;
import controller_layer.AccountController;
import dto.UserAccount;
import enums.AccountType;
import enums.TransactionType;
import frontend.component.AddLoanComponent;
import frontend.component.LoanTransactionComponent;
import models.account.Account;
import models.transaction.LoanTransaction;
import models.users.Customer;
import utilities.FancyBank;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

// whole loan list for front end management

public class LoanListView extends JPanel{
    private JPanel loanListView;
    private JButton addButton;
    private JButton deleteButton;
    private JPanel contentPanel;
    private Customer customer;
    private CustomerService customerService;

    private LoanTransactionService loanTransactionService;
    private AccountController accountController = new AccountController();
    private AccountService accountService = new AccountServiceImpl();

    // create the list view for customer's loan
    public LoanListView(){
        customerService = new CustomerServiceImpl();
        this.customer = customerService.getCustomerByID(FancyBank.getInstance().getUserId());
        loanTransactionService = new LoanTransactionServiceImpl();
        add(loanListView);
        contentPanel.add(LoanTransactionComponent.getInstance(customer));
        addButton.setActionCommand("add");
        deleteButton.setActionCommand("delete");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                contentPanel.remove(0);
                contentPanel.add(new AddLoanComponent(contentPanel));
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
                int totalAmount = (int) (loanTransaction.getAmount() + loanTransaction.getInterest());
                try {
                    accountController.changeBalance(TransactionType.LoanDeduct, accountService.getAccountId(customer.getId(), AccountType.Savings),
                            totalAmount, loanTransaction.getCollateral().getMoneyType().getId());
                } catch (SQLException m) {
                    throw new RuntimeException(m);
                }
                loanTransactionService.deleteLoan(customer, loanTransaction);
                LoanTransactionComponent.getInstance(customer).reloadTable(customer);
                contentPanel.validate();
                contentPanel.repaint();
            }
        });
    }

    public static void main(String[] args) throws SQLException {
        JFrame frame = new JFrame("LoanListView");
        Customer customer = new Customer(1, "name");
        frame.setContentPane(new LoanListView());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
