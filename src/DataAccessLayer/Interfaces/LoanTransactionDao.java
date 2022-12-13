package DataAccessLayer.Interfaces;

import Models.Account.Account;
import Models.Account.SavingsAccount;
import Models.Transaction.Collateral;
import Models.Users.Customer;
import Models.Transaction.LoanTransaction;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface LoanTransactionDao {

    // get loan transaction of a specific user
    public List<LoanTransaction> getCustomerLoan(Connection connection, Customer customer) throws SQLException;

    // let customer add loan
    public int addLoan(Connection connection, Customer customer, Collateral collateral, int amount) throws SQLException;

    // let customer delete loan
    public int deleteLoan(Connection connection, Customer customer, LoanTransaction loanTransaction) throws SQLException;

}
