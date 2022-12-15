package frontend;

import business_logic_layer.impl.AccountOperationServiceImpl;
import business_logic_layer.impl.AccountServiceImpl;
import business_logic_layer.impl.SecurityServiceImpl;
import business_logic_layer.interfaces.AccountOperationService;
import business_logic_layer.interfaces.AccountService;
import business_logic_layer.interfaces.SecurityService;
import dto.UserAccount;
import enums.AccountType;
import enums.TransactionType;
import utilities.BaseDao;
import utilities.FancyBank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class OpenSecurityAccountPanel extends JPanel{
    private static final int USD_TYPE = 1;
    private static final double MINIMUM_INITIAL_MONEY = 1000;
    private static final double MINIMUM_SAVINGS_MONEY = 5000;

    private static final int BUTTON_FONT_SIZE = 20;
    private static final int LABEL_FONT_SIZE = 20;
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    private JLabel openAccountPrompt;
    private JLabel inputPrompt;
    private JTextField initialMoneyTextField;
    private JButton openButton;

    private AccountOperationService accountOperationService;
    private AccountService accountService;
    private SecurityService securityService;

    public OpenSecurityAccountPanel(){
        reload();
    }

    public void reload(){
        // initial money label
        inputPrompt = new JLabel("Open Security Account ", JLabel.RIGHT);
        inputPrompt.setBounds(280, 150, 200, 30);
        inputPrompt.setFont(new Font("serif", Font.PLAIN, LABEL_FONT_SIZE));
        add(inputPrompt);
        // initial money label
        inputPrompt = new JLabel("Initial Money: ", JLabel.RIGHT);
        inputPrompt.setBounds(200, 200, 160, 30);
        inputPrompt.setFont(new Font("serif", Font.PLAIN, LABEL_FONT_SIZE));
        add(inputPrompt);
        // initial money text field
        initialMoneyTextField = new JTextField(20);
        initialMoneyTextField.setBounds(360, 200, 200, 30);
        initialMoneyTextField.setFont(new Font("serif", Font.PLAIN, LABEL_FONT_SIZE));
        add(initialMoneyTextField);
        // checking account button
        openButton = new JButton("Open");
        openButton.setBounds(350, 260, 100, 40);
        openButton.setFont(new Font("serif", Font.PLAIN, BUTTON_FONT_SIZE));
        openButton.addActionListener(this::clickOpenButton);
        add(openButton);
        // service
        accountOperationService = new AccountOperationServiceImpl();
        accountService = new AccountServiceImpl();
        securityService = new SecurityServiceImpl();
        // panel
        setLayout(null);
        setVisible(true);
    }

    private void clickOpenButton(ActionEvent e){
        double initialMoney = 0;
        try{
            initialMoney = Integer.parseInt(initialMoneyTextField.getText());
        } catch (NumberFormatException nfe){
            nfe.printStackTrace();
        }
        if(initialMoney < MINIMUM_INITIAL_MONEY){
            JOptionPane.showMessageDialog(this, "Initial money must be larger than 1000", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int accountId = accountService.getAccountId(FancyBank.getInstance().getUserId(), AccountType.Savings);
        ArrayList<UserAccount> userAccounts = null;
        try{
            userAccounts = accountOperationService.getAccountsByIdWithBalance(accountId);
        } catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        double savingsMoney = 0;
        boolean ok = false;
        for(UserAccount userAccount: userAccounts){
            if(userAccount.moneyType.getType().equals("usd")){
                savingsMoney = userAccount.amount;
                if(userAccount.amount >= MINIMUM_SAVINGS_MONEY){
                    ok = true;
                }
            }
        }
        if(ok){
            if(savingsMoney < initialMoney){
                JOptionPane.showMessageDialog(this, "No enough monet in savings account", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
            Connection connection = BaseDao.getConnection();
            try{
                accountOperationService.changeBalance(TransactionType.Withdraw, accountId, initialMoney, USD_TYPE);
                securityService.createNewSecurityAccount(connection, FancyBank.getInstance().getUserId(), initialMoney);
                securityService.modifyMoneyInSecurityAccount(connection, FancyBank.getInstance().getUserId(), initialMoney);
            } catch (SQLException sqle){
                try {
                    sqle.printStackTrace();
                    System.out.println("Error occur. Try to rollback");
                    connection.rollback();
                } catch (SQLException ex) {
                    System.out.println("Rollback failed");
                }
            }
            finally {
                BaseDao.close(connection, null, null);
            }
        }



        JOptionPane.showMessageDialog(this, "Please select a data", "Warning", JOptionPane.WARNING_MESSAGE);

    }



}
