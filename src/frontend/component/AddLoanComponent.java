package frontend.component;

import business_logic_layer.interfaces.LoanTransactionService;
import business_logic_layer.impl.LoanTransactionServiceImpl;
import controller_layer.AccountController;
import controller_layer.TransactionController;
import dto.UserAccount;
import models.account.Account;
import models.transaction.MoneyType;
import models.transaction.Collateral;
import models.users.Customer;
import utilities.FancyBank;
import utilities.Tuple;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class AddLoanComponent extends JPanel{
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
    private JLabel accountLable;
    private JComboBox accountBox;
    private LoanTransactionService loanTransactionService;
    TransactionController controller = new TransactionController();
    AccountController accountController = new AccountController();

    public AddLoanComponent(JPanel jPanel) {

        moneyTypes = new ArrayList<>();
        try {
            moneyTypes = controller.getAllmoneyTypes();
        } catch(SQLException e) {

        }

        accountArrayList = new ArrayList<>();
        try {
            accountArrayList = accountController.getAccountsByIdWithBalance(FancyBank.getInstance().getUserId());
            // accountArrayList = accountController.getAccountsByIdWithBalance(2);
        } catch(SQLException e) {

        }

        for (MoneyType type: moneyTypes) {
            moneyTypeBox.addItem(new Tuple(type.getType() + "("
                    + type.getSymbol() + ")",
                    type.getId()));
        }

        for (UserAccount userAccount: accountArrayList) {
            accountBox.addItem(new Tuple(userAccount.accountType.getDisplay(), userAccount.accountId));
        }

        loanTransactionService = new LoanTransactionServiceImpl();
        add(addLoanPanel);
        saveButton.setActionCommand("save");
        cancelButton.setActionCommand("cancel");


        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String name = textField1.getText();
                String worth = textField2.getText();
                String loanAmount = textField4.getText();
                MoneyType currMoneyType = new MoneyType(moneyTypes.get(moneyTypeBox.getSelectedIndex()).getId(),
                        moneyTypes.get(moneyTypeBox.getSelectedIndex()).getType(),
                        moneyTypes.get(moneyTypeBox.getSelectedIndex()).getSymbol());
                Collateral collateral = new Collateral(name,
                        currMoneyType,
                        Integer.parseInt(worth));
                loanTransactionService.addLoan(customer, collateral, Integer.parseInt(loanAmount));
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
