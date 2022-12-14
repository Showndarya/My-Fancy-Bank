package ControllerLayer;

import BusinessLogicLayer.AccountOperationService;
import BusinessLogicLayer.impl.AccountOperationServiceImpl;
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
}
