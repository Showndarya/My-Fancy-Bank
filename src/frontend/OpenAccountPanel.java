package frontend;

import business_logic_layer.impl.AccountOperationServiceImpl;
import business_logic_layer.impl.AccountServiceImpl;
import business_logic_layer.impl.MoneyTypeServiceImpl;
import business_logic_layer.impl.SecurityServiceImpl;
import business_logic_layer.interfaces.AccountOperationService;
import business_logic_layer.interfaces.AccountService;
import business_logic_layer.interfaces.MoneyTypeService;
import business_logic_layer.interfaces.SecurityService;
import dto.UserAccount;
import enums.AccountType;
import enums.TransactionType;
import models.transaction.MoneyType;
import org.omg.PortableInterceptor.INACTIVE;
import utilities.BaseDao;
import utilities.FancyBank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;

public abstract class OpenAccountPanel extends JPanel{
    protected static final double MINIMUM_INITIAL_MONEY = 100;
    protected static final double MINIMUM_MONEY_TYPE_NUM = 2;
    protected static final int USD_TYPE = 1;

    private static final int BUTTON_FONT_SIZE = 20;
    private static final int LABEL_FONT_SIZE = 20;
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    private JLabel openAccountPrompt;
    private JLabel inputPrompt;
    protected JTextField initialMoneyTextField;
    private JButton openButton;
    protected Hashtable<Integer, JCheckBox> moneyTypeBoxes;

    protected AccountOperationService accountOperationService;
    protected AccountService accountService;
    protected SecurityService securityService;
    protected MoneyTypeService moneyTypeService;

    public OpenAccountPanel(){
        reload();
    }

    public void reload(){
        // initial money label
        openAccountPrompt = new JLabel("", JLabel.RIGHT);
        openAccountPrompt.setBounds(130, 150, 200, 30);
        openAccountPrompt.setFont(new Font("serif", Font.PLAIN, LABEL_FONT_SIZE));
        add(openAccountPrompt);
        // initial money label
        inputPrompt = new JLabel("Initial Money: ", JLabel.RIGHT);
        inputPrompt.setBounds(50, 200, 160, 30);
        inputPrompt.setFont(new Font("serif", Font.PLAIN, LABEL_FONT_SIZE));
        add(inputPrompt);
        // initial money text field
        initialMoneyTextField = new JTextField(20);
        initialMoneyTextField.setBounds(210, 200, 200, 30);
        initialMoneyTextField.setFont(new Font("serif", Font.PLAIN, LABEL_FONT_SIZE));
        add(initialMoneyTextField);
        // checking account button
        openButton = new JButton("Open");
        openButton.setBounds(200, 320, 100, 40);
        openButton.setFont(new Font("serif", Font.PLAIN, BUTTON_FONT_SIZE));
        openButton.addActionListener(this::clickOpenButton);
        add(openButton);
        // initialize
        initialize();
        // panel
        setBounds(250, 10, 540, 540);
        setBackground(Color.LIGHT_GRAY);
        setLayout(null);
        setVisible(true);
    }

    private void initialize(){
        // service
        moneyTypeService = new MoneyTypeServiceImpl();
        accountOperationService = new AccountOperationServiceImpl();
        accountService = new AccountServiceImpl();
        securityService = new SecurityServiceImpl();
        // Component
        openAccountPrompt.setText(getPrompt());
        moneyTypeBoxes = new Hashtable<>();
        ArrayList<MoneyType> moneyTypes = null;
        try {
            moneyTypes = moneyTypeService.getAllMoneyTypes();
        } catch (SQLException e){
            e.printStackTrace();
            return;
        }
        int i = 0;
        for(MoneyType moneyType: moneyTypes){
            moneyTypeBoxes.put(moneyType.getId(), new JCheckBox(moneyType.getType()));
            moneyTypeBoxes.get(moneyType.getId()).setBounds(130+100*i, 260, 80, 30);
            moneyTypeBoxes.get(moneyType.getId()).setFont(new Font("serif", Font.PLAIN, BUTTON_FONT_SIZE));
            add(moneyTypeBoxes.get(moneyType.getId()));
            i++;
        }

    }

    protected abstract String getPrompt();

    protected abstract void clickOpenButton(ActionEvent e);


}
