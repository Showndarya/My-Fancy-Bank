package business_logic_layer.interfaces;

import dto.TableList;

import java.sql.Connection;
import java.sql.SQLException;

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


    /**
     * get all the transaction details
     *
     * @param clientId
     * @return
     */
    public TableList getTransactionDetails(int clientId);
}
