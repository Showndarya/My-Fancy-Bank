package BusinessLogicLayer.impl;

import BusinessLogicLayer.OpenInterestService;
import DataAccessLayer.OpenInterestDao;
import DataAccessLayer.impl.OpenInterestDaoImpl;

import java.sql.Connection;
import java.sql.SQLException;

public class OpenInterestServiceImpl implements OpenInterestService {
    private OpenInterestDao openInterestDao;
    public OpenInterestServiceImpl() {
        openInterestDao = new OpenInterestDaoImpl();
    }

    @Override
    public int addToOpenInterest(Connection connection,int clientId, int stockId, double price, int numOfShare) throws SQLException {
        return openInterestDao.addStockToOpenInterest(connection,clientId,stockId,price,numOfShare);
    }
}
