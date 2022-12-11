package DataAccessLayer.Interfaces;

import Enums.TransactionType;
import Models.Users.Customer;
import Models.Transaction.Transaction;

import java.sql.SQLException;
import java.util.List;

public interface CurrentTransactionDao {
    public List<Transaction> getAllTransactions(Customer customer, TransactionType type) throws SQLException;
    public Boolean addTransaction(Customer customer, Transaction deposit) throws SQLException;
}
