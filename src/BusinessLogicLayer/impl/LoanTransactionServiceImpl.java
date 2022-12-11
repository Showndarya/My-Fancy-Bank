package BusinessLogicLayer.impl;

import BusinessLogicLayer.LoanTransactionService;
import DataAccessLayer.Implementation.LoanTransactionDaoImpl;
import DataAccessLayer.Interfaces.LoanTransactionDao;
import Models.Transaction.LoanTransaction;
import Models.Users.Customer;
import dto.TableList;

import java.sql.SQLException;
import java.util.List;

public class LoanTransactionServiceImpl implements LoanTransactionService {
    private LoanTransactionDao loanTransactionDao;

    public LoanTransactionServiceImpl() {
        this.loanTransactionDao = new LoanTransactionDaoImpl();
    }

    @Override
    public TableList getAllCustomersWithLoan() {
        List<Customer> list;
        try {
            list = loanTransactionDao.getAllCustomersWithLoan();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        TableList tableList = new TableList();
        tableList.setColumnsName(new Object[]{"User Id", "User Name"});
        Object[][] rowData = new Object[list.size()][];
        for(int i = 0; i < list.size(); i++){
            Customer customer = list.get(i);
            rowData[i] = new Object[]{customer.getId(), customer.getName()};
        }
        tableList.setRowData(rowData);
        return tableList;
    }

    @Override
    public TableList getCustomerLoan(Customer customer) {
        List<LoanTransaction> list;
        try {
            list = loanTransactionDao.getCustomerLoan(customer);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        TableList tableList = new TableList();
        tableList.setColumnsName(new Object[]{"Customer Name", "Collateral Name", "Amount", "Interest"});
        Object[][] rowData = new Object[list.size()][];
        for(int i = 0; i < list.size(); i++){
            LoanTransaction loanTransaction = list.get(i);
            rowData[i] = new Object[]{loanTransaction.getCustomer().getName(),
                    loanTransaction.getCollateral().getName(),
                    loanTransaction.getAmount(),
                    loanTransaction.getInterest()};
        }
        tableList.setRowData(rowData);
        return tableList;
    }
}
