package DataAccessLayer.impl;

import DataAccessLayer.BaseDao;
import DataAccessLayer.StockDao;
import Models.Stock;
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
     * @param stockId
     * @return
     */
    @Override
    public Stock getStockById(int stockId) throws SQLException {
        Connection connection = BaseDao.getConnection();
        Statement statement = null;
        ResultSet resultSet = null;
        String sql = "select * from stock where stock_id = "+stockId;
        resultSet = BaseDao.execute(connection, sql, statement, resultSet);
        Stock stock = null;
        if (resultSet.next()){
            StockFactory.getStock(resultSet.getString("name"), resultSet.getString("tag"), resultSet.getDouble("price"));
        }
        BaseDao.close(null, statement, resultSet);
        return stock;
    }


}
