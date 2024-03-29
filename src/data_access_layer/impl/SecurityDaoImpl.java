package data_access_layer.impl;

import utilities.BaseDao;
import data_access_layer.interfaces.SecurityDao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SecurityDaoImpl implements SecurityDao {

    /**
     * mondify the money for the client
     *
     * @param clientId
     * @param amount
     * @return
     */
    @Override
    public int modifyMoneyInSecurityAccount(Connection connection, int clientId, double amount) throws SQLException {

        Statement statement = null;
        String sql = "update security_account set money=" + amount + " where client_id=" + clientId;
        int i = BaseDao.executeUpdate(connection, sql, statement);
        BaseDao.close(null, statement, null);
        return i;
    }

    @Override
    public int createNewSecurityAccount(Connection connection, int clientId, double money) throws SQLException {

        Statement statement = null;
        String sql = "insert into security_account (`client_id`,`money`) values (" + clientId + "," + money + ")";
        int i = BaseDao.executeUpdate(connection, sql, statement);
        BaseDao.close(null, statement, null);
        return i;
    }

    @Override
    public double getCustomerMoney(int clientId) throws SQLException {
        Connection connection = BaseDao.getConnection();
        Statement statement = null;
        ResultSet resultSet = null;
        String sql = "select money from security_account where client_id=" + clientId;
        resultSet = BaseDao.execute(connection, sql, statement, resultSet);
        double result = 0;
        if (resultSet.next()) {
            result = resultSet.getDouble("money");
        }
        BaseDao.close(connection, statement, resultSet);
        return result;
    }

    @Override
    public boolean checkAccountExists(int clientId) throws SQLException {
        Connection connection = BaseDao.getConnection();
        Statement statement = null;
        ResultSet resultSet = null;
        String sql = "select count(*) as num from security_account where client_id=" + clientId;
        resultSet = BaseDao.execute(connection, sql, statement, resultSet);
        double result = 0;
        if (resultSet.next()) {
            result = resultSet.getInt("num");
        }
        BaseDao.close(connection, statement, resultSet);
        return result > 0;
    }
}
