package DataAccessLayer.Interfaces;

import Models.Transaction.Collateral;
import Models.Users.Customer;
import Models.Transaction.LoanTransaction;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface LoanTransactionDao {
    // get all customer loan transaction
    public List<Customer> getAllCustomersWithLoan() throws SQLException;

    // get loan transaction of a specific user
    public List<LoanTransaction> getCustomerLoan(Customer customer) throws SQLException;

    // let customer add loan
    public int addLoan(Connection connection, Customer customer, Collateral collateral, int amount) throws SQLException;

}
