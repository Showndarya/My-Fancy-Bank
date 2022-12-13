package DataAccessLayer.Interfaces;

import Enums.AccountType;
import Models.Account.Account;
import Models.MoneyType;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

public interface AccountDao {
    public Account getById(int id) throws SQLException;
    public boolean hasAccount(int ownerId) throws SQLException;
    public Account getByOwnerId(int ownerId) throws SQLException;
    public int addAccount(Connection connection, int ownerId) throws SQLException;
    public int removeAccount(Connection connection, int id) throws SQLException;
//    public int modifyAccountMoney(Connection connection, int id, MoneyType type, double money) throws SQLException;
//    public HashMap<Integer, Double> getAllKindsOfMoney(int account_id) throws SQLException;
//    public double getMoney(MoneyType type, int account_id) throws SQLException;
//    public int setMoney(Connection connection, MoneyType type, int account_id) throws SQLException;

}
