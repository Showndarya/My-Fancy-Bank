package frontend;

import business_logic_layer.interfaces.SecurityService;
import business_logic_layer.interfaces.StockService;
import business_logic_layer.impl.SecurityServiceImpl;
import business_logic_layer.impl.StockServiceImpl;
import frontend.component.StockListComponent;
import models.transaction.Stock;
import dto.TableList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class UserStockView extends JPanel {
    private JPanel rightPanel;
    private JPanel userInfoPanel;
    private JScrollPane userStockPanel;
    private JPanel leftPanel;
    private JPanel userStockView;
    private JLabel userName;
    private StockService stockService;
    private JLabel securityBalance;
    private JTable table1;
    private JLabel stockMarketLabel;
    private JPanel stockListPanel;
    private JButton btn1;
    private JButton btn2;
    private JPanel bottomPanel;
    private JTextField textField1;

    private SecurityService securityService;

    private double customerMoney;

    private int clientId;


    public UserStockView() {
        clientId = 1;
        securityService = new SecurityServiceImpl();
        stockService = new StockServiceImpl();
        add(userStockView);
        customerMoney = 0;
        String name = "YYJ"; //user service get user by id
        try {
            customerMoney = securityService.getCustomerMoney(1);
        } catch (SQLException e) {
            System.out.println("get customer money failed");
        }
        StockListComponent stockListComponent = StockListComponent.getInstance();
        stockListPanel.add(stockListComponent);
        userName.setText("User: " + name);
        securityBalance.setText("Security Balance: " + customerMoney);
        textField1.setPreferredSize(new Dimension(130, 25));
        btn1.setPreferredSize(new Dimension(100, 25));
        btn2.setPreferredSize(new Dimension(100, 25));
        TableList userStock = stockService.getUserStockDataByUserId(clientId);

        DefaultTableModel model = (DefaultTableModel) table1.getModel();
        Object[][] rowData = userStock.getRowData();
        model.setDataVector(rowData, userStock.getColumnsName());

//        table1 = new JTable(userStock.getRowData(), userStock.getColumnsName()) {
//            public boolean editCellAt(int row, int column, java.util.EventObject e) {
//                return false;
//            }
//        };

        table1.setDefaultEditor(Object.class, null);
        table1.setForeground(Color.BLACK);
        table1.setFont(new Font(null, Font.PLAIN, 14));
        table1.setSelectionForeground(Color.DARK_GRAY);
        table1.setSelectionBackground(Color.LIGHT_GRAY);
        table1.setGridColor(Color.GRAY);


        table1.getTableHeader().setFont(new Font(null, Font.BOLD, 14));
        table1.getTableHeader().setForeground(Color.RED);
        table1.getTableHeader().setResizingAllowed(false);
        table1.getTableHeader().setReorderingAllowed(false);


        table1.setRowHeight(30);


        table1.getColumnModel().getColumn(0).setPreferredWidth(40);

        table1.setPreferredScrollableViewportSize(new Dimension(400, 300));

        userStockPanel.setViewportView(table1);
        userStockPanel.setVisible(true);


        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
//                int selectedRow = table1.getSelectedRow();
                String tag = stockListComponent.getStockTag(); // what am i doing here......
                if (tag == null) {
                    JOptionPane.showMessageDialog(userStockPanel, "Please select a stock", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                double stockPrice = stockListComponent.getSelectedStockPrice();
                Stock stock = stockService.getStockByTag(tag);
                String num = textField1.getText();
                if (num.equals("")) {
                    JOptionPane.showMessageDialog(userStockPanel, "Please enter a number", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                int i = JOptionPane.showConfirmDialog(userStockView, "Buy stock at price " + stockPrice + "?", "Confirmation", JOptionPane.YES_NO_OPTION);
                if (i == 1) {
                    return;
                }
                stockService.buyStock(1, stock.getId(), Integer.parseInt(num), stockPrice);
                reloadTable();

                userStockView.validate();
                userStockView.repaint();

            }
        });
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int selectedRow = table1.getSelectedRow();
                System.out.println(selectedRow);
                if (selectedRow < 0) {
                    JOptionPane.showMessageDialog(userStockPanel, "Please select a stock on the left", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                int stockId = (int) model.getValueAt(selectedRow, 0);
                String tag = (String) model.getValueAt(selectedRow, 1);
                double stockPrice = stockListComponent.getSellPrice(tag);

                String num = textField1.getText();
                if (num.equals("")) {
                    JOptionPane.showMessageDialog(userStockPanel, "Please enter a number", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                int i = JOptionPane.showConfirmDialog(userStockView, "Sell stock at price " + stockPrice + "?", "Confirmation", JOptionPane.YES_NO_OPTION);
                if (i == 1) {
                    return;
                }
                stockService.sellStock(1, stockId, Integer.parseInt(num), stockPrice);
                reloadTable();
                userStockView.validate();
                userStockView.repaint();
            }
        });
    }

    public void reloadTable() {
        try {
            customerMoney = securityService.getCustomerMoney(clientId);
        } catch (SQLException e) {
            System.out.println("get customer money failed");
        }
        securityBalance.setText("Security Balance: " + customerMoney);
        int userId = 1;
        TableList userStock = stockService.getUserStockDataByUserId(userId);

        DefaultTableModel model = (DefaultTableModel) table1.getModel();
        Object[][] rowData = userStock.getRowData();
        model.setDataVector(rowData, userStock.getColumnsName());
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("UserStockView");
        frame.setContentPane(new UserStockView());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}