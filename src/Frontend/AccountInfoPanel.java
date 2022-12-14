package Frontend;

import BusinessLogicLayer.AccountOperationService;
import BusinessLogicLayer.AccountService;
import BusinessLogicLayer.impl.AccountOperationServiceImpl;
import BusinessLogicLayer.impl.AccountServiceImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Hashtable;

public abstract class AccountInfoPanel extends JPanel{
    private static final int LABEL_FONT_SIZE = 20;
    private static final int BUTTON_FONT_SIZE = 20;
    private static final int MONEY_TYPE_NUMBER = 3;
    private static final int WIDTH = 600;
    private static final int HEIGHT = 600;
    protected static final int ACCOUNT_NUMBER_LENGTH = 8;
    private JLabel accountTypePrompt;
    private JLabel accountNumberPrompt;
    private JLabel accountNumberLabel;
    private JLabel balancePrompt;
    private JButton removeAccountButton;
    protected JLabel removeAccountErrorLabel;
    private ArrayList<JLabel> balancesLabels;

    // service
    protected AccountService accountService;
    protected AccountOperationService accountOperationService;

    public AccountInfoPanel(){
        // account prompt
        accountTypePrompt = new JLabel("", JLabel.CENTER);
        accountTypePrompt.setBounds(0, 0, 160, 50);
        accountTypePrompt.setFont(new Font("serif", Font.PLAIN, LABEL_FONT_SIZE));
        add(accountTypePrompt);
        // account number prompt
        accountNumberPrompt = new JLabel("Account Number:", JLabel.RIGHT);
        accountNumberPrompt.setBounds(0, 60, 160, 50);
        accountNumberPrompt.setFont(new Font("serif", Font.PLAIN, LABEL_FONT_SIZE));
        add(accountNumberPrompt);
        // account number label
        accountNumberLabel = new JLabel("", JLabel.RIGHT);
        accountNumberLabel.setBounds(170, 60, 160, 50);
        accountNumberLabel.setFont(new Font("serif", Font.PLAIN, LABEL_FONT_SIZE));
        add(accountNumberLabel);
        // balance prompt
        balancePrompt = new JLabel("Balances:", JLabel.CENTER);
        balancePrompt.setBounds(0, 120, 160, 30);
        balancePrompt.setFont(new Font("serif", Font.PLAIN, LABEL_FONT_SIZE));
        add(balancePrompt);
        // delete account button
        removeAccountButton = new JButton("delete account");
        removeAccountButton.setBounds(50, 500, 200, 40);
        removeAccountButton.setFont(new Font("serif", Font.PLAIN, BUTTON_FONT_SIZE));
        removeAccountButton.addActionListener(this::clickDeleteAccountButton);
        add(removeAccountButton);
        // delete account error label
        removeAccountErrorLabel = new JLabel("Can only delete account without balances!");
        removeAccountErrorLabel.setBounds(50, 550, 400, 30);
        removeAccountErrorLabel.setFont(new Font("serif", Font.PLAIN, BUTTON_FONT_SIZE));
        removeAccountErrorLabel.setVisible(false);
        add(removeAccountErrorLabel);
        // balances labels
        balancesLabels = new ArrayList<>();
        // Initialize
        initialize();
        // Panel
//        setSize(WIDTH, HEIGHT);
        setBounds(210, 10, 540, 540);
        setBackground(Color.LIGHT_GRAY);
        setLayout(null);
        setVisible(true);
    }

    /**
     * Initialize from database
     */
    protected void initialize(){
        // service
        accountService = new AccountServiceImpl();
        accountOperationService = new AccountOperationServiceImpl();
        // get from back end
        String accountType = getAccountType();
        String accountNumber = getAccountNumber();
        Hashtable<String, Double> balances = getBalances();
        // set
        setAccountTypePrompt(accountType);
        setAccountNumber(accountNumber);
        setBalance(balances);
    };

    /**
     * Get account type from back-end
     * @return account type
     */
    protected abstract String getAccountType();

    /**
     * Get account number from back-end
     * @return account number
     */
    protected abstract String getAccountNumber();

    /**
     * Get balances from back-end
     * @return balances
     */
    protected abstract Hashtable<String, Double> getBalances();

    /**
     * Set account type
     * @param accountType account type
     */
    protected void setAccountTypePrompt(String accountType){
        accountTypePrompt.setText(accountType);
    }

    /**
     * Set account number
     * @param accountNumber account number
     */
    protected void setAccountNumber(String accountNumber){
        accountNumberLabel.setText(accountNumber);
    }

    /**
     * Set the balances of each type of money
     * @param balances balances
     */
    protected void setBalance(Hashtable<String, Double> balances){
        int i = 0;
        for(String key: balances.keySet()){
            JLabel jl = new JLabel(key+": "+balances.get(key), JLabel.LEFT);
            jl.setBounds(100, 160+40*i, 160, 40);
            jl.setFont(new Font("serif", Font.PLAIN, LABEL_FONT_SIZE));
            balancesLabels.add(jl);
            add(jl);
            i++;
        }
        repaint();
    }

    protected abstract void clickDeleteAccountButton(ActionEvent e);




}
