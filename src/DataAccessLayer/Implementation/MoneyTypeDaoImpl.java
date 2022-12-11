package DataAccessLayer.Implementation;

import DataAccessLayer.BaseDao;
import DataAccessLayer.Interfaces.MoneyTypeDao;
import Models.MoneyType;
import Models.Transaction.Transaction;

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
}
