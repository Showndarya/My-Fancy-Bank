package Frontend;

import BusinessLogicLayer.StockService;
import BusinessLogicLayer.impl.StockServiceImpl;
import Frontend.component.AddStockComponent;
import Frontend.component.StockListComponent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StockListView extends JPanel {
    private JPanel stockListView;
    private JPanel topPanel;
    private JPanel contentPanel;
    private JButton deleteButton;
    private JButton editButton;
    private JButton addButton;

    private StockService stockService;


    public static void main(String[] args) {
        JFrame frame = new JFrame("StockListView");
        frame.setContentPane(new StockListView());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public StockListView() {
        stockService = new StockServiceImpl();
        add(stockListView);

        contentPanel.add(StockListComponent.getInstance());
        addButton.setActionCommand("add");
        editButton.setActionCommand("edit");
        deleteButton.setActionCommand("delete");

//        addButton.addActionListener(new MyActionListener(contentPanel));
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                contentPanel.remove(0);
                contentPanel.add(new AddStockComponent(contentPanel));
                StockListComponent.getInstance().reloadTable();
                contentPanel.validate();
                contentPanel.repaint();
            }
        });
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String tag = StockListComponent.getInstance().getStockTag();
                System.out.println(tag);
                if (tag == null) {
                    System.out.println("Must select a row.");
                    JOptionPane.showMessageDialog(contentPanel, "Please select a data", "Warning", JOptionPane.WARNING_MESSAGE);
                } else {
                    contentPanel.remove(0);
                    contentPanel.add(new AddStockComponent(contentPanel, tag));
                    StockListComponent.getInstance().reloadTable();
                    contentPanel.validate();
                    contentPanel.repaint();
                }
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String tag = StockListComponent.getInstance().getStockTag();
                if (tag == null) {
                    JOptionPane.showMessageDialog(contentPanel, "Please select a data", "Warning", JOptionPane.WARNING_MESSAGE);

                }
                stockService.deleteStockByTag(tag);

                StockListComponent.getInstance().reloadTable();
                contentPanel.validate();
                contentPanel.repaint();
            }
        });
    }
}



