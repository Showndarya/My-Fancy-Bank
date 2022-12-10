package BusinessLogicLayer.impl;

import BusinessLogicLayer.StockTransactionService;
import DataAccessLayer.StockTransactionDao;
import DataAccessLayer.impl.StockTransactionDaoImpl;

import java.sql.Connection;
import java.sql.SQLException;

public class StockTransactionServiceImpl implements StockTransactionService {
    private StockTransactionDao stockTransactionDao;

    public StockTransactionServiceImpl() {
        stockTransactionDao = new StockTransactionDaoImpl();

    }

    /**
     * add transaction in the table
     *
     * @param clientId
     * @param stockId
     * @param price
     * @param transType
     * @param numOfShare
     * @return
     */
    @Override
    public int addTransaction(Connection connection,int clientId, int stockId, double price, boolean transType, int numOfShare) throws SQLException {
        int i = stockTransactionDao.addTransaction(connection,clientId, stockId, price, transType, numOfShare);
        return i;
    }

}
