package models.account;

import enums.AccountType;

/**
 * Security Account
 * extends: Account
 */
public class SecurityAccount extends Account {
    public SecurityAccount() {
        super(AccountType.Security);
    }

    public SecurityAccount(int id, int ownerId){
        super(AccountType.Security, id, ownerId);
    }

}
