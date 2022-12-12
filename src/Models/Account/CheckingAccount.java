package Models.Account;

import Enums.AccountType;

/**
 * Checking Account
 * extends: Account
 */
public class CheckingAccount extends Account {
    public CheckingAccount() {
        super(AccountType.Checking);
    }

    public CheckingAccount(int ownerId){
        super(AccountType.Checking);
    }

}
