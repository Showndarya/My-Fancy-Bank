package Frontend;

import BusinessLogicLayer.ManagerService;
import Frontend.component.CustomerComponent;
import Frontend.component.LoanTransactionComponent;
import Frontend.component.LoanTransactionComponent;

import javax.swing.*;

public class ManagerView extends JPanel{
    private ManagerService managerService;
    private JPanel managerView;
    private JTabbedPane tabbedPane;
    private JPanel allCustomer;
    private JPanel customerWithLoan;
    private JPanel searchCustomer;
    private JPanel dailyReport;
    private JPanel stock;

    public static void main(String[] args) {
        JFrame frame = new JFrame("managerView");
        frame.setContentPane(new ManagerView());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public ManagerView() {
        add(managerView);
        allCustomer.add(new CustomerComponent());
        customerWithLoan.add(new CustomerComponent(1));
        dailyReport.add(new JLabel("Loan Transaction"));
        dailyReport.add(new LoanTransactionComponent());
        stock.add(new StockListView());
        setVisible(true);
    }


}
