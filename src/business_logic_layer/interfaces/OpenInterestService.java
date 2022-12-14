package business_logic_layer.interfaces;

import models.transaction.OpenInterest;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface OpenInterestService {

    public void addToOpenInterest(Connection connection, int clientId, int stockId, double price, int numOfShare) throws SQLException;


    public int getOpenInterestNum(int clientId, int stockId);

    /**
     * num of share is the new number of shares
     *
     * @param connection
     * @param clientId
     * @param stockId
     * @param numOfShare
     */
    public void updateOpenInterest(Connection connection, int id, int clientId, int stockId, int numOfShare) throws SQLException;

    public void deleteOpenInterest(Connection connection, int id, int clientId, int stockId) throws SQLException;

    public List<OpenInterest> getOpenInterestOrderedByPurchasePrice(Connection connection, int clientId, int stockId) throws SQLException;


}
