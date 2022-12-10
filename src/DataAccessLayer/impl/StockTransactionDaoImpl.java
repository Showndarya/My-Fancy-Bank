package DataAccessLayer.impl;

import DataAccessLayer.BaseDao;
import DataAccessLayer.StockTransactionDao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class StockTransactionDaoImpl implements StockTransactionDao {


    /**
     * add transaction in the table
     *
     * @param clientId
     * @param stockId
     * @param price
     * @param transType
     * @param numOfShare
     * @return
     */
    @Override
    public int addTransaction(Connection connection,int clientId, int stockId, double price, boolean transType, int numOfShare) throws SQLException {
        Statement statement = null;
        String sql = "insert into stock_transaction (`client_id`,`stock_id`,`price`,`transaction_type`,`number_of_share`) values (" + clientId + "," + stockId + "," + price + "," + transType + "," + numOfShare + ")";
        int i = BaseDao.executeUpdate(connection, sql, statement);
        BaseDao.close(null, statement, null);
        return i;
    }
}
