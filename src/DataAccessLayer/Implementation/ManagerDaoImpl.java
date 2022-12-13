package DataAccessLayer.Implementation;

import DataAccessLayer.BaseDao;
import DataAccessLayer.Interfaces.ManagerDao;
import Models.Stock;
import Models.Users.Customer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ManagerDaoImpl implements ManagerDao {
    @Override
    public List<Customer> getAllCustomers() throws SQLException {
        Connection connection = BaseDao.getConnection();
        Statement statement = null;
        ResultSet resultSet = null;
        String sql = "select * from user";
        resultSet = BaseDao.execute(connection, sql, statement, resultSet);
        List<Customer> list = new ArrayList<>();
        Customer customer;
        while (resultSet.next()){
            customer = new Customer(resultSet.getInt("id"), resultSet.getString("user_name"));
            list.add(customer);
        }
        BaseDao.close(connection, statement, resultSet);
        return list;
    }

    @Override
    public List<Customer> getCustomersWithLoan() throws SQLException {
        return null;
    }

    @Override
    public List<Customer> getDailyReport() throws SQLException {
        return null;
    }
}
