package Frontend.component;

import BusinessLogicLayer.StockService;
import BusinessLogicLayer.impl.StockServiceImpl;
import Models.Stock;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddStockComponent extends JPanel {
    private JLabel label;
    private JTextField textField1;
    private JTextField textField2;
    private JLabel label1;
    private JTextField textField3;
    private JButton saveButton;
    private JButton cancelButton;

    private StockService stockService;


    private JPanel buttonPanel;
    private JPanel addStockPanel;


    public AddStockComponent(JPanel jPanel) {
        stockService = new StockServiceImpl();
        add(addStockPanel);
        saveButton.setActionCommand("save");
        cancelButton.setActionCommand("cancel");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String name = textField1.getText();
                String tag = textField2.getText();
                String price = textField3.getText();
                stockService.addStock(name, tag, Double.parseDouble(price));
                StockListComponent.getInstance().reloadTable();
                jPanel.remove(0);
                jPanel.add(StockListComponent.getInstance());
                jPanel.validate();
                jPanel.repaint();
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                jPanel.remove(0);
                jPanel.add(StockListComponent.getInstance());
                jPanel.validate();
                jPanel.repaint();

            }
        });
        setVisible(true);
    }

    public AddStockComponent(JPanel jPanel, String tag) {
        stockService = new StockServiceImpl();
        add(addStockPanel);
        saveButton.setActionCommand("save");
        cancelButton.setActionCommand("cancel");
        Stock stock = stockService.getStockByTag(tag);
        textField1.setText(stock.getName());
        textField2.setText(stock.getTag());
        textField3.setText(stock.getPrice() + "");
        textField1.setEnabled(false);
        textField2.setEnabled(false);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String tag = textField2.getText();
                String price = textField3.getText();
                stockService.updateStockPriceByTag(tag, Double.parseDouble(price));
                StockListComponent.getInstance().reloadTable();
                jPanel.remove(0);
                jPanel.add(StockListComponent.getInstance());
                jPanel.validate();
                jPanel.repaint();
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                jPanel.remove(0);
                jPanel.add(StockListComponent.getInstance());
                jPanel.validate();
                jPanel.repaint();

            }
        });
        setVisible(true);
    }


}


