package DataAccessLayer.Interfaces;

import Models.Transaction.LoanTransaction;
import Models.Transaction.Transaction;
import Models.Users.Customer;

import java.sql.SQLException;
import java.util.List;

public interface ManagerDao {
    public List<Customer> getAllCustomers() throws SQLException;
    public List<Customer> getCustomersWithLoan() throws SQLException;
    public List<Transaction> getDailyCurrentTransaction() throws SQLException;
    public List<LoanTransaction> getDailyLoanTransaction() throws SQLException;
}
