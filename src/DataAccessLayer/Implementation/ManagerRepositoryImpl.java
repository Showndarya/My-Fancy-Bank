package DataAccessLayer.Implementation;

import DataAccessLayer.Interfaces.ManagerRepository;
import Models.Users.Customer;

import java.sql.SQLException;
import java.util.List;

public class ManagerRepositoryImpl implements ManagerRepository {
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
