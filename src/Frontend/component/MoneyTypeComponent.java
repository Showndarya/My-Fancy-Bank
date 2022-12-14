package Frontend.component;

import BusinessLogicLayer.MoneyTypeService;
import BusinessLogicLayer.impl.MoneyTypeServiceImpl;
import dto.TableList;

import javax.swing.*;
import java.awt.*;

public class MoneyTypeComponent extends JScrollPane {
    private MoneyTypeService moneyTypeService;
    private Object[][] rowData;
    private JTable table;

    public MoneyTypeComponent() {
        moneyTypeService = new MoneyTypeServiceImpl();
        TableList tableList = moneyTypeService.getMoneyTypes();
        Object[] columnNames = tableList.getColumnsName();
        rowData = tableList.getRowData();
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


        setViewportView(table);
        setVisible(true);
    }

    public static void main(String[] args) {
        JFrame jf = new JFrame();
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jf.setContentPane(new MoneyTypeComponent());

        jf.pack();
        jf.setLocationRelativeTo(null);
        jf.setVisible(true);
    }
}
