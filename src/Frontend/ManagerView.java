package Frontend;

import BusinessLogicLayer.ManagerService;
import BusinessLogicLayer.impl.ManagerServiceImpl;

import javax.swing.*;
import java.awt.*;

public class ManagerView extends JPanel{
    private ManagerService managerService;
    private JPanel managerView;
    private JTabbedPane tabbedPane;
    private JPanel allCustomer;
    private JPanel customerWithLoan;
    private JPanel searchCustomer;
    private JPanel dailyReport;
    private JPanel stock;

    public static void main(String[] args) {
        JFrame frame = new JFrame("managerView");
        frame.setContentPane(new ManagerView());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public ManagerView() {
        add(managerView);


    }




}
