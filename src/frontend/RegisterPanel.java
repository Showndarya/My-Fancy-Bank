package frontend;

import business_logic_layer.interfaces.CustomerService;
import business_logic_layer.impl.CustomerServiceImpl;
import utilities.FancyBank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class RegisterPanel extends JPanel{
    private static final int PROMPT_TEXT_FONT_SIZE = 20;
    private static final int TEXTFIELD_FONT_SIZE = 16;
    private static final int BUTTON_FONT_SIZE = 20;
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    private JLabel registerPromptLabel;
    private JLabel namePromptLabel;
    private JLabel passwordPromptLabel;
    private JLabel passwordRepeatPromptLabel;
    private JTextField nameTextField;
    private JPasswordField passwordField;
    private JPasswordField passwordRepeatField;
    private JLabel errorLabel;
    private JButton registerButton;
    private JButton returnButton;

    // service
    private CustomerService customerService;

    public RegisterPanel(){
        // Sign up
        registerPromptLabel = new JLabel("Sign up:", JLabel.RIGHT);
        registerPromptLabel.setBounds(220, 60, 100, 50);
        registerPromptLabel.setFont(new Font("serif", Font.PLAIN, PROMPT_TEXT_FONT_SIZE));
        add(registerPromptLabel);
        // Name label
        namePromptLabel = new JLabel("Name:", JLabel.RIGHT);
        namePromptLabel.setBounds(220, 140, 100, 50);
        namePromptLabel.setFont(new Font("serif", Font.PLAIN, PROMPT_TEXT_FONT_SIZE));
        add(namePromptLabel);
        // Password label
        passwordPromptLabel = new JLabel("Password:", JLabel.RIGHT);
        passwordPromptLabel.setBounds(220, 200, 100, 50);
        passwordPromptLabel.setFont(new Font("serif", Font.PLAIN, PROMPT_TEXT_FONT_SIZE));
        add(passwordPromptLabel);
        // Repeat Password label
        passwordRepeatPromptLabel = new JLabel("Repeat:", JLabel.RIGHT);
        passwordRepeatPromptLabel.setBounds(220, 260, 100, 50);
        passwordRepeatPromptLabel.setFont(new Font("serif", Font.PLAIN, PROMPT_TEXT_FONT_SIZE));
        add(passwordRepeatPromptLabel);
        // Name text field
        nameTextField = new JTextField(20);
        nameTextField.setBounds(340,150,150,30);
        nameTextField.setFont(new Font("serif", Font.PLAIN, TEXTFIELD_FONT_SIZE));
        add(nameTextField);
        // password text field
        passwordField = new JPasswordField(20);
        passwordField.setBounds(340,210,150,30);
        passwordField.setFont(new Font("serif", Font.PLAIN, TEXTFIELD_FONT_SIZE));
        add(passwordField);
        // password repeat text field
        passwordRepeatField = new JPasswordField(20);
        passwordRepeatField.setBounds(340,270,150,30);
        passwordRepeatField.setFont(new Font("serif", Font.PLAIN, TEXTFIELD_FONT_SIZE));
        add(passwordRepeatField);
        // Error label
        errorLabel = new JLabel("Passwords don't match!", JLabel.CENTER);
        errorLabel.setBounds(250, 310, 300, 30);
        errorLabel.setFont(new Font("serif", Font.PLAIN, PROMPT_TEXT_FONT_SIZE));
        errorLabel.setVisible(false);
        add(errorLabel);
        // login button
        registerButton = new JButton("Sign up");
        registerButton.setBounds(250, 340, 100, 30);
        registerButton.setFont(new Font("serif", Font.PLAIN, BUTTON_FONT_SIZE));
        registerButton.addActionListener(this::clickRegisterButton);
        add(registerButton);
        // login button
        returnButton = new JButton("Return");
        returnButton.setBounds(450, 340, 100, 30);
        returnButton.setFont(new Font("serif", Font.PLAIN, BUTTON_FONT_SIZE));
        returnButton.addActionListener(this::clickReturnButton);
        add(returnButton);
        // initialize
        initialize();
        // Panel
        setSize(WIDTH, HEIGHT);
        setLayout(null);
    }

    private void initialize(){
        customerService = new CustomerServiceImpl();
    }

    private void clickRegisterButton(ActionEvent e){
        String name = nameTextField.getText();
        String password = String.valueOf(passwordField.getPassword());
        String repeatedPassword = String.valueOf(passwordRepeatField.getPassword());
        if(name.length() == 0 || password.length() == 0 || repeatedPassword.length() == 0){
            return;
        }
        if(!password.equals(repeatedPassword)){
            errorLabel.setText("Passwords don't match!");
            errorLabel.setVisible(true);
            repaint();
            return;
        }
        boolean ret = customerService.registerCustomer(name, password);
        if(ret){
            // set customer info
            int userId = customerService.getCustomerId(name);
            FancyBank.getInstance().setUserId(userId);
            FancyBank.getInstance().setUserName(name);
            // set panel
            MainFrame.getInstance().setPanel(new MenuPanel());
        }
        else{
            errorLabel.setText("Name of "+name+" has existed!");
            errorLabel.setVisible(true);
            repaint();
        }
    }

    private void clickReturnButton(ActionEvent e){

    }

}
