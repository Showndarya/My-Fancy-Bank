package BusinessLogicLayer;

import Models.Users.Customer;

import java.sql.SQLException;

public interface CustomerService {
    public Customer getCustomer(String name) throws SQLException;

    /**
     * Check whether there is a customer with "name"
     * @param name customer name
     * @return true if there is
     * @throws SQLException
     */
    public boolean hasCustomer(String name) throws SQLException;

    /**
     * Add a new customer
     * @param name name
     * @param password password
     * @return 0:success, -1:fail
     * @throws SQLException
     */
    public int addCustomer(String name, String password) throws SQLException;

}
