package models.account;

import enums.AccountType;

/**
 * Checking Account
 * extends: Account
 */
public class CheckingAccount extends Account {
    public CheckingAccount() {
        super(AccountType.Checking);
    }

    public CheckingAccount(int id, int ownerId){
        super(AccountType.Checking, id, ownerId);
    }

}
