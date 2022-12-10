package BusinessLogicLayer.impl;

import BusinessLogicLayer.SecurityService;
import DataAccessLayer.SecurityDao;
import DataAccessLayer.impl.SecurityDaoImpl;

import java.sql.Connection;
import java.sql.SQLException;

public class SecurityServiceImpl implements SecurityService {
    private SecurityDao securityDao;

    public SecurityServiceImpl() {
        securityDao = new SecurityDaoImpl();
    }

    /**
     * mondify the money for the client
     *
     * @param clientId
     * @param amount
     * @return
     */
    @Override
    public int modifyMoneyInSecurityAccount(Connection connection,int clientId, double amount) throws SQLException {
        int i = securityDao.modifyMoneyInSecurityAccount(connection,clientId, amount);
        return i;
    }

    @Override
    public int createNewSecurityAccount(Connection connection,int clientId, int money) throws SQLException {
        int i = securityDao.createNewSecurityAccount(connection,clientId, money);
        return i;
    }

    @Override
    public double getCustomerMoney(int clientId) throws SQLException {
        double customerMoney = securityDao.getCustomerMoney(clientId);
        return customerMoney;
    }
}
