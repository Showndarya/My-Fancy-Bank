package Frontend;

import BusinessLogicLayer.CustomerService;
import BusinessLogicLayer.impl.CustomerServiceImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;


public class LoginPanel extends JPanel{
    private static final int PROMPT_TEXT_FONT_SIZE = 20;
    private static final int TEXTFIELD_FONT_SIZE = 16;
    private static final int BUTTON_FONT_SIZE = 20;
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    private JLabel loginPromptLabel;
    private JLabel namePromptLabel;
    private JLabel passwordPromptLabel;
    private JTextField nameTextField;
    private JPasswordField passwordField;
    private JLabel errorLabel;
    private JButton loginButton;
    private JButton returnButton;

    // services
    CustomerService customerService;

    public LoginPanel(){
        // Sign in
        loginPromptLabel = new JLabel("Sign in:", JLabel.RIGHT);
        loginPromptLabel.setBounds(220, 60, 100, 50);
        loginPromptLabel.setFont(new Font("serif", Font.PLAIN, PROMPT_TEXT_FONT_SIZE));
        add(loginPromptLabel);
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
        // Name text field
        nameTextField = new JTextField(20);
        nameTextField.setBounds(340,150,150,30);
        nameTextField.setFont(new Font("serif", Font.PLAIN, TEXTFIELD_FONT_SIZE));
        add(nameTextField);
        // Name text field
        passwordField = new JPasswordField(20);
        passwordField.setBounds(340,210,150,30);
        passwordField.setFont(new Font("serif", Font.PLAIN, TEXTFIELD_FONT_SIZE));
        add(passwordField);
        // Error label
        errorLabel = new JLabel("Wrong name or password!", JLabel.CENTER);
        errorLabel.setBounds(250, 240, 300, 30);
        errorLabel.setFont(new Font("serif", Font.PLAIN, PROMPT_TEXT_FONT_SIZE));
        errorLabel.setVisible(false);
        add(errorLabel);
        // login button
        loginButton = new JButton("Sign in");
        loginButton.setBounds(250, 280, 100, 30);
        loginButton.setFont(new Font("serif", Font.PLAIN, BUTTON_FONT_SIZE));
        loginButton.addActionListener(this::clickLoginButton);
        add(loginButton);
        // login button
        returnButton = new JButton("Return");
        returnButton.setBounds(450, 280, 100, 30);
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

    private void clickLoginButton(ActionEvent e){
        String name = nameTextField.getText();
        char[] password = passwordField.getPassword();
        if(name.length() == 0 || password.length == 0){
            return;
        }
//        boolean ret = customerService.loginCustomer(name, String.valueOf(password));
    }

    private void clickReturnButton(ActionEvent e){

    }

}
