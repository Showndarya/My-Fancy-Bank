package DataAccessLayer.Interfaces;

import Models.Users.Customer;
import Models.Transaction.Loan;

import java.sql.SQLException;
import java.util.List;

public interface LoanTransactionDao {
    public List<Loan> getLoanTransactions(Customer customer) throws SQLException;
}
