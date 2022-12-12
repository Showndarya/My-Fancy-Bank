package BusinessLogicLayer.impl;

import BusinessLogicLayer.CustomerService;
import Models.Users.Customer;

import java.sql.SQLException;

public class CustomerServiceImpl implements CustomerService {
    @Override
    public Customer getCustomer(String name) throws SQLException {
        return null;
    }

    @Override
    public boolean hasCustomer(String name) throws SQLException {
        return false;
    }

    @Override
    public int addCustomer(String name, String password) throws SQLException {
        return 0;
    }

    @Override
    public boolean loginCustomer(String name, String password) throws SQLException {
        return false;
    }

    @Override
    public boolean registerCustomer(String name, String password) throws SQLException {
        return false;
    }
}
