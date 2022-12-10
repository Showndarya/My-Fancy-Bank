package DataAccessLayer.impl;

import DataAccessLayer.BaseDao;
import DataAccessLayer.OpenInterestDao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class OpenInterestDaoImpl implements OpenInterestDao {
    /**
     * add stock to open interest
     *
     * @param clientId
     * @param stockId
     * @param numOfShare
     * @param price
     * @return
     */
    @Override
    public int addStockToOpenInterest(Connection connection,int clientId, int stockId, double price, int numOfShare) throws SQLException {
        Statement statement = null;
        String sql = "insert into open_interest (`stock_id`,`client_id`,`purchase_price`,`num_of_share`)\n" +
                "values \n" +
                "    ("+stockId+","+clientId+","+price+","+numOfShare+")";
        int i = BaseDao.executeUpdate(connection, sql, statement);
        BaseDao.close(null, statement, null);
        return i;
    }
}
