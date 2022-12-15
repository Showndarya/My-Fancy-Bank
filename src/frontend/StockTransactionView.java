package frontend;

import business_logic_layer.interfaces.StockTransactionService;
import business_logic_layer.impl.StockTransactionServiceImpl;
import dto.TableList;
import utilities.FancyBank;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StockTransactionView extends JPanel {
    private JPanel panel1;
    private JScrollPane jScrollPanel;
    private JPanel topPanel;
    private JTextField textField3;
    private JLabel dash;
    private JTextField textField2;
    private JTextField textField1;
    private JLabel dash1;
    private JTable table;
    private JButton button;

    private int clientId;

    private StockTransactionService stockTransactionService;

    public StockTransactionView() {
        clientId = FancyBank.getInstance().getUserId();

        stockTransactionService = new StockTransactionServiceImpl();
        add(panel1);

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        Object[] columnName = new Object[]{"Name", "Tag", "Price", "Transaction Type", "Number of share"};

        TableList transactionDetails = stockTransactionService.getTransactionDetails(clientId);
        model.setDataVector(transactionDetails.getRowData(), transactionDetails.getColumnsName());


        table.setDefaultEditor(Object.class, null);
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

        table.setPreferredScrollableViewportSize(new Dimension(800, 300));

        jScrollPanel.setViewportView(table);
        setVisible(true);


        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String text = textField1.getText();
                String text1 = textField2.getText();
                String text2 = textField3.getText();
                if (!text.matches("\\d+") || !text1.matches("\\d+") || !text2.matches("\\d+")) {
                    System.out.println("Must enter right date");
                    JOptionPane.showMessageDialog(panel1, "Please the right date", "Warning", JOptionPane.WARNING_MESSAGE);

                    return;
                }

                DefaultTableModel model = (DefaultTableModel) table.getModel();
                TableList tableList = stockTransactionService.getTransactionDetails(clientId, textField1.getText() + "-" + textField2.getText() + "-" + textField3.getText());
                model.setDataVector(tableList.getRowData(), tableList.getColumnsName());
                jScrollPanel.validate();
                jScrollPanel.repaint();

            }
        });
    }

    public static void main(String[] args) {
        FancyBank.getInstance().setUserId(1);
        StockTransactionView stockTransactionView = new StockTransactionView();
        JFrame frame = new JFrame("UserStockView");
        frame.setContentPane(stockTransactionView);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }
}
