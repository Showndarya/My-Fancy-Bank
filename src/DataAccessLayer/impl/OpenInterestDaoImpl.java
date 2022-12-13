package DataAccessLayer.impl;

import DataAccessLayer.BaseDao;
import DataAccessLayer.OpenInterestDao;
import Models.OpenInterest;
import Utilities.OpenInterestFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
    public int addStockToOpenInterest(Connection connection, int clientId, int stockId, double price, int numOfShare) throws SQLException {
        Statement statement = null;
        String sql = "insert into open_interest (`stock_id`,`client_id`,`purchase_price`,`num_of_share`)\n" +
                "values \n" +
                "    (" + stockId + "," + clientId + "," + price + "," + numOfShare + ")";
        int i = BaseDao.executeUpdate(connection, sql, statement);
        BaseDao.close(null, statement, null);
        return i;
    }

    @Override
    public int getNumOfShare(int cliendId, int stockId) throws SQLException {
        Connection connection = BaseDao.getConnection();
        Statement statement = null;
        ResultSet resultSet = null;

        String sql = "select sum(`num_of_share`) as numOfShare from open_interest where client_id=" + cliendId + " and stock_id=" + stockId + " group by stock_id";
        resultSet = BaseDao.execute(connection, sql, statement, resultSet);
        String numOfShare = null;
        if (resultSet.next()) {
            numOfShare = resultSet.getString("numOfShare");
        }
        BaseDao.close(null, statement, resultSet);
        return Integer.parseInt(numOfShare);
    }

    @Override
    public void updateOpenInterest(Connection connection, int id, int clientId, int stockId, int numOfShare) throws SQLException {
        Statement statement = null;
        String sql = "update open_interest set num_of_share=" + numOfShare + " where id=" + id + " and client_id=" + clientId + " and stock_id=" + stockId;
        System.out.println(sql);
        BaseDao.executeUpdate(connection, sql, statement);
        BaseDao.close(null, statement, null);
    }

    @Override
    public void deleteOpenInterest(Connection connection, int id, int clientId, int stockId) throws SQLException {
        Statement statement = null;
        String sql = "delete from open_interest where id=" + id + " and stock_id=" + stockId + " and client_id=" + clientId;
        BaseDao.executeUpdate(connection, sql, statement);
        BaseDao.close(null, statement, null);
    }

    @Override
    public List<OpenInterest> getOpenInterestOrderedByPurchasePrice(Connection connection, int clientId, int stockId) throws SQLException {
        List<OpenInterest> list = new ArrayList<>();
        OpenInterest openInterest;
        Statement statement = null;
        ResultSet resultSet = null;
        String sql = "select * from open_interest where stock_id=" + stockId + " and client_id=" + clientId + " order by purchase_price";
        resultSet = BaseDao.execute(connection, sql, statement, resultSet);
        while (resultSet.next()) {
            openInterest = OpenInterestFactory.getOpenInterest(resultSet.getInt("id"), resultSet.getInt("client_id"), resultSet.getInt("stock_id"), resultSet.getDouble("purchase_price"), resultSet.getInt("num_of_share"));
            list.add(openInterest);
        }
        return list;
    }

}
