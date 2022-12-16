package data_access_layer.interfaces;

import models.transaction.OpenInterest;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * dao for open interest
 */
public interface OpenInterestDao {

    /**
     * add stock to open interest
     *
     * @param clientId
     * @param stockId
     * @param numOfShare
     * @param price
     * @return
     */
    public int addStockToOpenInterest(Connection connection, int clientId, int stockId, double price, int numOfShare) throws SQLException;

    int getNumOfShare(int cliendId, int stockId) throws SQLException;

    void updateOpenInterest(Connection connection, int id, int clientId, int stockId, int numOfShare) throws SQLException;

    void deleteOpenInterest(Connection connection, int id, int clientId, int stockId) throws SQLException;

    List<OpenInterest> getOpenInterestOrderedByPurchasePrice(Connection connection, int clientId, int stockId) throws SQLException;
}
