package data_access_layer.interfaces;

import models.transaction.MoneyType;

import java.sql.SQLException;
import java.util.HashMap;

public interface AccountMoneyDao {
    public double getMoney(MoneyType type, int account_id) throws SQLException;
    public HashMap<Integer, Double> getAllKindsOfMoney(int account_id) throws SQLException;

}
