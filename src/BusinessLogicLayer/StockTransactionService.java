package BusinessLogicLayer;

import java.sql.Connection;
import java.sql.SQLException;

public interface StockTransactionService {
    /**
     * add transaction in the table
     * @param clientId
     * @param stockId
     * @param price
     * @param transType
     * @param numOfShare
     * @return
     */
    public int addTransaction(Connection connection,int clientId, int stockId, double price, boolean transType, int numOfShare) throws SQLException;
}
