package business_logic_layer.interfaces;

import dto.TableList;

public interface ManagerService {
    public TableList getAllCustomers();

    public TableList getCustomersWithLoan();

    public TableList getDailyCurrentTransaction();

    public TableList getDailyLoanTransaction();

}
