package DataAccessLayer;

import Models.Customer;
import Models.Transaction.Transaction;

import java.sql.SQLException;
import java.util.List;

public interface CurrentTransactionRepository {
    public List<Transaction> getAllDeposits(Customer customer) throws SQLException;
    public Boolean addDeposit(Customer customer, Transaction deposit) throws SQLException;
}
