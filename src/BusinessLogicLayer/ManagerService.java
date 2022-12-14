package BusinessLogicLayer;

import Models.Transaction.LoanTransaction;
import Models.Transaction.Transaction;
import Models.Users.Customer;
import dto.TableList;

import java.sql.SQLException;
import java.util.List;

public interface ManagerService {
    public TableList getAllCustomers();

    public TableList getCustomersWithLoan();

    public TableList getDailyCurrentTransaction();

    public TableList getDailyLoanTransaction();

}
