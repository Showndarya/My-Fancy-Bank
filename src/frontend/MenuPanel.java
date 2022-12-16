package frontend;

import business_logic_layer.impl.SecurityServiceImpl;
import business_logic_layer.interfaces.SecurityService;
import frontend.component.AccountTransactionsListComponent;
import utilities.FancyBank;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Panel of menu after signing in
 * extends: JTabbedPane
 */
public class MenuPanel extends JTabbedPane {
    private static final int BUTTON_FONT_SIZE = 20;
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    //    private JTabbedPane mainTabbedPane;
    private JPanel stockPanel;
    private JPanel myAccountPanel;
    private JPanel returnPanel;
    private JPanel transactionsPanel;

    private JPanel stockTransaction;

    private JPanel loanPanel;

    private JPanel updateDayPanel;

    private SecurityService securityService;


    public MenuPanel() {
        super(SwingConstants.LEFT);
        securityService = new SecurityServiceImpl();
//        mainTabbedPane = new JTabbedPane(SwingConstants.LEFT);
        // panels

        // To add a panel
        // new a panel
        // add your panel to that panel
        // add the panel to mainTabbedPane

        // My Account
        myAccountPanel = generateTabPanel();
        myAccountPanel.add(new MyAccountPanel());
        addTab("Accounts", myAccountPanel);

        // Stock
        stockPanel = generateTabPanel();

        UserStockView userStockView = new UserStockView();
        boolean b = securityService.checkAccountExists(FancyBank.getInstance().getUserId());
        System.out.println(b);
        if (b) {
            stockPanel.setLayout(new FlowLayout());
            stockPanel.add(userStockView);
        } else {
            stockPanel.add(new OpenSecurityAccountPanel());
        }


        addTab("Stock", stockPanel);

        stockTransaction = generateTabPanel();
        stockTransaction.setLayout(new FlowLayout());
        StockTransactionView stockTransactionView = new StockTransactionView();
        stockTransaction.add(stockTransactionView);
        addTab("StockTransaction", stockTransaction);
        //Account Transactions
        transactionsPanel = generateTabPanel();
        transactionsPanel.setLayout(new FlowLayout());
        transactionsPanel.add(new AccountTransactionsView());
        addTab("Transfer between", transactionsPanel);

        // Loan
        loanPanel = generateTabPanel();
        loanPanel.setLayout(new FlowLayout());
        loanPanel.add(new LoanListView());
        addTab("Loan Transactions", loanPanel);

        // Return
        returnPanel = generateTabPanel();
        returnPanel.add(new ReturnPanel());
        addTab("Log out", returnPanel);

        // Update day
        updateDayPanel = generateTabPanel();
        updateDayPanel.add(new UpdateDayPanel());
        addTab("Update Day", updateDayPanel);


        // Panel
        addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int index = getSelectedIndex();
                if (index == 0) {
                    myAccountPanel.removeAll();
                    myAccountPanel.add(new MyAccountPanel());
                    myAccountPanel.repaint();
                } else if (index == 1) {
                    boolean b = securityService.checkAccountExists(FancyBank.getInstance().getUserId());
                    if (b) {
                        userStockView.reloadTable();
                    }
                } else if (index == 2) {
                    stockTransactionView.reload();

                } else if (index == 3) {
                    transactionsPanel.removeAll();
                    transactionsPanel.add(new AccountTransactionsView());
                    transactionsPanel.repaint();
                    AccountTransactionsListComponent.getInstance().reloadTable();
                } else if (index == 4) {
                    loanPanel.removeAll();
                    loanPanel.add(new LoanListView());
                    loanPanel.repaint();
                } else if (index == 5) {
                    returnPanel.removeAll();
                    returnPanel.add(new ReturnPanel());
                    returnPanel.repaint();
                } else if (index == 6) {
                    updateDayPanel.removeAll();
                    updateDayPanel.add(new UpdateDayPanel());
                    updateDayPanel.repaint();
                }
                System.out.println("Current index: " + getSelectedIndex());
            }
        });
        setSelectedIndex(0);
        setVisible(true);
    }

    private void clickMyAccountButton(ActionEvent e) {
        MainFrame.getInstance().setPanel(new MyAccountPanel());
    }

    private JPanel generateTabPanel() {
        JPanel jPanel = new JPanel();
        jPanel.setLayout(null);
        jPanel.setSize(800, 600);
        return jPanel;
    }


}
