package business_logic_layer.impl;
/**
 * class with service of openinterst
 */

import business_logic_layer.interfaces.OpenInterestService;
import utilities.BaseDao;
import data_access_layer.interfaces.OpenInterestDao;
import data_access_layer.impl.OpenInterestDaoImpl;
import models.transaction.OpenInterest;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class OpenInterestServiceImpl implements OpenInterestService {
    private OpenInterestDao openInterestDao;

    public OpenInterestServiceImpl() {
        openInterestDao = new OpenInterestDaoImpl();
    }

    @Override
    public void addToOpenInterest(Connection connection, int clientId, int stockId, double price, int numOfShare) throws SQLException {
        openInterestDao.addStockToOpenInterest(connection, clientId, stockId, price, numOfShare);
    }

    @Override
    public int getOpenInterestNum(int clientId, int stockId) {
        try {
            System.out.println(clientId + " " + stockId);
            return openInterestDao.getNumOfShare(clientId, stockId);
        } catch (SQLException e) {
            System.out.println("get open interest failed");
        }
        return 0;
    }

    /**
     * num of share is the new number of shares
     *
     * @param connection
     * @param clientId
     * @param stockId
     * @param numOfShare
     */
    @Override
    public void updateOpenInterest(Connection connection, int id, int clientId, int stockId, int numOfShare) throws SQLException {
        openInterestDao.updateOpenInterest(connection, id, clientId, stockId, numOfShare);
    }

    @Override
    public void deleteOpenInterest(Connection connection, int id, int clientId, int stockId) throws SQLException {
        openInterestDao.deleteOpenInterest(connection, id, clientId, stockId);
    }

    @Override
    public List<OpenInterest> getOpenInterestOrderedByPurchasePrice(Connection connection, int clientId, int stockId) throws SQLException {
        return openInterestDao.getOpenInterestOrderedByPurchasePrice(connection, clientId, stockId);

    }

    public static void main(String[] args) throws SQLException {
        new OpenInterestServiceImpl().updateOpenInterest(BaseDao.getConnection(), 42, 1, 3, 50);
    }
}
