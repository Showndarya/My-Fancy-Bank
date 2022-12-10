package DataAccessLayer.Interfaces;

import Models.Users.Customer;
import Models.Transaction.Transaction;

import java.sql.SQLException;
import java.util.List;

public interface CurrentTransactionDao {
    public List<Transaction> getAllDeposits(Customer customer) throws SQLException;
    public Boolean addDeposit(Customer customer, Transaction deposit) throws SQLException;
}
