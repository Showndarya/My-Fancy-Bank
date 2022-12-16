package frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Main Frame
 * extends: JFrame
 */
public class MainFrame extends JFrame{
    private static MainFrame INSTANCE;
    private static final String FRAME_TITLE = "Utilities.FancyBank";
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 600;
    private JPanel currentPanel;
    private JComponent currentPane;


    public static MainFrame getInstance(){
        if(INSTANCE == null){
            INSTANCE = new MainFrame();
        }
        return INSTANCE;
    }

    private MainFrame(){
        super(FRAME_TITLE);
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(1, 1));
        setPanel(new WelcomePanel());
    }


    public void setPanel(JComponent jComponent){
//        if(currentPane != null){
//            remove(currentPane);
//        }
        currentPane = jComponent;
        setContentPane(jComponent);
        setVisible(true);
    }


}
