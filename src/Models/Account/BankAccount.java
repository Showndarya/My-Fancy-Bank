package Models.Account;

import Enums.AccountType;

/**
 * Bank Account which is a special kind of account only for bank
 * extends: Account
 */
public class BankAccount extends Account {
    public BankAccount(AccountType type) {
        super(type);
    }
}
