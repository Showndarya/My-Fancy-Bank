package frontend;

import business_logic_layer.interfaces.ManagerService;
import frontend.component.CurrentTransactionComponent;
import frontend.component.CustomerComponent;
import frontend.component.LoanTransactionComponent;

import javax.swing.*;

public class ManagerView extends JPanel{
    private ManagerService managerService;
    private JPanel managerView;
    private JTabbedPane tabbedPane;
    private JPanel allCustomer;
    private JPanel customerWithLoan;
    private JPanel searchCustomer;
    private JScrollPane dailyReport;
    private JPanel stock;
    private JPanel dailyReportContent;

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
        dailyReportContent.add(new JLabel("Loan Transaction"));
        dailyReportContent.add(new LoanTransactionComponent());
        dailyReportContent.add(new JLabel("Current Transaction"));
        dailyReportContent.add(CurrentTransactionComponent.getInstance());
        stock.add(new StockListView());
        setVisible(true);
    }


}
