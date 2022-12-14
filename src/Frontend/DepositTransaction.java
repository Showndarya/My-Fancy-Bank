package Frontend;

import ControllerLayer.AccountController;
import ControllerLayer.TransactionController;
import Enums.TransactionType;
import Frontend.component.AccountTransactionsListComponent;
import Frontend.component.StockListComponent;
import Models.MoneyType;
import Utilities.Tuple;
import dto.UserAccount;

import javax.swing.*;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class DepositTransaction extends JPanel{
    private JPanel depositPanel;
    private JComboBox accountSelect;
    private JTextField amount;
    private JComboBox currencySelect;
    private JLabel depositLabel;
    private JPanel accountDetailsPanel;
    private JPanel backPanel;
    private JButton submitButton;

    private TransactionType type;

    TransactionController controller = new TransactionController();
    AccountController accountController = new AccountController();

    public DepositTransaction(TransactionType type, JPanel jPanel) {
        type = type;

        add(depositPanel);
        submitButton.setActionCommand("submit");
        switch (type) {
            case Deposit:
                accountDetailsPanel.setVisible(false);
                depositLabel.setText("Make a deposit");
                break;
            case Withdraw:
                accountDetailsPanel.setVisible(false);
                depositLabel.setText("Withdraw money");
                break;
        }
        currencySelect.addPopupMenuListener(new PopupMenuListener() {
            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
                ArrayList<MoneyType> moneyTypes;
                ArrayList<UserAccount> userAccounts;
                if(currencySelect.getItemCount() == 0) {
                    try {
                        moneyTypes = controller.getAllmoneyTypes();
                        userAccounts = accountController.getAccountsByIdWithBalance(2);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }

                    for(MoneyType moneyType: moneyTypes)
                        currencySelect.addItem(
                                new Tuple(moneyType.getType()+"("+moneyType.getSymbol()+")", moneyType.getId())
                        );

                    for(UserAccount userAccount: userAccounts)
                        accountSelect.addItem(
                                new Tuple(userAccount.userId+" "+userAccount.accountId, userAccount.accountId)
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
        submitButton.addActionListener(e -> {
            AccountTransactionsListComponent.getInstance().reloadTable();
            jPanel.remove(0);
            jPanel.add(AccountTransactionsListComponent.getInstance());
            jPanel.validate();
            jPanel.repaint();
        });

        setVisible(true);
    }
}
