package frontend.component;

import business_logic_layer.impl.MoneyTypeServiceImpl;
import business_logic_layer.interfaces.LoanTransactionService;
import business_logic_layer.interfaces.ManagerService;
import business_logic_layer.impl.LoanTransactionServiceImpl;
import business_logic_layer.impl.ManagerServiceImpl;
import business_logic_layer.interfaces.MoneyTypeService;
import models.transaction.Collateral;
import models.transaction.LoanTransaction;
import models.transaction.MoneyType;
import models.users.Customer;
import dto.TableList;

import javax.swing.*;
import java.awt.*;

public class LoanTransactionComponent extends JScrollPane{
    private ManagerService managerService;
    private LoanTransactionService loanTransactionService;
    private Object[][] rowData;
    private JTable table;
    private Customer customer;

    private MoneyTypeService moneyTypeService = new MoneyTypeServiceImpl();
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


        table.getColumnModel().getColumn(0).setPreferredWidth(50);

        table.setPreferredScrollableViewportSize(new Dimension(600, 300));


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

    // select loan transaction from selected row
    public LoanTransaction getLoanTransaction() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow < 0) return null;
        Collateral collateral = new Collateral((Integer) rowData[selectedRow][0],
                (String) rowData[selectedRow][1]);
        MoneyType moneyType = new MoneyType(moneyTypeService.getMoneyTypeIdByType((String) rowData[selectedRow][3]));
        collateral.setMoneyType(moneyType);
        return new LoanTransaction(collateral, customer,
                (Double) rowData[selectedRow][2]);
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