package Frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
public class MyAccountPanel extends JPanel{
    private static final int LABEL_FONT_SIZE = 20;
    private static final int BUTTON_FONT_SIZE = 20;
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    private JLabel accountLabel;
    private JButton checkingAccountButton;
    private JButton addCheckingAccountButton;
    private JButton savingsAccountButton;
    private JButton addSavingsAccountButton;
    private JButton securityAccountButton;
    private JButton addSecurityAccountButton;
    private JButton returnButton;
    private JPanel currentAccountPanel;

    public MyAccountPanel(){
        // account label
        accountLabel = new JLabel("My accounts", JLabel.RIGHT);
        accountLabel.setBounds(0, 0, 130, 50);
        accountLabel.setFont(new Font("serif", Font.PLAIN, LABEL_FONT_SIZE));
        add(accountLabel);
        // checking account button
        checkingAccountButton = new JButton("checking");
        checkingAccountButton.setBounds(0, 50, 120, 40);
        checkingAccountButton.setFont(new Font("serif", Font.PLAIN, BUTTON_FONT_SIZE));
        checkingAccountButton.addActionListener(this::clickCheckingAccountButton);
        add(checkingAccountButton);
        // add checking account button
        addCheckingAccountButton = new JButton("add");
        addCheckingAccountButton.setBounds(130, 50, 70, 40);
        addCheckingAccountButton.setFont(new Font("serif", Font.PLAIN, BUTTON_FONT_SIZE));
        addCheckingAccountButton.addActionListener(this::clickAddCheckingAccountButton);
        add(addCheckingAccountButton);
        // savings account button
        savingsAccountButton = new JButton("savings");
        savingsAccountButton.setBounds(0, 100, 120, 40);
        savingsAccountButton.setFont(new Font("serif", Font.PLAIN, BUTTON_FONT_SIZE));
        savingsAccountButton.addActionListener(this::clickSavingsAccountButton);
        add(savingsAccountButton);
        // add savings account button
        addSavingsAccountButton = new JButton("add");
        addSavingsAccountButton.setBounds(130, 100, 70, 40);
        addSavingsAccountButton.setFont(new Font("serif", Font.PLAIN, BUTTON_FONT_SIZE));
        addSavingsAccountButton.addActionListener(this::clickAddSavingsAccountButton);
        add(addSavingsAccountButton);
        // security account button
        securityAccountButton = new JButton("security");
        securityAccountButton.setBounds(0, 150, 120, 40);
        securityAccountButton.setFont(new Font("serif", Font.PLAIN, BUTTON_FONT_SIZE));
        securityAccountButton.addActionListener(this::clickSecurityAccountButton);
        add(securityAccountButton);
        // add security account button
        addSecurityAccountButton = new JButton("add");
        addSecurityAccountButton.setBounds(130, 150, 70, 40);
        addSecurityAccountButton.setFont(new Font("serif", Font.PLAIN, BUTTON_FONT_SIZE));
        addSecurityAccountButton.addActionListener(this::clickAddSecurityAccountButton);
        add(addSecurityAccountButton);
        // return button
        returnButton = new JButton("return");
        returnButton.setBounds(0, 450, 160, 40);
        returnButton.setFont(new Font("serif", Font.PLAIN, BUTTON_FONT_SIZE));
        returnButton.addActionListener(this::clickReturnButton);
        add(returnButton);
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

    }

    private void clickAddSavingsAccountButton(ActionEvent e){

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

    }


}
