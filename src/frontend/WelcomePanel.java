package frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Welcome Panel
 * extends: JPanel
 */
public class WelcomePanel extends JPanel {
    private static final int WELCOME_TEXT_FONT_SIZE = 40;
    private static final int BUTTON_FONT_SIZE = 20;
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private JLabel headerLabel;
    private JButton loginButton;
    private JButton registerButton;

    public WelcomePanel(){
        // welcome label
        headerLabel = new JLabel("Welcome to Fancy Bank", JLabel.CENTER);
        headerLabel.setBounds(100, 50, 600, 200);
        headerLabel.setFont(new Font("serif", Font.PLAIN, WELCOME_TEXT_FONT_SIZE));
        add(headerLabel);
        // login button
        loginButton = new JButton("Sign in");
        loginButton.setBounds(200, 300, 100, 50);
        loginButton.setFont(new Font("serif", Font.PLAIN, BUTTON_FONT_SIZE));
        loginButton.addActionListener(this::clickLoginButton);
        add(loginButton);
        // register button
        registerButton = new JButton("Sign up");
        registerButton.setBounds(500, 300, 100, 50);
        registerButton.setFont(new Font("serif", Font.PLAIN, BUTTON_FONT_SIZE));
        registerButton.addActionListener(this::clickRegisterButton);
        add(registerButton);
        // Panel
        setSize(WIDTH, HEIGHT);
        setLayout(null);
        setVisible(true);
    }

    private void clickLoginButton(ActionEvent e){
        MainFrame.getInstance().setPanel(new LoginPanel());
    }

    private void clickRegisterButton(ActionEvent e){
        MainFrame.getInstance().setPanel(new RegisterPanel());
    }



}
