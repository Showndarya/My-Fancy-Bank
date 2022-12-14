package models.account;

import enums.AccountType;

/**
 * Savings Account
 * extends: Account
 */
public class SavingsAccount extends Account {
    public SavingsAccount(int id, int ownerId) {
        super(AccountType.Savings, id, ownerId);
    }

    public SavingsAccount(){
        super(AccountType.Savings);
    }

}
