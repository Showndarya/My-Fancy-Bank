package Frontend.component;

import Enums.TransactionType;
import Models.MoneyType;
import Utilities.Tuple;
import dto.UserAccount;

import javax.swing.*;
import java.util.ArrayList;

public class WithdrawTransaction extends JPanel{
    private JLabel withdrawLabel;
    private JComboBox accountSelect;
    private JComboBox currencySelect;
    private JButton submitButton;
    private JPanel withdrawTransactionPanel;
    private TransactionType type;

    public WithdrawTransaction(TransactionType transactionType, JPanel jPanel, ArrayList<MoneyType> moneyTypes, ArrayList<UserAccount> userAccounts) {
        type = transactionType;

        add(withdrawTransactionPanel);
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
