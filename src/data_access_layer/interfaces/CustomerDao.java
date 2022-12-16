package data_access_layer.interfaces;

import models.users.Customer;

import java.sql.SQLException;

/**
 * Dao for customers
 * extends: UserDao
 */
public interface CustomerDao extends UserDao {
    public Customer getCustomerByID(int id) throws SQLException;
}
