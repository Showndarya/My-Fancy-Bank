package DataAccessLayer;

import BusinessLogicLayer.StockServiceImpl;
import Models.Stock;
import dto.StockList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StockRepositoryImpl implements StockRepository{
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


}
