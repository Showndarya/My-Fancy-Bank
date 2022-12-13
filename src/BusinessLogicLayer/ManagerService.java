package BusinessLogicLayer;

import Models.Transaction.LoanTransaction;
import Models.Transaction.Transaction;
import Models.Users.Customer;
import dto.TableList;
import javafx.scene.control.Tab;

import java.sql.SQLException;
import java.util.List;

public interface ManagerService {
    public TableList getAllCustomers();
    public TableList getCustomersWithLoan();
    public TableList getDailyCurrentTransaction() throws SQLException;
    public TableList getDailyLoanTransaction() throws SQLException;

}
