package Models.Account;

/**
 * Bank Account which is a special kind of account only for bank
 * extends: Account
 */
public class BankAccount extends Account {
    public BankAccount(Account.Type type) {
        super(type);
    }
}
