package data_access_layer.impl;


import data_access_layer.interfaces.StockDao;
import models.transaction.Stock;
import utilities.BaseDao;
import utilities.StockFactory;
import dto.UserStock;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StockDaoImpl implements StockDao {
    @Override
    public List<Stock> getAllStocks() throws SQLException {
        Connection connection = BaseDao.getConnection();
        Statement statement = null;
        ResultSet resultSet = null;
        String sql = "select * from stock";
        resultSet = BaseDao.execute(connection, sql, statement, resultSet);
        List<Stock> list = new ArrayList<>();
        Stock stock;
        while (resultSet.next()) {
            stock = new Stock();
            stock.setName(resultSet.getString("name"));
            stock.setTag(resultSet.getString("tag"));
            stock.setPrice(resultSet.getDouble("price"));
            list.add(stock);
        }

        BaseDao.close(connection, statement, resultSet);
        return list;

    }

    /**
     * get a stock by stock id
     *
     * @param tag
     * @return
     */
    @Override
    public Stock getStockByTag(String tag) throws SQLException {
        Connection connection = BaseDao.getConnection();
        Statement statement = null;
        ResultSet resultSet = null;
        String sql = "select * from stock where tag = '" + tag + "'";
        resultSet = BaseDao.execute(connection, sql, statement, resultSet);
        Stock stock = null;
        if (resultSet.next()) {
            stock = StockFactory.getStock(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("tag"), resultSet.getDouble("price"));
        }
        BaseDao.close(null, statement, resultSet);
        return stock;
    }

    @Override
    public void addStock(Connection connection, String name, String tag, double price) throws SQLException {
        Statement statement = null;
        String sql = "insert into stock (`name`,`tag`,`price`) values (\'" + name + "\',\'" + tag + "\'," + price + ")";
        int i = BaseDao.executeUpdate(connection, sql, statement);
        BaseDao.close(null, statement, null);
    }

    @Override
    public void updateStock(Connection connection, String tag, double price) throws SQLException {
        Statement statement = null;
        String sql = "update stock set price='" + price + "' where tag='" + tag + "'";
        BaseDao.executeUpdate(connection, sql, statement);
        BaseDao.close(null, statement, null);
    }

    @Override
    public void deleteStockByTag(Connection connection, String tag) throws SQLException {
        Statement statement = null;
        String sql = "delete from stock where tag='" + tag + "'";
        BaseDao.executeUpdate(connection, sql, statement);
        BaseDao.close(null, statement, null);
    }

    @Override
    public List<UserStock> getUserStock(Connection connection, int clientId) throws SQLException {
        Statement statement = null;
        ResultSet resultSet = null;
        String sql = "select `stock_id`,`tag`, avg(`purchase_price`) as 'avg_price', sum(`num_of_share`) as `total_share` from open_interest join stock s on open_interest.stock_id = s.id  where client_id= " + clientId + " group by stock_id";
        resultSet = BaseDao.execute(connection, sql, statement, resultSet);
        List<UserStock> list = new ArrayList<>();
        UserStock userStock;
        while (resultSet.next()) {
            double avg_price = resultSet.getDouble("avg_price");
            userStock = new UserStock(resultSet.getInt("stock_id"), resultSet.getString("tag"), resultSet.getInt("total_share"), Math.round(avg_price * 100.00) / 100.00);
            list.add(userStock);
        }
        return list;
    }


}
