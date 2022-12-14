package frontend.component;

import business_logic_layer.interfaces.ManagerService;
import business_logic_layer.impl.ManagerServiceImpl;
import dto.TableList;

import javax.swing.*;
import java.awt.*;

public class CurrentTransactionComponent  extends JScrollPane {
    private ManagerService managerService;
    private Object[][] rowData;
    private JTable table;
    private static CurrentTransactionComponent currentTransactionComponent;

    public static CurrentTransactionComponent getInstance() {
        if (currentTransactionComponent == null) {
            currentTransactionComponent = new CurrentTransactionComponent();
        }
        return currentTransactionComponent;
    }

    public CurrentTransactionComponent() {
        managerService = new ManagerServiceImpl();
        TableList list = managerService.getDailyCurrentTransaction();
        Object[] columnNames = list.getColumnsName();
        rowData = list.getRowData();
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

    public static void main(String[] args) {
        JFrame jf = new JFrame();
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jf.setContentPane(CurrentTransactionComponent.getInstance());

        jf.pack();
        jf.setLocationRelativeTo(null);
        jf.setVisible(true);
    }
}
