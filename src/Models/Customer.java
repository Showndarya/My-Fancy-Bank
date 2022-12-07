package Models;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * Customer class
 * extends: User
 */
public class Customer extends User{
    private Hashtable<Account.Type, ArrayList<Account>> accounts;

    /**
     * Constructor of class Customer, which will create a customer from database
     * @param name name
     */
    public Customer(String name){
        super(name);
    }

    /**
     * Constructor of class Customer, which will create an empty customer
     * @param id id of the customer
     * @param name name of the customer
     */
    public Customer(String id, String name) {
        super(id, name);
        accounts = new Hashtable<>();
        accounts.put(Account.Type.Checking, new ArrayList<>());
        accounts.put(Account.Type.Savings, new ArrayList<>());
        accounts.put(Account.Type.Security, new ArrayList<>());
    }

    @Override
    protected boolean InitializeFromDatabase(String name) {
        // Initialize accounts
        accounts = new Hashtable<>();
        accounts.put(Account.Type.Checking, new ArrayList<>());
        accounts.put(Account.Type.Savings, new ArrayList<>());
        accounts.put(Account.Type.Security, new ArrayList<>());
        // Query

        return false;
    }

    /**
     * Get a list of a type of accounts
     * @param accountType account type
     * @return a list of accounts
     */
    public ArrayList<Account> getAccounts(Account.Type accountType){
        return null;
    }

    /**
     * Add an account
     * @param account account
     */
    public void addAccount(Account account){
        accounts.get(account.getType()).add(account);
    }

    /**
     * Check whether the customer can open a security account
     * @return true if the customer can open it
     */
    public boolean canOpenSecurity(){
        return false;
    }

    /**
     * Check whether the customer can maintain the security account
     * @return true if the customer can maintain it
     */
    public boolean maintainSecurity(){
        return false;
    }





}
