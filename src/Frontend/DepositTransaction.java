package Frontend;

import ControllerLayer.TransactionController;
import Models.MoneyType;
import Utilities.Tuple;

import javax.swing.*;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.sql.SQLException;
import java.util.ArrayList;

public class DepositTransaction {
    private JPanel depositPanel;
    private JComboBox accountSelect;
    private JTextField amount;
    private JComboBox currencySelect;
    private JButton backButton;
    private JLabel depositLabel;
    private JPanel accountDetailsPanel;
    private JPanel backPanel;

    private static DepositTransaction depositTransaction = new DepositTransaction();

    public DepositTransaction() {
        backButton.addActionListener(e -> ManageAccountTransactions.open());
        currencySelect.addPopupMenuListener(new PopupMenuListener() {
            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
                TransactionController controller = new TransactionController();
                ArrayList<MoneyType> moneyTypes;
                if(depositTransaction.currencySelect.getItemCount() == 0) {
                    try {
                        moneyTypes = controller.getAllmoneyTypes();
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                    for(MoneyType moneyType: moneyTypes)
                        depositTransaction.currencySelect.addItem(
                                new Tuple(moneyType.getType()+"("+moneyType.getSymbol()+")", moneyType.getId())
                        );
                }
            }

            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {

            }

            @Override
            public void popupMenuCanceled(PopupMenuEvent e) {

            }
        });
    }

    public static void open() throws SQLException {
        //JframeSingleton.getInstance().removePanel();
        depositTransaction.accountDetailsPanel.setVisible(false);
        JframeSingleton.getInstance().addPanel(depositTransaction.depositPanel);


    }
}
