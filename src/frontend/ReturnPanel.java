package frontend;

import utilities.FancyBank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ReturnPanel extends JPanel{
    private static final int BUTTON_FONT_SIZE = 20;
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    private JButton returnButton;

    public ReturnPanel(){
        // return button
        returnButton = new JButton("log out");
        returnButton.setBounds(350, 300, 160, 40);
        returnButton.setFont(new Font("serif", Font.PLAIN, BUTTON_FONT_SIZE));
        returnButton.addActionListener(this::clickReturnButton);
        add(returnButton);

        // Panel
        setSize(WIDTH, HEIGHT);
        setLayout(null);
    }

    // manager log out
    public ReturnPanel(int manager){
        // return button
        returnButton = new JButton("log out");
        returnButton.setBounds(350, 300, 160, 40);
        returnButton.setFont(new Font("serif", Font.PLAIN, BUTTON_FONT_SIZE));
        returnButton.addActionListener(this::clickReturnButton);
        add(returnButton);
    }

    private void clickReturnButton(ActionEvent e){
        FancyBank.getInstance().setUserId(-1);
        FancyBank.getInstance().setUserName("");
        MainFrame.getInstance().setPanel(new WelcomePanel());
    }

}
