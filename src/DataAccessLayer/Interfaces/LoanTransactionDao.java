package DataAccessLayer.Interfaces;

import Models.Users.Customer;
import Models.Transaction.LoanTransaction;

import java.sql.SQLException;
import java.util.List;

public interface LoanTransactionDao {
    public List<LoanTransaction> getLoanTransactions(Customer customer) throws SQLException;
}
