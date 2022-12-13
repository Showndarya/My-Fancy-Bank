package Frontend.component;

import BusinessLogicLayer.ManagerService;
import BusinessLogicLayer.impl.ManagerServiceImpl;
import dto.TableList;
import javafx.scene.control.Tab;

import javax.swing.*;
import java.awt.*;

public class CustomerComponent extends JScrollPane{
    private CustomerComponent customerComponent;
    private ManagerService managerService;
    private Object[][] rowData;
    private int type;
    private JTable table;

    public CustomerComponent() {
        managerService = new ManagerServiceImpl();
        TableList customerList = managerService.getAllCustomers();
        Object[] columnNames = customerList.getColumnsName();
        rowData = customerList.getRowData();
        table = new JTable(rowData, columnNames) {
            public boolean editCellAt(int row, int column, java.util.EventObject e) {
                return false;
            }
        };

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

    public CustomerComponent(int type) {
        this.type = type;
        managerService = new ManagerServiceImpl();
        TableList customerList = managerService.getCustomersWithLoan();
        Object[] columnNames = customerList.getColumnsName();
        rowData = customerList.getRowData();
        table = new JTable(rowData, columnNames) {
            public boolean editCellAt(int row, int column, java.util.EventObject e) {
                return false;
            }
        };

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

    public static void main(String[] args) {
        JFrame jf = new JFrame();
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        jf.setContentPane(new CustomerComponent());

        jf.pack();
        jf.setLocationRelativeTo(null);
        jf.setVisible(true);
    }
}
