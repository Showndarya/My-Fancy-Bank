package Frontend;

import ControllerLayer.AccountController;
import ControllerLayer.TransactionController;
import Enums.TransactionType;
import Models.MoneyType;
import Utilities.Tuple;
import dto.UserAccount;

import javax.swing.*;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
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
    private JButton submitButton;

    private static DepositTransaction depositTransaction = new DepositTransaction();

    public DepositTransaction() {
        backButton.addActionListener(e -> ManageAccountTransactions.open());
        currencySelect.addPopupMenuListener(new PopupMenuListener() {
            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
                TransactionController controller = new TransactionController();
                AccountController accountController = new AccountController();
                ArrayList<MoneyType> moneyTypes;
                ArrayList<UserAccount> userAccounts;
                if(depositTransaction.currencySelect.getItemCount() == 0) {
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
    }

    public static void open(TransactionType type) throws SQLException {
        //JframeSingleton.getInstance().removePanel();
        switch (type) {
            case Deposit:
                depositTransaction.accountDetailsPanel.setVisible(false);
                depositTransaction.depositLabel.setText("Make a deposit");
                JframeSingleton.getInstance().addPanel(depositTransaction.depositPanel);
                break;
            case Withdraw:
                depositTransaction.accountDetailsPanel.setVisible(false);
                depositTransaction.depositLabel.setText("Withdraw money");
                JframeSingleton.getInstance().addPanel(depositTransaction.depositPanel);
                break;
        }


    }
}
