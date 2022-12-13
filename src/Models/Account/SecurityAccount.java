package Models.Account;

import Enums.AccountType;

/**
 * Security Account
 * extends: Account
 */
public class SecurityAccount extends Account {
    public SecurityAccount() {
        super(AccountType.Security);
    }

    public SecurityAccount(int ownerId){
        super(AccountType.Security, ownerId);
    }

}
