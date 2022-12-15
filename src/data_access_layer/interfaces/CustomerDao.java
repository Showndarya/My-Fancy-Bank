package data_access_layer.interfaces;

import models.users.Customer;

import java.sql.SQLException;

public interface CustomerDao extends UserDao {
    public Customer getCustomerByID(int id) throws SQLException;
}
