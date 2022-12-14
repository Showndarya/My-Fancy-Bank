package business_logic_layer.interfaces;

import java.sql.Connection;
import java.sql.SQLException;

public interface SecurityService {
    /**
     * mondify the money for the client
     *
     * @param clientId
     * @param amount
     * @return
     */
    public void modifyMoneyInSecurityAccount(Connection connection, int clientId, double amount) throws SQLException;


    public int createNewSecurityAccount(Connection connection, int clientId, int money) throws SQLException;

    public double getCustomerMoney(int clientId) throws SQLException;
}
