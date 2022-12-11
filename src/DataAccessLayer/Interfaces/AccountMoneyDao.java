package DataAccessLayer.Interfaces;

import Models.MoneyType;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Hashtable;

public interface AccountMoneyDao {
    public double getMoney(MoneyType type, int account_id) throws SQLException;
    public HashMap<Integer, Double> getAllKindsOfMoney(int account_id) throws SQLException;

}
