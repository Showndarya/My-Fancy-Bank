package BusinessLogicLayer.impl;

import BusinessLogicLayer.CustomerService;
import DataAccessLayer.BaseDao;
import DataAccessLayer.Implementation.CustomerDaoImpl;
import DataAccessLayer.Interfaces.CustomerDao;
import Models.Users.Customer;
import Models.Users.User;

import java.sql.Connection;
import java.sql.SQLException;

public class CustomerServiceImpl implements CustomerService {
    private final CustomerDao customerDao;

    public CustomerServiceImpl(){
        customerDao = new CustomerDaoImpl();
    }

    @Override
    public int getCustomerId(String name) throws SQLException {
        Customer customer = null;
        try{
            customer = (Customer) customerDao.getByName(name);
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
        if(customer != null){
            return customer.getId();
        }
        else{
            return -1;
        }
    }

    @Override
    public boolean hasCustomer(String name) throws SQLException {
        try{
            return customerDao.hasUser(name);
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean addCustomer(String name, String password) throws SQLException {
        Connection connection = BaseDao.getConnection();
        try{
            // Check whether there is already a customer of name
            boolean has = customerDao.hasUser(name);
            if(has){
                return false;
            }
            // add customer
            int affectRows = customerDao.add(connection, name, password);
            if(affectRows < 1){
                return false;
            }
        } catch (SQLException e){
            try {
                e.printStackTrace();
                System.out.println("Error occur. Try to rollback");
                connection.rollback();
            } catch (SQLException ex) {
                System.out.println("Rollback failed");
            }
        } finally {
            BaseDao.close(connection, null, null);
        }
        return true;
    }

    @Override
    public boolean loginCustomer(String name, String password) throws SQLException {
        try{
            Customer customer = (Customer) customerDao.getByName(name);
            if(customer == null){
                return false;
            }
            return name.equals(customer.getName()) && password.equals(customer.getPassword());
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean registerCustomer(String name, String password) throws SQLException {
        try{
            Customer customer = (Customer) customerDao.getByName(name);
            if(customer == null){
                return false;
            }
           return addCustomer(name, password);
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
