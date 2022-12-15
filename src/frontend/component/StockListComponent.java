package frontend.component;

import business_logic_layer.interfaces.StockService;
import business_logic_layer.impl.StockServiceImpl;
import dto.TableList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Random;
import java.util.concurrent.TimeUnit;


public class StockListComponent extends JScrollPane {

    private static StockListComponent stockListComponent;
    private StockService stockService;
    private Object[][] rowData;

    private JTable table;

    private volatile boolean flag = true;

    private Random random;

    private DefaultTableModel model;


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
        model = (DefaultTableModel) table.getModel();
        model.setDataVector(rowData, columnNames);


    }


    private StockListComponent() {
        random = new Random();
        stockService = new StockServiceImpl();
        TableList stockList = stockService.getAllStock();
        Object[] columnNames = stockList.getColumnsName();
        rowData = stockList.getRowData();
//        Object[][] rowData = {{"Tesla","TSLA",194.66},{"Alphabet Class A","GOOGL",100.07}};
        table = new JTable();
        model = (DefaultTableModel) table.getModel();
        model.setDataVector(rowData, columnNames);


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
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (flag) {
                    try {
                        TimeUnit.SECONDS.sleep(5);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    int rowCount = model.getRowCount();

                    for (int i = 0; i < rowCount; i++) {
                        int i1 = random.nextInt(20);
                        double percentage = (double) i1 / 1000;
                        System.out.println(percentage);
                        if (i1 % 2 == 0) {
                            double v = (double) model.getValueAt(i, 2) * (1 + percentage);
                            model.setValueAt(Math.round(v * 100.0) / 100.0, i, 2);
                        } else {
                            double v = (double) model.getValueAt(i, 2) * (1 - percentage);
                            model.setValueAt(Math.round(v * 100.0) / 100.0, i, 2);

                        }
                    }

                }
            }
        }).start();


    }

    public String getStockTag() {
        int selectedRow = table.getSelectedRow();
        return selectedRow < 0 ? null : (String) rowData[selectedRow][1];
    }

    public double getSelectedStockPrice() {
        int selectedRow = table.getSelectedRow();
        double stockPrice = (double) model.getValueAt(selectedRow, 2);
        return stockPrice;
    }

    public double getSellPrice(String tag) {
        int rowCount = model.getRowCount();
        for (int i = 0; i < rowCount; i++) {
            String str = (String) model.getValueAt(i, 1);
            if (str.equals(tag)) {
                return (double) model.getValueAt(i, 2);
            }
        }
        return 0;
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
