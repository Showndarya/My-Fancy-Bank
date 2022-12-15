package frontend;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.event.ActionEvent;



public class MenuPanel extends JPanel{
    private static final int BUTTON_FONT_SIZE = 20;
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    private JTabbedPane mainTabbedPane;
    private JPanel stockPanel;
    private JPanel myAccountPanel;

    public MenuPanel(){
        mainTabbedPane = new JTabbedPane(SwingConstants.LEFT);
        // panels
        myAccountPanel = new JPanel();
        myAccountPanel.add(new MyAccountPanel());
        mainTabbedPane.addTab("Accounts", myAccountPanel);

        stockPanel = new JPanel();

        mainTabbedPane.addTab("Stock", stockPanel);

        // To add a panel
        // new a panel
        // add your panel to that panel
        // add the panel to mainTabbedPane

        mainTabbedPane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int index = mainTabbedPane.getSelectedIndex();
                if(index == 0){
                    myAccountPanel.removeAll();
                    myAccountPanel.add(new MyAccountPanel());
                    myAccountPanel.validate();
                    myAccountPanel.repaint();
                }
                else if(index == 1){



                }
                System.out.println("Current index: " + mainTabbedPane.getSelectedIndex());
            }
        });

        mainTabbedPane.setSelectedIndex(0);
        MainFrame.getInstance().setContentPane(mainTabbedPane);
        // Panel
        setSize(WIDTH, HEIGHT);
        setLayout(null);
        setVisible(true);
        repaint();
    }

    private void clickMyAccountButton(ActionEvent e){
        MainFrame.getInstance().setPanel(new MyAccountPanel());
    }


}
