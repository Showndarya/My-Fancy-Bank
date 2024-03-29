package data_access_layer.impl;

import utilities.BaseDao;
import data_access_layer.interfaces.MoneyTypeDao;
import models.transaction.MoneyType;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MoneyTypeDaoImpl implements MoneyTypeDao {
    @Override
    public ArrayList<MoneyType> getAllMoneyTypes() throws SQLException {
        Connection connection = BaseDao.getConnection();
        ResultSet results = null;

        String sql = "select * from money_type";
        results = BaseDao.execute(connection, sql, null, results);
        ArrayList<MoneyType> moneyTypes = new ArrayList<>();
        while (results.next()){
            MoneyType moneyType = new MoneyType(
                    results.getInt("id"),
                    results.getString("type"),
                    results.getString("symbol"));
            moneyTypes.add(moneyType);
        }

        BaseDao.close(connection, null, results);
        return moneyTypes;
    }

    @Override
    public ArrayList<MoneyType> getMoneyTypeByUserId(int userId) {
        return null;
    }

    @Override
    public MoneyType getMoneyTypeById(int moneyTypeId) {
        return null;
    }

    public int getMoneyTypeIdByType(String type) throws SQLException {
        Connection connection = BaseDao.getConnection();
        ResultSet results = null;

        String sql = "select * from money_type where type = '" + type + "'";
        results = BaseDao.execute(connection, sql, null, results);
        int id = 0;
        while (results.next()){
            id = results.getInt("id");
        }

        BaseDao.close(connection, null, results);
        return id;
    }
}
