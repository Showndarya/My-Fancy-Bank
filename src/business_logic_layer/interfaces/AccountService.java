package business_logic_layer.interfaces;

import enums.AccountType;

import java.util.ArrayList;

public interface AccountService {
    public int getAccountId(int ownerId, AccountType type);
    public boolean hasAccount(int ownerId, AccountType type);

    /**
     * Remove a specific type of account
     * @param ownerId ownerId
     * @param type type
     * @return true
     */
    public boolean removeAccount(int ownerId, AccountType type);

    /**
     * Create a specific type of account
     * @param ownerId ownerId
     * @param type type
     * @return true:succeed, false:fail
     */
    public boolean createAccount(int ownerId, AccountType type, ArrayList<Integer> moneyTypes);

}
