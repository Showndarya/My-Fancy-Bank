package BusinessLogicLayer;

import Models.Transaction.LoanTransaction;
import Models.Users.Customer;
import dto.TableList;

import java.sql.SQLException;
import java.util.List;

public interface LoanTransactionService {
    // get all customer loan transaction
    public TableList getAllCustomersWithLoan();

    // get loan transaction of a specific user
    public TableList getCustomerLoan(Customer customer);
}
