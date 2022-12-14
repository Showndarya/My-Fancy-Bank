package DataAccessLayer.impl;

import DataAccessLayer.BaseDao;
import DataAccessLayer.StockTransactionDao;
import dto.StockTransactionDetail;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
    public int addTransaction(Connection connection, int clientId, int stockId, double price, boolean transType, int numOfShare) throws SQLException {
        Statement statement = null;
        String sql = "insert into stock_transaction (`client_id`,`stock_id`,`price`,`transaction_type`,`number_of_share`) values (" + clientId + "," + stockId + "," + price + "," + transType + "," + numOfShare + ")";
        int i = BaseDao.executeUpdate(connection, sql, statement);
        BaseDao.close(null, statement, null);
        return i;
    }

    @Override
    public List<StockTransactionDetail> getTransactionDetail(int clientId, String simpleDate) throws SQLException {
        Connection connection = BaseDao.getConnection();
        List<StockTransactionDetail> list = new ArrayList<>();
        Statement statement = null;
        ResultSet resultSet = null;
        String[] strings = simpleDate.split("-");
        String sql = "select `name`,`tag`,`stock_transaction`.price,`transaction_type`,`number_of_share` from stock_transaction join stock s on s.id = stock_transaction.stock_id where (client_id=" + clientId + " and year(created_date)=" + strings[0] + " and month(created_date)=" + strings[1] + " and day(created_date)=" + strings[2] + ")";
        resultSet = BaseDao.execute(connection, sql, statement, resultSet);
        StockTransactionDetail stockTransactionDetail;
        while (resultSet.next()) {
            String transType = resultSet.getInt("transaction_type") == 0 ? "Buy" : "Sell";
            stockTransactionDetail = new StockTransactionDetail(resultSet.getString("name"), resultSet.getString("tag"), resultSet.getDouble("price"), transType, resultSet.getInt("number_of_share"));
            list.add(stockTransactionDetail);
        }
        BaseDao.close(connection, statement, resultSet);
        return list;
    }
}
