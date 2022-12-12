package Models.Account;

import Enums.AccountType;

/**
 * Savings Account
 * extends: Account
 */
public class SavingsAccount extends Account {
    public SavingsAccount(int ownerId) {
        super(AccountType.Savings, ownerId);
    }

    public SavingsAccount(){
        super(AccountType.Savings);
    }

}
