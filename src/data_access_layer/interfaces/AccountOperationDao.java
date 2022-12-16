package data_access_layer.interfaces;

import enums.TransactionType;
import dto.UserAccount;

import java.sql.SQLException;
import java.util.ArrayList;
/**
 * dao for account operations
 */
public interface AccountOperationDao {
    public Boolean changeBalance(TransactionType type, int accountId, double amount, int moneyType) throws SQLException;
    public double getBalance(int accountId, int moneyType) throws SQLException;

    public ArrayList<UserAccount> getAccountsByIdWithBalance(int userId) throws SQLException;
}
