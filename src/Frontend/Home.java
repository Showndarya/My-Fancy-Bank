package Frontend;

import javax.swing.*;

public class Home {
    private JPanel rootPanel;

    public static void main(String[] args) {
        JFrame jFrame = new JFrame("Home");
        jFrame.setContentPane(new Home().rootPanel);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.pack();
        jFrame.setVisible(true);
    }
}
