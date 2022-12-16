package frontend;

import utilities.FancyBank;
import utilities.SampleDate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Update simulated day
 * extends: JPanel
 */
public class UpdateDayPanel extends JPanel{

    private static final int BUTTON_FONT_SIZE = 20;
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    private JButton updateButton;

    public UpdateDayPanel(){
        // return button
        updateButton = new JButton("Update Date");
        updateButton.setBounds(350, 300, 160, 40);
        updateButton.setFont(new Font("serif", Font.PLAIN, BUTTON_FONT_SIZE));
        updateButton.addActionListener(this::clickUpdateButton);
        add(updateButton);

        // Panel
        setSize(WIDTH, HEIGHT);
        setLayout(null);
    }

    private void clickUpdateButton(ActionEvent e){
        SampleDate.getInstance().addOneDay();
    }

}
