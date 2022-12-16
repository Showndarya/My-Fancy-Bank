package frontend;

import business_logic_layer.interfaces.SecurityService;
import business_logic_layer.interfaces.StockService;
import business_logic_layer.impl.SecurityServiceImpl;
import business_logic_layer.impl.StockServiceImpl;
import frontend.component.StockListComponent;
import models.transaction.Stock;
import dto.TableList;
import utilities.FancyBank;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * used by user to trade stock
 */
public class UserStockView extends JPanel {
    private JPanel rightPanel;
    private JPanel userInfoPanel;
    private JScrollPane userStockPanel;
    private JPanel leftPanel;
    private JPanel userStockView;
    private StockService stockService;
    private JLabel securityBalance;
    private JTable table1;
    private JLabel stockMarketLabel;
    private JPanel stockListPanel;
    private JButton btn1;
    private JButton btn2;
    private JPanel bottomPanel;
    private JTextField textField1;
    private JLabel unrealizedProfit;

    private SecurityService securityService;

    private double customerMoney;

    private int clientId;

    private DefaultTableModel model;


    public UserStockView() {
        clientId = FancyBank.getInstance().getUserId();
        securityService = new SecurityServiceImpl();
        stockService = new StockServiceImpl();
        add(userStockView);
        customerMoney = 0;
        try {
            customerMoney = securityService.getCustomerMoney(clientId);
        } catch (SQLException e) {
            System.out.println("get customer money failed");
        }
        StockListComponent stockListComponent = StockListComponent.getInstance();
        stockListPanel.add(stockListComponent);
        securityBalance.setText("Security Balance: " + customerMoney);
        textField1.setPreferredSize(new Dimension(130, 25));
        btn1.setPreferredSize(new Dimension(100, 25));
        btn2.setPreferredSize(new Dimension(100, 25));
        TableList userStock = stockService.getUserStockDataByUserId(clientId);

        model = (DefaultTableModel) table1.getModel();
        Object[][] rowData = userStock.getRowData();
        model.setDataVector(rowData, userStock.getColumnsName());

        unrealizedProfit.setText("Estimate unrealized profit: " + calculateUnrealizedProfit() + "");

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
                boolean b = stockService.buyStock(clientId, stock.getId(), Integer.parseInt(num), stockPrice);
                if (!b) {
                    JOptionPane.showMessageDialog(userStockView, "No enough monet in savings account", "Warning", JOptionPane.WARNING_MESSAGE);
                }
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
                boolean b = stockService.sellStock(clientId, stockId, Integer.parseInt(num), stockPrice);
                if (!b) {
                    JOptionPane.showMessageDialog(userStockView, "No enough monet in savings account", "Warning", JOptionPane.WARNING_MESSAGE);
                }
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
        TableList userStock = stockService.getUserStockDataByUserId(clientId);

        DefaultTableModel model = (DefaultTableModel) table1.getModel();
        Object[][] rowData = userStock.getRowData();
        model.setDataVector(rowData, userStock.getColumnsName());
        unrealizedProfit.setText("Estimate unrealized profit: " + calculateUnrealizedProfit() + "");

    }

    private double calculateUnrealizedProfit() {
        int rowCount = model.getRowCount();
        double result = 0;
        for (int i = 0; i < rowCount; i++) {
            result += (double) model.getValueAt(i, 2) * (int) model.getValueAt(i, 3);
        }
        result = Math.round(result * 100.00) / 100.00;
        return result;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("UserStockView");
        frame.setContentPane(new UserStockView());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}
