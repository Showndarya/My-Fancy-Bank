package BusinessLogicLayer;

import Models.Users.Customer;

import java.sql.SQLException;

public interface CustomerService {
    public int getCustomerId(String name);

    /**
     * Check whether there is a customer with "name"
     * @param name customer name
     * @return true if there is
     */
    public boolean hasCustomer(String name);

    /**
     * Add a new customer
     * @param name name
     * @param password password
     * @return true if add successfully
     */
    public boolean addCustomer(String name, String password);

    /**
     * Login a customer by name and password
     * @param name
     * @param password
     * @return true if login successfully
     */
    public boolean loginCustomer(String name, String password);

    /**
     * Register a customer by name and password
     * @param name
     * @param password
     * @return true if register successfully
     */
    public boolean registerCustomer(String name, String password);

}
