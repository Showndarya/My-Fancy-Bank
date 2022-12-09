package DataAccessLayer;

import Models.Customer;
import Models.Transaction.LoanTransaction;

import java.sql.SQLException;
import java.util.List;

public interface LoanTransactionRepository {
    public List<LoanTransaction> getLoanTransactions(Customer customer) throws SQLException;
}
