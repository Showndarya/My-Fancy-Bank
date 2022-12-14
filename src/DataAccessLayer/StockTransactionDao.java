package DataAccessLayer;

import dto.StockTransactionDetail;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface StockTransactionDao {
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
    public int addTransaction(Connection connection, int clientId, int stockId, double price, boolean transType, int numOfShare) throws SQLException;

    List<StockTransactionDetail> getTransactionDetail(int clientId, String simpleDate) throws SQLException;
}
