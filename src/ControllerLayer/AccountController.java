package ControllerLayer;

import BusinessLogicLayer.AccountOperationService;
import BusinessLogicLayer.impl.AccountOperationServiceImpl;
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
}
