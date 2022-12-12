package DataAccessLayer.impl;


import DataAccessLayer.StockDao;
import Models.Stock;
import DataAccessLayer.BaseDao;
import Utilities.StockFactory;

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
        while (resultSet.next()){
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
        String sql = "select * from stock where tag = '"+tag+"'";
        resultSet = BaseDao.execute(connection, sql, statement, resultSet);
        Stock stock = null;
        if (resultSet.next()){
           stock= StockFactory.getStock(resultSet.getString("name"), resultSet.getString("tag"), resultSet.getDouble("price"));
        }
        BaseDao.close(null, statement, resultSet);
        return stock;
    }

    @Override
    public void addStock(Connection connection, String name, String tag, double price) throws SQLException {
        Statement statement=null;
        String sql = "insert into stock (`name`,`tag`,`price`) values (\'"+name+"\',\'"+tag+"\',"+price+")";
        int i = BaseDao.executeUpdate(connection,sql,statement);
        BaseDao.close(null,statement,null);
    }

    @Override
    public void updateStock(Connection connection, String tag,  double price) throws SQLException {
        Statement statement = null;
        String sql = "update stock set price='" + price + "' where tag='" + tag + "'";
        BaseDao.executeUpdate(connection, sql, statement);
        BaseDao.close(null, statement, null);
    }

    @Override
    public void deleteStockByTag(Connection connection, String tag) throws SQLException {
        Statement statement=null;
        String sql = "delete from stock where tag='" + tag + "'";
        BaseDao.executeUpdate(connection, sql, statement);
        BaseDao.close(null, statement, null);
    }


}
