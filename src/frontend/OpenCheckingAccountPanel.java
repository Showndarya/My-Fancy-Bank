package frontend;

import dto.UserAccount;
import enums.AccountType;
import enums.TransactionType;
import utilities.BaseDao;
import utilities.FancyBank;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class OpenCheckingAccountPanel extends OpenAccountPanel{
    @Override
    protected String getPrompt() {
        return "Open Checking Account ";
    }

    @Override
    protected void clickOpenButton(ActionEvent e) {
        double initialMoney = 0;
        try{
            initialMoney = Integer.parseInt(initialMoneyTextField.getText());
        } catch (NumberFormatException nfe){
            nfe.printStackTrace();
        }
        if(initialMoney < MINIMUM_INITIAL_MONEY){
            JOptionPane.showMessageDialog(this, "Initial money must be larger than 100", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int selectedNum = 0;
        boolean usdSelected = false;
        ArrayList<Integer> types = new ArrayList<>();
        for(int key: moneyTypeBoxes.keySet()){
            if(moneyTypeBoxes.get(key).isSelected()){
                if(key == USD_TYPE){
                    usdSelected = true;
                }
                selectedNum++;
                types.add(key);
            }
        }
        if(!usdSelected){
            JOptionPane.showMessageDialog(this, "USD must be selected", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if(selectedNum < MINIMUM_MONEY_TYPE_NUM){
            JOptionPane.showMessageDialog(this, "You must select at least three types of money", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        // create account
        if(accountService.createAccount(FancyBank.getInstance().getUserId(), AccountType.Checking, types)){
            int accountId = accountService.getAccountId(FancyBank.getInstance().getUserId(), AccountType.Checking);
            // deposit money
            accountOperationService.changeBalance(TransactionType.Deposit, accountId, initialMoney-FancyBank.OPEN_ACCOUNT_MONEY, USD_TYPE);
            MainFrame.getInstance().setPanel(new MenuPanel());
        }
        else{
            JOptionPane.showMessageDialog(this, "Fail to add checking account", "Warning", JOptionPane.WARNING_MESSAGE);

        }
    }
}
