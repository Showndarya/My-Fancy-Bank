package frontend.component;

import controller_layer.AccountController;
import dto.TableList;
import utilities.FancyBank;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class AccountTransactionsListComponent extends JScrollPane {
    private JPanel accountTransactionsView;
    private JTable transactions;
    private JButton deposit;
    private JButton withdraw;
    private AccountController controller;
    private Object[][] rowData;
    private static AccountTransactionsListComponent accountTransactionsListComponent;

    public static AccountTransactionsListComponent getInstance() {
        if (accountTransactionsListComponent == null) {
            try {
                accountTransactionsListComponent = new AccountTransactionsListComponent();
            }
            catch (SQLException e) {

            }
        }
        return accountTransactionsListComponent;
    }

    public static void main(String[] args) throws SQLException {
        JFrame frame = new JFrame("Account Transactions");
        frame.setContentPane(new AccountTransactionsListComponent());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public AccountTransactionsListComponent() throws SQLException {
        controller = new AccountController();
        TableList transactionList = controller.getAllTransactions(FancyBank.getInstance().getUserId());
        Object[] columnNames = transactionList.getColumnsName();
        rowData = transactionList.getRowData();
        transactions = new JTable(rowData, columnNames) {
            public boolean editCellAt(int row, int column, java.util.EventObject e) {
                return false;
            }
        };

        transactions.setForeground(Color.BLACK);
        transactions.setFont(new Font(null, Font.PLAIN, 14));
        transactions.setSelectionForeground(Color.DARK_GRAY);
        transactions.setSelectionBackground(Color.LIGHT_GRAY);
        transactions.setGridColor(Color.GRAY);
        transactions.getTableHeader().setFont(new Font(null, Font.BOLD, 14));
        transactions.getTableHeader().setForeground(Color.RED);
        transactions.getTableHeader().setResizingAllowed(false);
        transactions.getTableHeader().setReorderingAllowed(false);
        transactions.setRowHeight(30);
        transactions.getColumnModel().getColumn(0).setPreferredWidth(40);
        transactions.setPreferredScrollableViewportSize(new Dimension(400, 300));
        setViewportView(transactions);
        setVisible(true);
    }

    public void reloadTable() {
        controller = new AccountController();
        TableList transactionList = controller.getAllTransactions(FancyBank.getInstance().getUserId());
        rowData = transactionList.getRowData();
        Object[] columnNames = transactionList.getColumnsName();
        transactions = new JTable(rowData, columnNames) {
            public boolean editCellAt(int row, int column, java.util.EventObject e) {
                return false;
            }
        };


        transactions.setForeground(Color.BLACK);
        transactions.setFont(new Font(null, Font.PLAIN, 14));
        transactions.setSelectionForeground(Color.DARK_GRAY);
        transactions.setSelectionBackground(Color.LIGHT_GRAY);
        transactions.setGridColor(Color.GRAY);
        transactions.getTableHeader().setFont(new Font(null, Font.BOLD, 14));
        transactions.getTableHeader().setForeground(Color.RED);
        transactions.getTableHeader().setResizingAllowed(false);
        transactions.getTableHeader().setReorderingAllowed(false);
        transactions.setRowHeight(30);
        transactions.getColumnModel().getColumn(0).setPreferredWidth(40);
        transactions.setPreferredScrollableViewportSize(new Dimension(400, 300));
        setViewportView(transactions);
        setVisible(true);

    }
}
