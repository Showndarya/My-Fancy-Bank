package Frontend;

import javax.swing.*;

public class ManagerView extends JPanel{
    private JPanel managerView;
    private JButton getAllCustomersButton;
    private JButton getAllCustomersWithLoanButton;
    private JButton searchCustomerButton;
    private JButton dailyReportButton;
    private JButton stockButton;

    public static void main(String[] args) {
        JFrame frame = new JFrame("managerView");
        frame.setContentPane(new ManagerView());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public ManagerView() {
        add(getAllCustomersButton);
        add(getAllCustomersWithLoanButton);
        add(searchCustomerButton);
        add(dailyReportButton);
        add(stockButton);
    }

}
