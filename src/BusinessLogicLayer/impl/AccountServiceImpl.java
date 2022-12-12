package BusinessLogicLayer.impl;

import BusinessLogicLayer.AccountService;
import Enums.AccountType;
import Models.Account.Account;

public class AccountServiceImpl implements AccountService {
    @Override
    public Account getAccount(int ownerId, AccountType type) {
        return null;
    }

    @Override
    public Account hasAccount(int ownerId, AccountType type) {
        return null;
    }

    @Override
    public Account removeAccount(int ownerId, AccountType type) {
        return null;
    }

    @Override
    public boolean createAccount(int ownerId, AccountType type) {
        return false;
    }
}
