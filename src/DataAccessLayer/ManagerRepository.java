package DataAccessLayer;

import Models.Customer;

import java.sql.SQLException;
import java.util.List;

public interface ManagerRepository {
    public List<Customer> getAllCustomers() throws SQLException;
    public List<Customer> getCustomersWithLoan() throws SQLException;
    public List<Customer> getDailyReport() throws SQLException;
}
