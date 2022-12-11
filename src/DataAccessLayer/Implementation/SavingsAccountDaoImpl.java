package DataAccessLayer.Implementation;

import DataAccessLayer.Interfaces.SavingsAccountDao;
import Enums.AccountType;
import Models.Account.Account;
import Models.MoneyType;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

public class SavingsAccountDaoImpl implements SavingsAccountDao {
    @Override
    public Account getById(int id) throws SQLException {
        return null;
    }

    @Override
    public boolean hasAccount(int ownerId, AccountType type) throws SQLException {
        return false;
    }

    @Override
    public Account getByOwnerIdAndType(int ownerId, AccountType type) throws SQLException {
        return null;
    }

    @Override
    public int addAccount(int ownerId, AccountType type) throws SQLException {
        return 0;
    }

    @Override
    public int removeAccount(int id) throws SQLException {
        return 0;
    }

    @Override
    public int modifyAccountMoney(Connection connection, int id, MoneyType type, double money) throws SQLException {
        return 0;
    }

    @Override
    public HashMap<Integer, Double> getAllKindsOfMoney(int account_id) throws SQLException {
        return null;
    }

    @Override
    public double getMoney(MoneyType type, int account_id) throws SQLException {
        return 0;
    }

    @Override
    public int setMoney(MoneyType type, int account_id) throws SQLException {
        return 0;
    }
}
