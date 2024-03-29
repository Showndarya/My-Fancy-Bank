package frontend.component;

import business_logic_layer.impl.CustomerServiceImpl;
import business_logic_layer.interfaces.CustomerService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchCustomerComponent extends JPanel {

    private JLabel searchLabel;
    private JTextField textField1;
    private JButton searchButton;
    private JPanel contentPanel;
    private JPanel searchPanel;
    private CustomerService customerService;

    public SearchCustomerComponent() {
        customerService = new CustomerServiceImpl();
        contentPanel.add(new CustomerComponent(""));
        add(searchPanel);
        searchButton.setActionCommand("search");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textField1.getText() == "") return;

                contentPanel.remove(0);

                contentPanel.add(new CustomerComponent(textField1.getText()));
                contentPanel.validate();
                contentPanel.repaint();
            }
        });
    }

    public static void main(String[] args) {
        JFrame jf = new JFrame();
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        jf.setContentPane(new SearchCustomerComponent());

        jf.pack();
        jf.setLocationRelativeTo(null);
        jf.setVisible(true);
    }

}
