package frontend.component;

import business_logic_layer.impl.AccountServiceImpl;
import business_logic_layer.impl.CustomerServiceImpl;
import business_logic_layer.interfaces.AccountService;
import business_logic_layer.interfaces.CustomerService;
import business_logic_layer.interfaces.LoanTransactionService;
import business_logic_layer.impl.LoanTransactionServiceImpl;
import controller_layer.AccountController;
import controller_layer.TransactionController;
import dto.UserAccount;
import enums.AccountType;
import enums.TransactionType;
import models.account.Account;
import models.account.SavingsAccount;
import models.transaction.MoneyType;
import models.transaction.Collateral;
import models.users.Customer;
import utilities.FancyBank;
import utilities.Tuple;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

// panel for adding loan in front end

public class AddLoanComponent extends JPanel {
    private Customer customer;
    ArrayList<MoneyType> moneyTypes;
    ArrayList<UserAccount> accountArrayList;
    private JPanel addLoanPanel;
    private JLabel collateralName;
    private JTextField textField1;
    private JTextField textField2;
    private JLabel collateralWorth;
    private JLabel moneyType;
    private JLabel loanAmount;
    private JTextField textField4;
    private JButton saveButton;
    private JButton cancelButton;
    private JComboBox moneyTypeBox;
    private LoanTransactionService loanTransactionService;
    private int accountID;
    private AccountService accountService = new AccountServiceImpl();
    TransactionController controller = new TransactionController();
    AccountController accountController = new AccountController();
    CustomerService customerService = new CustomerServiceImpl();

    public AddLoanComponent(JPanel jPanel) {
        customer = customerService.getCustomerByID(FancyBank.getInstance().getUserId());

        moneyTypes = new ArrayList<>();
        try {
            moneyTypes = controller.getAllmoneyTypes();
        } catch (SQLException e) {

        }

        for (MoneyType type : moneyTypes) {
            moneyTypeBox.addItem(new Tuple(type.getType() + "("
                    + type.getSymbol() + ")",
                    type.getId()));
        }


        loanTransactionService = new LoanTransactionServiceImpl();
        add(addLoanPanel);
        saveButton.setActionCommand("save");
        cancelButton.setActionCommand("cancel");


        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (accountService.hasAccount(customer.getId(), AccountType.Savings) == false) return;
                accountID = accountService.getAccountId(customer.getId(), AccountType.Savings);
                String name = textField1.getText();
                String worth = textField2.getText();
                String loanAmount = textField4.getText();
                if (name.length() == 0) {
                    JOptionPane.showMessageDialog(addLoanPanel, "There must be a collateral name", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                try {
                    int amount = Integer.parseInt(loanAmount);
                    int worthInt = Integer.parseInt(worth);
                }catch (Exception e) {
                    JOptionPane.showMessageDialog(addLoanPanel, "Amount and worth must be int", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                MoneyType currMoneyType = new MoneyType(moneyTypes.get(moneyTypeBox.getSelectedIndex()).getId(),
                        moneyTypes.get(moneyTypeBox.getSelectedIndex()).getType(),
                        moneyTypes.get(moneyTypeBox.getSelectedIndex()).getSymbol());
                Collateral collateral = new Collateral(name,
                        currMoneyType,
                        Integer.parseInt(worth));
                loanTransactionService.addLoan(customer, collateral, Integer.parseInt(loanAmount));
                try {
                    accountController.changeBalance(TransactionType.LoanAdd, accountID,
                            Integer.parseInt(loanAmount), currMoneyType.getId());
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                LoanTransactionComponent.getInstance(customer).reloadTable(customer);
                jPanel.remove(0);
                jPanel.add(LoanTransactionComponent.getInstance(customer));
                jPanel.validate();
                jPanel.repaint();

            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                jPanel.remove(0);
                jPanel.add(new LoanTransactionComponent(customer));
                jPanel.validate();
                jPanel.repaint();
            }
        });
        setVisible(true);

    }

}
