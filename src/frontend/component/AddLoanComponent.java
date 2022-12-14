package frontend.component;

import business_logic_layer.interfaces.LoanTransactionService;
import business_logic_layer.interfaces.MoneyTypeService;
import business_logic_layer.impl.LoanTransactionServiceImpl;
import business_logic_layer.impl.MoneyTypeServiceImpl;
import models.transaction.MoneyType;
import models.transaction.Collateral;
import models.users.Customer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class AddLoanComponent extends JPanel{
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
    private JTextField textField3;
    private LoanTransactionService loanTransactionService;
    private MoneyTypeService moneyTypeService;

    public AddLoanComponent(JPanel jPanel, Customer customer) {
        moneyTypeService = new MoneyTypeServiceImpl();
        loanTransactionService = new LoanTransactionServiceImpl();
        add(addLoanPanel);
        saveButton.setActionCommand("save");
        cancelButton.setActionCommand("cancel");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String name = textField1.getText();
                String worth = textField2.getText();
                String type = textField3.getText();
                String loanAmount = textField4.getText();
                int moneyId = 0;
                try {
                    moneyId = moneyTypeService.getMoneyTypeIdByType(type);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                MoneyType moneyType = new MoneyType(moneyId, type);
                Collateral collateral = new Collateral(name, moneyType, Integer.parseInt(worth));
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
