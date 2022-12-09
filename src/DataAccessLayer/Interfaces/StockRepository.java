package DataAccessLayer.Interfaces;

import Models.Stock;

import java.sql.SQLException;
import java.util.List;

public interface StockRepository {
    public List<Stock> getAllStocks() throws SQLException;
}
