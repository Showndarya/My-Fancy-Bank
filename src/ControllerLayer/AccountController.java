package ControllerLayer;

import BusinessLogicLayer.AccountOperationService;
import BusinessLogicLayer.impl.AccountOperationServiceImpl;
import Enums.TransactionType;
import Models.MoneyType;
import Models.Transaction.Transaction;
import dto.TableList;
import dto.UserAccount;

import java.sql.SQLException;
import java.util.ArrayList;

public class AccountController {

    private AccountOperationService accountOperationService;

    public AccountController() {
        accountOperationService = new AccountOperationServiceImpl();
    }

    public ArrayList<UserAccount> getAccountsByIdWithBalance(int accountId) throws SQLException {
        return accountOperationService.getAccountsByIdWithBalance(accountId);
    }

    public TableList getAllTransactions(int userId) {
        return accountOperationService.getAllTransactions(userId);
    }

    public Boolean addTransaction(int userId, Transaction transaction) { return accountOperationService.addTransaction(userId, transaction);}

    public Boolean changeBalance(TransactionType type, int accountId, double amount, int moneyType) throws SQLException {
        return accountOperationService.changeBalance(type,accountId, amount, moneyType);
    }

    public double getBalance(int accountId, int moneyType) throws SQLException {
        return accountOperationService.getBalance(accountId, moneyType);
    }

}
