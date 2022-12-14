package Frontend;

import ControllerLayer.AccountController;
import ControllerLayer.TransactionController;
import Enums.TransactionType;
import Frontend.component.AccountTransactionsListComponent;
import Models.MoneyType;
import Utilities.Tuple;
import dto.UserAccount;

import javax.swing.*;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class MakeTransaction extends JPanel {
    private JPanel makeTransaction;
    private JComboBox accountSelect;
    private JComboBox currencySelect;
    private JLabel depositLabel;
    private JButton submitButton;

    private TransactionType type;

    TransactionController controller = new TransactionController();
    AccountController accountController = new AccountController();

    public MakeTransaction(TransactionType type, JPanel jPanel) {
        type = type;

        add(makeTransaction);
        submitButton.setActionCommand("submit");
        switch (type) {
            case Deposit:
                depositLabel.setText("Make a deposit");
                break;
            case Withdraw:
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
