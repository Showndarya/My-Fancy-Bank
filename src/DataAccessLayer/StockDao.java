package DataAccessLayer;

import Models.Stock;

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
     * @param stockId
     * @return
     */
    public Stock getStockById(int stockId) throws SQLException;






}
