package BusinessLogicLayer;

import Enums.AccountType;
import Models.Account.Account;

public interface AccountService {
    public Account getAccount(int ownerId, AccountType type);
    public Account hasAccount(int ownerId, AccountType type);

    /**
     * Remove a specific type of account
     * @param ownerId
     * @param type
     * @return
     */
    public Account removeAccount(int ownerId, AccountType type);

    /**
     * Create a specific type of account
     * @param ownerId
     * @param type
     * @return true:succeed, false:fail
     */
    public boolean createAccount(int ownerId, AccountType type);

}
