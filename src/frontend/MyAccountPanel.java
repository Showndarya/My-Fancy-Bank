package frontend;

import business_logic_layer.interfaces.AccountService;
import business_logic_layer.interfaces.SecurityService;
import business_logic_layer.impl.AccountServiceImpl;
import business_logic_layer.impl.SecurityServiceImpl;
import enums.AccountType;
import utilities.FancyBank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Hashtable;

public class MyAccountPanel extends JPanel{
    private static final int LABEL_FONT_SIZE = 20;
    private static final int BUTTON_FONT_SIZE = 20;
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    private JLabel accountLabel;
    private Hashtable<AccountType, JButton> accountsButton;
    private Hashtable<AccountType, JButton> addAccountsButton;
    private JButton returnButton;
    private JPanel currentAccountPanel;

    // service
    private AccountService accountService;
    private SecurityService securityService;

    public MyAccountPanel(){
        // account label
        accountLabel = new JLabel("My accounts", JLabel.RIGHT);
        accountLabel.setBounds(20, 0, 130, 50);
        accountLabel.setFont(new Font("serif", Font.PLAIN, LABEL_FONT_SIZE));
        add(accountLabel);
        // Buttons
        accountsButton = new Hashtable<>();
        addAccountsButton = new Hashtable<>();
        // checking account button
        JButton checkingAccountButton = new JButton("checking");
        checkingAccountButton.setBounds(20, 50, 120, 40);
        checkingAccountButton.setFont(new Font("serif", Font.PLAIN, BUTTON_FONT_SIZE));
        checkingAccountButton.addActionListener(this::clickCheckingAccountButton);
        accountsButton.put(AccountType.Checking, checkingAccountButton);
        add(checkingAccountButton);
        // add checking account button
        JButton addCheckingAccountButton = new JButton("add");
        addCheckingAccountButton.setBounds(150, 50, 70, 40);
        addCheckingAccountButton.setFont(new Font("serif", Font.PLAIN, BUTTON_FONT_SIZE));
        addCheckingAccountButton.addActionListener(this::clickAddCheckingAccountButton);
        addAccountsButton.put(AccountType.Checking, addCheckingAccountButton);
        add(addCheckingAccountButton);
        // savings account button
        JButton savingsAccountButton = new JButton("savings");
        savingsAccountButton.setBounds(20, 100, 120, 40);
        savingsAccountButton.setFont(new Font("serif", Font.PLAIN, BUTTON_FONT_SIZE));
        savingsAccountButton.addActionListener(this::clickSavingsAccountButton);
        accountsButton.put(AccountType.Savings, savingsAccountButton);
        add(savingsAccountButton);
        // add savings account button
        JButton addSavingsAccountButton = new JButton("add");
        addSavingsAccountButton.setBounds(150, 100, 70, 40);
        addSavingsAccountButton.setFont(new Font("serif", Font.PLAIN, BUTTON_FONT_SIZE));
        addSavingsAccountButton.addActionListener(this::clickAddSavingsAccountButton);
        addAccountsButton.put(AccountType.Savings, addSavingsAccountButton);
        add(addSavingsAccountButton);
        // security account button
        JButton securityAccountButton = new JButton("security");
        securityAccountButton.setBounds(20, 150, 120, 40);
        securityAccountButton.setFont(new Font("serif", Font.PLAIN, BUTTON_FONT_SIZE));
        securityAccountButton.addActionListener(this::clickSecurityAccountButton);
        accountsButton.put(AccountType.Security, securityAccountButton);
//        add(securityAccountButton);
        // add security account button
        JButton addSecurityAccountButton = new JButton("add");
        addSecurityAccountButton.setBounds(150, 150, 70, 40);
        addSecurityAccountButton.setFont(new Font("serif", Font.PLAIN, BUTTON_FONT_SIZE));
        addSecurityAccountButton.addActionListener(this::clickAddSecurityAccountButton);
        addAccountsButton.put(AccountType.Security, addSecurityAccountButton);
//        add(addSecurityAccountButton);
        // return button
        returnButton = new JButton("return");
        returnButton.setBounds(20, 400, 160, 40);
        returnButton.setFont(new Font("serif", Font.PLAIN, BUTTON_FONT_SIZE));
        returnButton.addActionListener(this::clickReturnButton);
//        add(returnButton);
        // Initialize
        initialize();
        // Panel
        setSize(WIDTH, HEIGHT);
        setLayout(null);
    }

    private void clickCheckingAccountButton(ActionEvent e){
        setCurrentPanel(new CheckingAccountInfoPanel());
    }

    private void clickSavingsAccountButton(ActionEvent e){
        setCurrentPanel(new SavingsAccountInfoPanel());
    }

    private void clickSecurityAccountButton(ActionEvent e){
        setCurrentPanel(new SecurityAccountInfoPanel());
    }

    private void clickAddCheckingAccountButton(ActionEvent e){
        if(accountService.createAccount(FancyBank.getInstance().getUserId(), AccountType.Checking)){
            accountsButton.get(AccountType.Checking).setEnabled(true);
            addAccountsButton.get(AccountType.Checking).setEnabled(false);
        }
    }

    private void clickAddSavingsAccountButton(ActionEvent e){
        if(accountService.createAccount(FancyBank.getInstance().getUserId(), AccountType.Savings)){
            accountsButton.get(AccountType.Savings).setEnabled(true);
            addAccountsButton.get(AccountType.Savings).setEnabled(false);
        }
    }

    private void clickAddSecurityAccountButton(ActionEvent e){


    }

    private void clickReturnButton(ActionEvent e){

    }

    private void setCurrentPanel(JPanel panel){
        if(currentAccountPanel != null){
            remove(currentAccountPanel);
        }
        add(panel);
        currentAccountPanel = panel;
        repaint();
        setVisible(true);
    }

    private void initialize(){
        accountService = new AccountServiceImpl();
        securityService = new SecurityServiceImpl();
        // Find 3 accounts
        if(accountService.hasAccount(FancyBank.getInstance().getUserId(), AccountType.Checking)){
            addAccountsButton.get(AccountType.Checking).setEnabled(false);
        }
        else{
            accountsButton.get(AccountType.Checking).setEnabled(false);
        }
        if(accountService.hasAccount(FancyBank.getInstance().getUserId(), AccountType.Savings)){
            addAccountsButton.get(AccountType.Savings).setEnabled(false);
        }
        else{
            accountsButton.get(AccountType.Savings).setEnabled(false);
        }

//        for(AccountType type: AccountType.values()){
//            if(accountService.hasAccount(FancyBank.getInstance().getUserId(), type)){
//                addAccountsButton.get(type).setEnabled(false);
//            }
//            else{
//                accountsButton.get(type).setEnabled(false);
//            }
//        }
    }


}
