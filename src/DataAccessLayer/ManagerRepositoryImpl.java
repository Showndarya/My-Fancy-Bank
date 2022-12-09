package DataAccessLayer;

import Models.Customer;
import Models.Stock;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class ManagerRepositoryImpl implements ManagerRepository{
    @Override
    public List<Customer> getAllCustomers() throws SQLException {
        return null;
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
