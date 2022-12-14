package BusinessLogicLayer;

import dto.StockTransactionDetail;
import dto.TableList;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public interface StockTransactionService {
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
    public void addTransaction(Connection connection, int clientId, int stockId, double price, boolean transType, int numOfShare) throws SQLException;


    /**
     * get all the transaction details on specific date
     *
     * @param clientId
     * @return
     */
    public TableList getTransactionDetails(int clientId, String simpleDate);
}
