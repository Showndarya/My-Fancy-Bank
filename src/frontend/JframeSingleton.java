package frontend;

import javax.swing.*;

public class JframeSingleton {
    public static  JframeSingleton jframeSingleton = null;
    private static JFrame jFrame;

    public static JframeSingleton getInstance() {
        if(jframeSingleton==null)
        {
            jframeSingleton=new JframeSingleton();
            jFrame = new JFrame();
            jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        }

        return jframeSingleton;
    }

    public void addPanel(JPanel jPanel) {
        jFrame.setContentPane(jPanel);
        jFrame.setSize(300,300);
        jFrame.pack();
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
    }

    public void removePanel() {
        jFrame.removeAll();
    }
}
