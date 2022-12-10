package BusinessLogicLayer;

import java.sql.Connection;
import java.sql.SQLException;

public interface OpenInterestService {

    public int addToOpenInterest(Connection connection,int clientId, int stockId, double price, int numOfShare) throws SQLException;
}
