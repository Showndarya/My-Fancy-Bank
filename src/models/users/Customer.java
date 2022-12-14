package models.users;

import enums.AccountType;
import models.account.Account;
import models.account.CheckingAccount;
import models.account.SavingsAccount;
import models.account.SecurityAccount;

import java.util.Hashtable;

/**
 * Customer class
 * extends: User
 */
public class Customer extends User{
    private Hashtable<AccountType, Account> accounts;
    // loan?

    /**
     * Construct an empty Customer
     */
    public Customer(){
        super();
    }

    /**
     * Construct a customer
     * @param id id of the customer
     * @param name name of the customer
     */
    public Customer(int id, String name, CheckingAccount checkingAccount, SavingsAccount savingsAccount, SecurityAccount securityAccount) {
        super(id, name);
        accounts = new Hashtable<>();
        accounts.put(AccountType.Checking, checkingAccount);
        accounts.put(AccountType.Savings, savingsAccount);
        accounts.put(AccountType.Security, securityAccount);
    }

    public Customer(int id, String name){
        super(id, name);
        accounts = new Hashtable<>();
    }

    /**
     * Set a type of account
     * @param account account object
     * @param accountType account type
     */
    public void setAccounts(Account account, AccountType accountType){
        accounts.put(accountType, account);
    }

    /**
     * Get a type of account
     * @param accountType account type
     * @return an account
     */
    public Account getAccount(AccountType accountType){
        return accounts.get(accountType);
    }
}
