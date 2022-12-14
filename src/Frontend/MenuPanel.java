package Frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MenuPanel extends JPanel{
    private static final int BUTTON_FONT_SIZE = 20;
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private JButton myAccountButton;

    public MenuPanel(){
        // my account button
        myAccountButton = new JButton("My Account");
        myAccountButton.setBounds(100, 50, 160, 40);
        myAccountButton.setFont(new Font("serif", Font.PLAIN, BUTTON_FONT_SIZE));
        myAccountButton.addActionListener(this::clickMyAccountButton);
        add(myAccountButton);
        // Panel
        setSize(WIDTH, HEIGHT);
        setLayout(null);
    }

    private void clickMyAccountButton(ActionEvent e){
        MainFrame.getInstance().setPanel(new MyAccountPanel());
    }


}
