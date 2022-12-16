package business_logic_layer.interfaces;

import models.transaction.Collateral;
import models.transaction.LoanTransaction;
import models.users.Customer;
import dto.TableList;

// service for frontend to get loan transactions
public interface LoanTransactionService {

    // get loan transaction of a specific user
    public TableList getCustomerLoan(Customer customer);

    public int addLoan(Customer customer, Collateral collateral, int amount);
    public int deleteLoan(Customer customer, LoanTransaction loanTransaction);
}
