package frontend;

import business_logic_layer.impl.*;
import business_logic_layer.interfaces.*;
import enums.AccountType;
import enums.TransactionType;
import models.transaction.MoneyType;
import models.transaction.Transaction;
import models.users.Customer;
import utilities.BaseDao;
import utilities.FancyBank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;

public class TransferBetweenAccountsPanel extends JPanel{
    private static final int BUTTON_FONT_SIZE = 20;
    private static final int LABEL_FONT_SIZE = 20;
    protected static final int USD_TYPE = 1;
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    protected Hashtable<Integer, JCheckBox> moneyTypeBoxes;
    private  JComboBox<String> senderComboBox;
    private  JComboBox<String> receiverComboBox;
    private String sendAccount;
    private String receiveAccount;
    protected JTextField receivedNameTextField;
    protected JTextField moneyTextField;
    private JButton transferButton;
    private JLabel namePrompt;
    private JLabel moneyPrompt;


    // service
    private MoneyTypeService moneyTypeService;
    private AccountService accountService;
    private SecurityService securityService;
    private AccountOperationService accountOperationService;
    private CustomerService customerService;

    public TransferBetweenAccountsPanel(){
        senderComboBox = new JComboBox<String>();
        senderComboBox.setFont(new Font("serif", Font.PLAIN, LABEL_FONT_SIZE));
        senderComboBox.setBounds(210, 50, 200, 40);
        add(senderComboBox);
        sendAccount = "Checking";

        senderComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                // 只处理选中的状态
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    sendAccount = (String) senderComboBox.getSelectedItem();
                    System.out.println("Choose: " + senderComboBox.getSelectedIndex() + " = " + senderComboBox.getSelectedItem());
                    if(sendAccount.equals("Checking")){
                        int accountId = accountService.getAccountId(FancyBank.getInstance().getUserId(), AccountType.Checking);
                        reloadMoneyType(accountId);
                    }
                    else if(sendAccount.equals("Savings")){
                        int accountId = accountService.getAccountId(FancyBank.getInstance().getUserId(), AccountType.Savings);
                        reloadMoneyType(accountId);
                    }
                    else if(sendAccount.equals("Security")){
                        reloadMoneyType(-1);
                    }
                }
            }
        });

        // receiver name text field
        receivedNameTextField = new JTextField(20);
        receivedNameTextField.setBounds(210, 160, 200, 30);
        receivedNameTextField.setFont(new Font("serif", Font.PLAIN, LABEL_FONT_SIZE));
        add(receivedNameTextField);
        // input name label
        namePrompt = new JLabel("Receiver: ", JLabel.LEFT);
        namePrompt.setBounds(100, 160, 100, 30);
        namePrompt.setFont(new Font("serif", Font.PLAIN, LABEL_FONT_SIZE));
        add(namePrompt);

        receiverComboBox = new JComboBox<String>();
        receiverComboBox.addItem("Checking");
        receiverComboBox.addItem("Savings");
        receiverComboBox.addItem("Security");
        receiverComboBox.setFont(new Font("serif", Font.PLAIN, LABEL_FONT_SIZE));
        receiverComboBox.setBounds(210, 210, 200, 40);
        receiveAccount = "Checking";
        add(receiverComboBox);

        receiverComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    receiveAccount = (String) receiverComboBox.getSelectedItem();
                }
            }
        });

        // money text field
        moneyTextField = new JTextField(20);
        moneyTextField.setBounds(210, 260, 200, 30);
        moneyTextField.setFont(new Font("serif", Font.PLAIN, LABEL_FONT_SIZE));
        add(moneyTextField);
        // input name label
        moneyPrompt = new JLabel("Money: ", JLabel.LEFT);
        moneyPrompt.setBounds(100, 260, 100, 30);
        moneyPrompt.setFont(new Font("serif", Font.PLAIN, LABEL_FONT_SIZE));
        add(moneyPrompt);

        // account button
        transferButton = new JButton("Send");
        transferButton.setBounds(200, 320, 100, 40);
        transferButton.setFont(new Font("serif", Font.PLAIN, BUTTON_FONT_SIZE));
        transferButton.addActionListener(this::clickTransferButton);
        add(transferButton);

        initialize();

        // panel
        setSize(WIDTH, HEIGHT);
        setLayout(null);
    }

    private void initialize(){
        // service
        moneyTypeService = new MoneyTypeServiceImpl();
        accountService = new AccountServiceImpl();
        securityService = new SecurityServiceImpl();
        accountOperationService = new AccountOperationServiceImpl();
        customerService = new CustomerServiceImpl();
        // component
        if(accountService.hasAccount(FancyBank.getInstance().getUserId(), AccountType.Checking)){
            senderComboBox.addItem("Checking");
        }
        if(accountService.hasAccount(FancyBank.getInstance().getUserId(), AccountType.Savings)){
            senderComboBox.addItem("Savings");
        }
        if(securityService.checkAccountExists(FancyBank.getInstance().getUserId())){
            senderComboBox.addItem("Security");
        }
    }

    private void reloadMoneyType(int accountId){
        if(moneyTypeBoxes != null){
            for(int moneyId: moneyTypeBoxes.keySet()){
                remove(moneyTypeBoxes.get(moneyId));
            }
        }
        // get account id
        moneyTypeBoxes = new Hashtable<>();
        ArrayList<MoneyType> moneyTypes = null;
        if(accountId == -1){
            moneyTypeBoxes.put(USD_TYPE, new JCheckBox("USD"));
            moneyTypeBoxes.get(USD_TYPE).setBounds(130, 100, 80, 30);
            moneyTypeBoxes.get(USD_TYPE).setFont(new Font("serif", Font.PLAIN, BUTTON_FONT_SIZE));
            add(moneyTypeBoxes.get(USD_TYPE));
        }
        else{
            moneyTypes = accountService.getAccountMoneyType(accountId, FancyBank.getInstance().getUserId());
            int i = 0;
            for (MoneyType moneyType : moneyTypes) {
                moneyTypeBoxes.put(moneyType.getId(), new JCheckBox(moneyType.getType()));
                moneyTypeBoxes.get(moneyType.getId()).setBounds(130 + 100 * i, 100, 80, 30);
                moneyTypeBoxes.get(moneyType.getId()).setFont(new Font("serif", Font.PLAIN, BUTTON_FONT_SIZE));
                add(moneyTypeBoxes.get(moneyType.getId()));
                i++;
            }
        }
        repaint();
    }

    private void clickTransferButton(ActionEvent e){
        double money = -1;
        try{
            money = Double.parseDouble(moneyTextField.getText());
        } catch (NumberFormatException numberFormatException){
            JOptionPane.showMessageDialog(this, "Invalid money", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if(money <= 0){
            JOptionPane.showMessageDialog(this, "Money should be larger than 0", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int moneyType = -1;
        for(int t: moneyTypeBoxes.keySet()){
            if(moneyTypeBoxes.get(t).isSelected()){
                if(moneyType != -1){
                    JOptionPane.showMessageDialog(this, "You can only choose one type of money!", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                moneyType = t;
            }
        }
        if(moneyType == -1){
            JOptionPane.showMessageDialog(this, "You should choose one type of money!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String receiverName = receivedNameTextField.getText();
        // get sender account id
        int senderAccountId = -1;
        if(sendAccount.equals("Checking")){
            senderAccountId = accountService.getAccountId(FancyBank.getInstance().getUserId(), AccountType.Checking);
        }
        else if(sendAccount.equals("Savings")){
            senderAccountId = accountService.getAccountId(FancyBank.getInstance().getUserId(), AccountType.Savings);
        }
        else if(sendAccount.equals("Security")){
            int receiverId = -1;
            if(!customerService.hasCustomer(receiverName)){
                JOptionPane.showMessageDialog(this, receiverName + " not found!", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
            receiverId = customerService.getCustomerId(receiverName);
            if(receiverId != FancyBank.getInstance().getUserId()){
                JOptionPane.showMessageDialog(this, "You can only transfer money to your own account!", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
            int receiverAccountId = -1;
            if(receiveAccount.equals("Checking")){
                if(accountService.hasAccount(receiverId, AccountType.Checking)){
                    receiverAccountId = accountService.getAccountId(receiverId, AccountType.Checking);
                }
            }
            else if(receiveAccount.equals("Savings")){
                if(accountService.hasAccount(receiverId, AccountType.Savings)){
                    receiverAccountId = accountService.getAccountId(receiverId, AccountType.Savings);
                }
            }
            if(receiverAccountId == -1){
                JOptionPane.showMessageDialog(this, "Receiver doesn't have the account!", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
            Transaction transaction = new Transaction(
                    new Customer(),
                    money,
                    TransactionType.Deposit,
                    receiverAccountId,
                    moneyType
            );
            if(!accountOperationService.changeBalance(TransactionType.Deposit, receiverAccountId, money, moneyType)){
                JOptionPane.showMessageDialog(this, "You don't have enough money!", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
            accountOperationService.addTransaction(FancyBank.getInstance().getUserId(), transaction);
            // increase money
            Connection connection = BaseDao.getConnection();
            try{
                double m = securityService.getCustomerMoney(FancyBank.getInstance().getUserId());
                securityService.modifyMoneyInSecurityAccount(connection, FancyBank.getInstance().getUserId(), m-money);
            } catch (SQLException sqlException){
                sqlException.printStackTrace();
                return;
            }
            JOptionPane.showMessageDialog(this, "Transfer successfully!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // get receiver id
        int receiverId = -1;
        if(!customerService.hasCustomer(receiverName)){
            JOptionPane.showMessageDialog(this, receiverName + " not found!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        receiverId = customerService.getCustomerId(receiverName);
        int receiverAccountId = -1;
        if(receiveAccount.equals("Checking")){
            if(accountService.hasAccount(receiverId, AccountType.Checking)){
                receiverAccountId = accountService.getAccountId(receiverId, AccountType.Checking);
            }
        }
        if(receiveAccount.equals("Savings")){
            if(accountService.hasAccount(receiverId, AccountType.Savings)){
                receiverAccountId = accountService.getAccountId(receiverId, AccountType.Savings);
            }
        }
        if(receiveAccount.equals("Security")){
            if(securityService.checkAccountExists(receiverId)){
                if(receiverId != FancyBank.getInstance().getUserId()){
                    JOptionPane.showMessageDialog(this, "You can only transfer money to your own security account!", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                else{
                    if(sendAccount.equals("Savings")){
                        // decrease money
                        Transaction transaction = new Transaction(
                                new Customer(),
                                money,
                                TransactionType.Withdraw,
                                senderAccountId,
                                moneyType
                        );
                        if(!accountOperationService.changeBalance(TransactionType.Withdraw, senderAccountId, money, moneyType)){
                            JOptionPane.showMessageDialog(this, "You don't have enough money!", "Warning", JOptionPane.WARNING_MESSAGE);
                            return;
                        }
                        accountOperationService.addTransaction(FancyBank.getInstance().getUserId(), transaction);
                        // increase money
                        Connection connection = BaseDao.getConnection();
                        try{
                            securityService.modifyMoneyInSecurityAccount(connection, receiverAccountId, money);
                        } catch (SQLException sqlException){
                            sqlException.printStackTrace();
                            return;
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(this, "You can only transfer money from savings account to security account!", "Warning", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                }
            }
        }
        if(receiverAccountId == -1){
            JOptionPane.showMessageDialog(this, "Receiver doesn't have the account!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        // delete money from sender
        // decrease money
        Transaction transaction = new Transaction(
                new Customer(),
                money,
                TransactionType.Withdraw,
                senderAccountId,
                moneyType
        );
        if(!accountOperationService.changeBalance(TransactionType.Withdraw, senderAccountId, money, moneyType)){
            JOptionPane.showMessageDialog(this, "You don't have enough money!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        accountOperationService.addTransaction(FancyBank.getInstance().getUserId(), transaction);


        // add money to receiver
        // decrease money
        transaction = new Transaction(
                new Customer(),
                money,
                TransactionType.Deposit,
                receiverAccountId,
                moneyType
        );
        accountOperationService.changeBalance(TransactionType.Deposit, receiverAccountId, money, moneyType);
        accountOperationService.addTransaction(receiverId, transaction);
        JOptionPane.showMessageDialog(this, "Transfer successfully!", "Warning", JOptionPane.WARNING_MESSAGE);
    }

}
