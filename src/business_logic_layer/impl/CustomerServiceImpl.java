package business_logic_layer.impl;

import business_logic_layer.interfaces.CustomerService;
import business_logic_layer.interfaces.ManagerService;
import dto.TableList;
import enums.UserType;
import utilities.BaseDao;
import data_access_layer.impl.CustomerDaoImpl;
import data_access_layer.interfaces.CustomerDao;
import models.users.Customer;
import utilities.MD5Encryptor;

import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;

public class CustomerServiceImpl implements CustomerService {
    private static final int ENCRYPTION_PASSWORD_LENGTH = 16;
    private final CustomerDao customerDao;

    public CustomerServiceImpl(){
        customerDao = new CustomerDaoImpl();
    }

    @Override
    public int getCustomerId(String name){
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
    public UserType getUserType(int id) {
        Customer customer = null;
        try{
            customer = (Customer) customerDao.getById(id);
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
        return customer.getType();
    }

    @Override
    public TableList getCustomerAsTableListByName(String name) {
        Customer customer = null;
        try {
            customer = (Customer) customerDao.getByName(name);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        TableList tableList = new TableList();
        tableList.setColumnsName(new Object[]{"User Id", "User Name"});
        Object[][] rowData = new Object[1][];
        if (customer == null) {
            rowData[0] = new Object[]{"N/A", "N/A"};
            tableList.setRowData(rowData);
        }
        else {
            rowData[0] = new Object[]{customer.getId(), customer.getName()};
            tableList.setRowData(rowData);
        }
        return tableList;
    }

    @Override
    public boolean hasCustomer(String name){
        try{
            return customerDao.hasUser(name);
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean addCustomer(String name, String password){
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
    public boolean loginCustomer(String name, String password){
        try{
            Customer customer = (Customer) customerDao.getByName(name);
            if(customer == null){
                return false;
            }
            String encryptedPassword = Objects.requireNonNull(MD5Encryptor.getMD5(password));
            encryptedPassword = encryptedPassword.substring(0, Math.min(ENCRYPTION_PASSWORD_LENGTH, encryptedPassword.length()));
            return name.equals(customer.getName()) && encryptedPassword.equals(customer.getPassword());
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean registerCustomer(String name, String password){
        try{
            Customer customer = (Customer) customerDao.getByName(name);
            if(customer != null){
                return false;
            }
            String encryptedPassword = Objects.requireNonNull(MD5Encryptor.getMD5(password)).substring(0, ENCRYPTION_PASSWORD_LENGTH);
           return addCustomer(name, encryptedPassword);
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Customer getCustomerByID(int id) {
        try{
            Customer customer = customerDao.getCustomerByID(id);
            return customer;
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

}
