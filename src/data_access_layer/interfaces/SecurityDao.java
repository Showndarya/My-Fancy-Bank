package data_access_layer.interfaces;

import java.sql.Connection;
import java.sql.SQLException;

public interface SecurityDao {

    /**
     * mondify the money for the client
     * @param clientId
     * @param amount
     * @return
     */
    public int modifyMoneyInSecurityAccount(Connection connection,int clientId, double amount) throws SQLException;


    public int createNewSecurityAccount(Connection connection,int clientId, double money) throws SQLException;


    public double getCustomerMoney(int clientId) throws SQLException;
}
