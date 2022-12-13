package Frontend.component;

import BusinessLogicLayer.StockService;
import BusinessLogicLayer.impl.StockServiceImpl;
import dto.TableList;

import javax.swing.*;
import java.awt.*;


public class StockListComponent extends JScrollPane {

    private static StockListComponent stockListComponent;
    private StockService stockService;
    private Object[][] rowData;

    private JTable table;

    public static StockListComponent getInstance() {
        if (stockListComponent == null) {
            stockListComponent = new StockListComponent();
        }
        return stockListComponent;
    }

    public void reloadTable() {
        TableList stockList = stockService.getAllStock();
        rowData = stockList.getRowData();
        Object[] columnNames = stockList.getColumnsName();
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


//        JScrollPane scrollPane = new JScrollPane(table);

        setViewportView(table);
        setVisible(true);

    }


    private StockListComponent() {
        stockService = new StockServiceImpl();
        TableList stockList = stockService.getAllStock();
        Object[] columnNames = stockList.getColumnsName();
        rowData = stockList.getRowData();
//        Object[][] rowData = {{"Tesla","TSLA",194.66},{"Alphabet Class A","GOOGL",100.07}};
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


//        JScrollPane scrollPane = new JScrollPane(table);

        setViewportView(table);
        setVisible(true);


    }

    public String getStockTag() {
        int selectedRow = table.getSelectedRow();
        return selectedRow < 0 ? null : (String) rowData[selectedRow][1];
    }


    public static void main(String[] args) {
        JFrame jf = new JFrame();
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        jf.setContentPane(new StockListComponent());

        jf.pack();
        jf.setLocationRelativeTo(null);
        jf.setVisible(true);
    }
}
