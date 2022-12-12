package BusinessLogicLayer.impl;

import BusinessLogicLayer.OpenInterestService;
import DataAccessLayer.OpenInterestDao;
import DataAccessLayer.impl.OpenInterestDaoImpl;
import Models.OpenInterest;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class OpenInterestServiceImpl implements OpenInterestService {
    private OpenInterestDao openInterestDao;

    public OpenInterestServiceImpl() {
        openInterestDao = new OpenInterestDaoImpl();
    }

    @Override
    public int addToOpenInterest(Connection connection, int clientId, int stockId, double price, int numOfShare) throws SQLException {
        return openInterestDao.addStockToOpenInterest(connection, clientId, stockId, price, numOfShare);
    }

    @Override
    public int getOpenInterestNum(int clientId, int stockId) {
        try {
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
    public List<OpenInterest> getOpenInterestOrderedByPurchasePrice(Connection connection, int clientId, int stockId) {
        openInterestDao.getOpenInterestOrderedByPurchasePrice(connection, clientId, stockId);
    }
}
