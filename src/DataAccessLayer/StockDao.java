package DataAccessLayer;

import Models.Stock;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface StockDao {
    /**
     * get all stocks in the database
     * @return
     * @throws SQLException
     */
    public List<Stock> getAllStocks() throws SQLException;

    /**
     * get a stock by stock id
     * @param tag
     * @return
     */
    public Stock getStockByTag(String tag) throws SQLException;


    void addStock(Connection connection, String name, String tag, double price) throws SQLException;

    public void updateStock(Connection connection, String tag, double price) throws SQLException;

    public void deleteStockByTag(Connection connection, String tag) throws SQLException;
}
