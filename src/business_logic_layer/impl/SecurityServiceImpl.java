package business_logic_layer.impl;

import business_logic_layer.interfaces.SecurityService;
import data_access_layer.interfaces.SecurityDao;
import data_access_layer.impl.SecurityDaoImpl;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * class with service of security account
 */
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
    public void modifyMoneyInSecurityAccount(Connection connection, int clientId, double amount) throws SQLException {
        int i = securityDao.modifyMoneyInSecurityAccount(connection, clientId, amount);
    }

    @Override
    public int createNewSecurityAccount(Connection connection, int clientId, double money) throws SQLException {
        int i = securityDao.createNewSecurityAccount(connection, clientId, money);
        return i;
    }

    @Override
    public double getCustomerMoney(int clientId) throws SQLException {
        double customerMoney = Math.round(securityDao.getCustomerMoney(clientId) * 100.00) / 100.00;
        return customerMoney;
    }

    @Override
    public boolean checkAccountExists(int clientId) {
        boolean result = false;
        try {
            result = securityDao.checkAccountExists(clientId);
        } catch (SQLException e) {
            System.out.println("Check account num failed");
        }
        return result;
    }

}
