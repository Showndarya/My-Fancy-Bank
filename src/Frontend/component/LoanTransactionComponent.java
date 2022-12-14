package Frontend.component;

import BusinessLogicLayer.LoanTransactionService;
import BusinessLogicLayer.ManagerService;
import BusinessLogicLayer.impl.LoanTransactionServiceImpl;
import BusinessLogicLayer.impl.ManagerServiceImpl;
import Models.Users.Customer;
import dto.TableList;

import javax.swing.*;
import java.awt.*;

public class LoanTransactionComponent extends JScrollPane{
    private ManagerService managerService;
    private LoanTransactionService loanTransactionService;
    private Object[][] rowData;
    private JTable table;
    private Customer customer;

    private static LoanTransactionComponent loanTransactionComponent;

    public LoanTransactionComponent() {
        managerService = new ManagerServiceImpl();
        TableList customerList = managerService.getDailyLoanTransaction();
        Object[] columnNames = customerList.getColumnsName();
        rowData = customerList.getRowData();
        table = new JTable(rowData, columnNames) {
            public boolean editCellAt(int row, int column, java.util.EventObject e) {
                return false;
            }
        };
        setTable();
    }

    public LoanTransactionComponent(Customer customer) {
        this.customer = customer;
        loanTransactionService = new LoanTransactionServiceImpl();
        TableList loanList = loanTransactionService.getCustomerLoan(customer);
        Object[] columnNames = loanList.getColumnsName();
        rowData = loanList.getRowData();
        table = new JTable(rowData, columnNames) {
            public boolean editCellAt(int row, int column, java.util.EventObject e) {
                return false;
            }
        };
        setTable();
    }

    public void setTable() {
        table.setForeground(Color.BLACK);
        table.setFont(new Font(null, Font.PLAIN, 14));
        table.setSelectionForeground(Color.DARK_GRAY);
        table.setSelectionBackground(Color.LIGHT_GRAY);
        table.setGridColor(Color.GRAY);


        table.getTableHeader().setFont(new Font(null, Font.BOLD, 14));
        table.getTableHeader().setForeground(Color.RED);
        table.getTableHeader().setResizingAllowed(false);
        table.getTableHeader().setReorderingAllowed(false);


        table.setRowHeight(30);


        table.getColumnModel().getColumn(0).setPreferredWidth(40);

        table.setPreferredScrollableViewportSize(new Dimension(400, 300));


        setViewportView(table);
        setVisible(true);
    }

    public void reloadTable(Customer customer) {
        TableList loanList = loanTransactionService.getCustomerLoan(customer);
        Object[] columnNames = loanList.getColumnsName();
        rowData = loanList.getRowData();
        table = new JTable(rowData, columnNames) {
            public boolean editCellAt(int row, int column, java.util.EventObject e) {
                return false;
            }
        };
        setTable();
    }

    public static void main(String[] args) {
        JFrame jf = new JFrame();
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Customer customer = new Customer(1, "name");
        jf.setContentPane(new LoanTransactionComponent(customer));

        jf.pack();
        jf.setLocationRelativeTo(null);
        jf.setVisible(true);
    }

    public static LoanTransactionComponent getInstance(Customer customer) {
        if (loanTransactionComponent == null) {
            loanTransactionComponent = new LoanTransactionComponent(customer);
        }
        return loanTransactionComponent;
    }
}