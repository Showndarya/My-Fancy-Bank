package business_logic_layer.interfaces;

import dto.TableList;
import enums.UserType;
import models.users.Customer;

import java.sql.SQLException;

/**
 * Service for customers
 */
public interface CustomerService {
    public int getCustomerId(String name);

    public UserType getUserType(int id);

    public TableList getCustomerAsTableListByName(String name);

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

    public Customer getCustomerByID(int id);

}
