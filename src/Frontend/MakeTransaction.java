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

    public MakeTransaction(TransactionType transactionType, JPanel jPanel, ArrayList<MoneyType> moneyTypes, ArrayList<UserAccount> userAccounts) {
        type = transactionType;

        add(makeTransaction);
        submitButton.setActionCommand("submit");
        if(currencySelect.getItemCount() == 0) {
            for(MoneyType moneyType: moneyTypes)
                currencySelect.addItem(
                        new Tuple(moneyType.getType()+"("+moneyType.getSymbol()+")", moneyType.getId())
                );

            for(UserAccount userAccount: userAccounts)
                accountSelect.addItem(
                        new Tuple(userAccount.userId+" "+userAccount.accountId, userAccount.accountId)
                );
        }
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
