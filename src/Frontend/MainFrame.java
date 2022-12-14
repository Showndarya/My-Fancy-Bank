package Frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class MainFrame extends JFrame{
    private static MainFrame INSTANCE;
    private static final String FRAME_TITLE = "FancyBank";
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private JPanel currentPanel;


    public static MainFrame getInstance(){
        if(INSTANCE == null){
            INSTANCE = new MainFrame();
        }
        return INSTANCE;
    }

    private MainFrame(){
        super(FRAME_TITLE);
        setSize(WIDTH, HEIGHT);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                System.exit(0);
            }
        });
        setLayout(new GridLayout(1, 1));


        WelcomePanel welcomePanel = new WelcomePanel();
        LoginPanel loginPanel = new LoginPanel();
        RegisterPanel registerPanel = new RegisterPanel();
        MenuPanel menuPanel = new MenuPanel();
        MyAccountPanel myAccountPanel = new MyAccountPanel();
        setPanel(welcomePanel);
    }

    public void setPanel(JPanel panel){
        if(currentPanel != null){
            remove(currentPanel);
        }
        add(panel);
        currentPanel = panel;
        setVisible(true);
    }

    public static void main(String[] args){
        MainFrame mainFrame = MainFrame.getInstance();
    }


}
