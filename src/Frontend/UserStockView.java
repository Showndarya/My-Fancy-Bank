package Frontend;

import Frontend.component.StockListComponent;

import javax.swing.*;

public class UserStockView extends JPanel {
    private JPanel rightPanel;
    private JPanel userInfoPanel;
    private JScrollPane userStockPanel;
    private JPanel leftPanel;
    private JPanel userStockView;
    private JLabel userName;

    public static void main(String[] args) {
        JFrame frame = new JFrame("UserStockView");
        frame.setContentPane(new UserStockView().userStockView);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private JLabel securityBalance;
    private JTable table1;
    private JLabel stockMarketLabel;
    private JPanel stockListPanel;

    public UserStockView() {
        add(userStockView);
        stockListPanel.add(StockListComponent.getInstance());
        userName.setText("get user name");
        securityBalance.setText("get user money");
    }
}
