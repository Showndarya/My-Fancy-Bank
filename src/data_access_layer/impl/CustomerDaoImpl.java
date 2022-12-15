package data_access_layer.impl;

import utilities.BaseDao;
import data_access_layer.interfaces.CustomerDao;
import enums.UserType;
import models.users.Customer;
import models.users.User;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {

    /**
     * Check whether there is a customer with "name"
     * @param name name
     * @return true or false
     * @throws SQLException
     */
    @Override
    public boolean hasUser(String name) throws SQLException {
        Connection connection = BaseDao.getConnection();
        Statement statement = null;
        ResultSet resultSet = null;
        String sql = "select id from user " +
                "where user_name = '" + name + "';";
        resultSet = BaseDao.execute(connection, sql, statement, resultSet);
        // return true if there is result
        return resultSet.next();
    }

    /**
     * You should first call hasUser to make sure that user with "name" exists, otherwise, this function will return null
     * @param name name
     * @return User
     * @throws SQLException
     */
    @Override
    public User getByName(String name) throws SQLException {
        Connection connection = BaseDao.getConnection();
        Statement statement = null;
        ResultSet resultSet = null;
        String sql = "select * from user " +
                "where user_name = '" + name + "';";
        resultSet = BaseDao.execute(connection, sql, statement, resultSet);
        Customer customer = null;
        if(resultSet.next()) {
            customer = new Customer();
            customer.setName(resultSet.getString("user_name"));
            customer.setId(resultSet.getInt("id"));
            customer.setPassword(resultSet.getString("password"));
            int userType = resultSet.getInt("user_type");
            if(userType == 1){
                customer.setType(UserType.Manager);
            }
            else if(userType == 2){
                customer.setType(UserType.Customer);
            }
        }
        return customer;
    }


    @Override
    public User getById(int id) throws SQLException {
        Connection connection = BaseDao.getConnection();
        Statement statement = null;
        ResultSet resultSet = null;
        String sql = "select * from user " +
                "where id = " + id + ";";
        resultSet = BaseDao.execute(connection, sql, statement, resultSet);
        Customer customer = null;
        if(resultSet.next()) {
            customer = new Customer();
            customer.setName(resultSet.getString("user_name"));
            customer.setId(resultSet.getInt("id"));
            customer.setPassword(resultSet.getString("password"));
            int userType = resultSet.getInt("user_type");
            if(userType == 1){
                customer.setType(UserType.Manager);
            }
            else if(userType == 2){
                customer.setType(UserType.Customer);
            }
        }
        return customer;
    }

    @Override
    public int add(Connection connection, String name, String password) throws SQLException {
        Statement statement = null;
        String sql = "insert into user (user_name, user_type, password, created_date, modified_date) values ('"+
                name + "'," +
                UserType.Customer.ordinal() + ",'" +
                password + "','" +
                java.sql.Date.valueOf(LocalDate.now()) + "','" +
                java.sql.Date.valueOf(LocalDate.now()) + "');";
        int affectRows = BaseDao.executeUpdate(connection, sql, statement);
        return affectRows;
    }

    @Override
    public Customer getCustomerByID(int id) throws SQLException {
        Connection connection = BaseDao.getConnection();
        Statement statement = null;
        ResultSet resultSet = null;
        String sql = "select * from user where id = " + id;
        resultSet = BaseDao.execute(connection, sql, statement, resultSet);
        Customer customer = null;
        while (resultSet.next()){
            customer = new Customer(resultSet.getInt("id"), resultSet.getString("user_name"));
        }
        BaseDao.close(connection, statement, resultSet);
        return customer;
    }
}
