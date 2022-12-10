package DataAccessLayer;

import java.sql.Connection;
import java.sql.SQLException;

public interface OpenInterestDao {

    /**
     * add stock to open interest
     * @param clientId
     * @param stockId
     * @param numOfShare
     * @param price
     * @return
     */
    public int addStockToOpenInterest(Connection connection,int clientId, int stockId, double price, int numOfShare) throws SQLException;
}
