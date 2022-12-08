package Models.Account;

import Enums.AccountType;

/**
 * Savings Account
 * extends: Account
 */
public class SavingsAccount extends Account {
    public SavingsAccount() {
        super(AccountType.Savings);
    }
}
