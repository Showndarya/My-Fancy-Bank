package data_access_layer.interfaces;

import models.transaction.LoanTransaction;
import models.transaction.Transaction;
import models.users.Customer;

import java.sql.SQLException;
import java.util.List;

public interface ManagerDao {
    public List<Customer> getAllCustomers() throws SQLException;
    public List<Customer> getCustomersWithLoan() throws SQLException;
    public List<Transaction> getDailyCurrentTransaction() throws SQLException;
    public List<LoanTransaction> getDailyLoanTransaction() throws SQLException;
}
