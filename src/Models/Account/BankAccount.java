package Models.Account;

import Enums.AccountType;

/**
 * Bank Account which is a special kind of account only for bank
 * extends: Account
 */
public class BankAccount extends Account {
    public BankAccount() {
        super(AccountType.Bank, 0, 0);
    }
}
