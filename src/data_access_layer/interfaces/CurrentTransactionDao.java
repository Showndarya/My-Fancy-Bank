package data_access_layer.interfaces;

import enums.TransactionType;
import models.users.Customer;
import models.transaction.Transaction;

import java.sql.SQLException;
import java.util.List;

/**
 * dao for transaction operations.
 */
public interface CurrentTransactionDao {
    public List<Transaction> getAllTransactions(Customer customer, TransactionType type) throws SQLException;
    public Boolean addTransaction(Customer customer, Transaction deposit) throws SQLException;
}
