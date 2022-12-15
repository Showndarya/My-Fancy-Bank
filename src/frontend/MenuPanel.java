package frontend;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.event.ActionEvent;



public class MenuPanel extends JTabbedPane{
    private static final int BUTTON_FONT_SIZE = 20;
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

//    private JTabbedPane mainTabbedPane;
    private JPanel stockPanel;
    private JPanel myAccountPanel;
    private JPanel returnPanel;

    public MenuPanel(){
        super(SwingConstants.LEFT);
//        mainTabbedPane = new JTabbedPane(SwingConstants.LEFT);
        // panels

        // To add a panel
        // new a panel
        // add your panel to that panel
        // add the panel to mainTabbedPane

        // My Account
        myAccountPanel = generateTabPanel();
        myAccountPanel.add(new MyAccountPanel());
        addTab("Accounts", myAccountPanel);

        // Stock
        stockPanel = generateTabPanel();

        addTab("Stock", stockPanel);

        // Return
        returnPanel = generateTabPanel();
        returnPanel.add(new ReturnPanel());
        addTab("Log out", returnPanel);

        // Panel
        addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int index = getSelectedIndex();
                if(index == 0){
                    myAccountPanel.removeAll();
                    myAccountPanel.add(new MyAccountPanel());
                    myAccountPanel.repaint();
                }
                else if(index == 1){


                }
                else if(index == 2){
                    returnPanel.removeAll();
                    returnPanel.add(new ReturnPanel());
                    returnPanel.repaint();
                }
                System.out.println("Current index: " + getSelectedIndex());
            }
        });
        setSelectedIndex(0);
        setVisible(true);
    }

    private void clickMyAccountButton(ActionEvent e){
        MainFrame.getInstance().setPanel(new MyAccountPanel());
    }

    private JPanel generateTabPanel(){
        JPanel jPanel = new JPanel();
        jPanel.setLayout(null);
        jPanel.setSize(800, 600);
        return jPanel;
    }


}
