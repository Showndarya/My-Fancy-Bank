package DataAccessLayer.Implementation;

import DataAccessLayer.BaseDao;
import DataAccessLayer.Interfaces.CustomerDao;
import Enums.AccountType;
import Enums.UserType;
import Models.Users.Customer;
import Models.Users.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
            // query checking account

            // query savings account

            // query security account

        }
        return customer;
    }


    @Override
    public User getById(int id) throws SQLException {
        Connection connection = BaseDao.getConnection();
        Statement statement = null;
        ResultSet resultSet = null;
        String sql = "select * from user " +
                "where user_name = " + id + ";";
        resultSet = BaseDao.execute(connection, sql, statement, resultSet);
        Customer customer = null;
        if(resultSet.next()) {
            customer = new Customer();
            customer.setName(resultSet.getString("user_name"));
            customer.setId(resultSet.getInt("id"));
            // query checking account

            // query savings account

            // query security account

        }
        return customer;
    }

    @Override
    public int add(Connection connection, String name, String password) throws SQLException {
        Statement statement = null;
        String sql = "insert into user ('user_name', 'user_type', 'password') values ('"+ name + "',"+ UserType.Customer + ",'"+ password +"');";
        int affectRows = BaseDao.executeUpdate(connection, sql, statement);
        return affectRows;
    }
}
