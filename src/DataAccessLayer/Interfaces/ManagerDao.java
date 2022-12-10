package DataAccessLayer.Interfaces;

import Models.Users.Customer;

import java.sql.SQLException;
import java.util.List;

public interface ManagerDao {
    public List<Customer> getAllCustomers() throws SQLException;
    public List<Customer> getCustomersWithLoan() throws SQLException;
    public List<Customer> getDailyReport() throws SQLException;
}
