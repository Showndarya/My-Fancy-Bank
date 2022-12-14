package frontend;

import enums.AccountType;
import utilities.FancyBank;
import dto.UserAccount;

import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;

public class CheckingAccountInfoPanel extends AccountInfoPanel {


    /**
     * Get account type from back-end
     *
     * @return account type
     */
    @Override
    protected String getAccountType() {
        return "Checking Account: ";
    }

    /**
     * Get account number from back-end
     *
     * @return account number
     */
    @Override
    protected String getAccountNumber() {
        int accountNumber = accountService.getAccountId(FancyBank.getInstance().getUserId(), AccountType.Checking);
        int numberLength = String.valueOf(accountNumber).length();
        String result = String.join("", Collections.nCopies(ACCOUNT_NUMBER_LENGTH-numberLength, "0")) + accountNumber;
        return result;
    }

    /**
     * Get balances from back-end
     *
     * @return balances
     */
    @Override
    protected Hashtable<String, Double> getBalances() {
        int accountId = accountService.getAccountId(FancyBank.getInstance().getUserId(), AccountType.Checking);
        ArrayList<UserAccount> accounts;
        try{
            accounts = accountOperationService.getAccountsByIdWithBalance(accountId);
        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
        Hashtable<String, Double> balances =  new Hashtable<>();
        for(UserAccount userAccount: accounts){
            balances.put(userAccount.moneyType.getSymbol(), userAccount.amount);
        }
        return balances;
    }

    @Override
    protected void clickDeleteAccountButton(ActionEvent e) {
        // check whether there is no balance
        Hashtable<String, Double> balances = getBalances();
        boolean has = false;
        for(String key: balances.keySet()){
            if(balances.get(key) > 0){
                has = true;
                break;
            }
        }
        if(has){
            removeAccountErrorLabel.setVisible(true);
            repaint();
        }
        else{
            accountService.removeAccount(FancyBank.getInstance().getUserId(), AccountType.Checking);
        }
    }
}
