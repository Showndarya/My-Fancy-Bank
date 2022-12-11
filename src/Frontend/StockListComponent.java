package Frontend;

import BusinessLogicLayer.StockService;
import BusinessLogicLayer.impl.StockServiceImpl;
import dto.TableList;

import javax.swing.*;
import java.awt.*;


public class StockListComponent {
    private StockService stockService;


    public StockListComponent() {
        stockService = new StockServiceImpl();
    }

    public JScrollPane loadFrame(){
        TableList tableList = stockService.getAllStock();





        Object[] columnNames = tableList.getColumnsName();


        Object[][] rowData = tableList.getRowData();
//        Object[][] rowData = {{"Tesla","TSLA",194.66},{"Alphabet Class A","GOOGL",100.07}};


        JTable table = new JTable(rowData, columnNames);


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

        JScrollPane scrollPane = new JScrollPane(table);


        return scrollPane;


    }

    public static void main(String[] args) {
        JFrame jf = new JFrame();
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JScrollPane jScrollPane = new StockListComponent().loadFrame();
        jf.setContentPane(jScrollPane);

        jf.pack();
        jf.setLocationRelativeTo(null);
        jf.setVisible(true);
    }
}
