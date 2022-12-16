package business_logic_layer.impl;

import business_logic_layer.interfaces.ManagerService;
import utilities.BaseDao;
import data_access_layer.impl.ManagerDaoImpl;
import data_access_layer.interfaces.ManagerDao;
import models.transaction.LoanTransaction;
import models.transaction.Transaction;
import models.users.Customer;
import dto.TableList;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

// service for frontend to get manager functions
public class ManagerServiceImpl implements ManagerService {
    private ManagerDao managerDao;

    public ManagerServiceImpl() {
        this.managerDao = new ManagerDaoImpl();
    }
    @Override
    public TableList getAllCustomers() {
        Connection connection = BaseDao.getConnection();
        List<Customer> list;
        try {
            list = managerDao.getAllCustomers();
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
    public TableList getCustomersWithLoan() {
        List<Customer> list;
        try {
            list = managerDao.getCustomersWithLoan();
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
    public TableList getDailyCurrentTransaction(){
        List<Transaction> list;
        try {
            list = managerDao.getDailyCurrentTransaction();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        TableList tableList = new TableList();
        tableList.setColumnsName(new Object[]{"Customer", "Transaction Type", "Amount"});
        Object[][] rowData = new Object[list.size()][];
        for(int i = 0; i < list.size(); i++){
            Transaction transaction = list.get(i);
            rowData[i] = new Object[]{transaction.getCustomer().getName(),
                    transaction.getTransactionType().toString(),
                    transaction.getAmount()};
        }
        tableList.setRowData(rowData);
        return tableList;
    }

    @Override
    public TableList getDailyLoanTransaction(){
        List<LoanTransaction> list;
        try {
            list = managerDao.getDailyLoanTransaction();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        TableList tableList = new TableList();
        tableList.setColumnsName(new Object[]{"Customer", "Collateral", "Loan Amount"});
        Object[][] rowData = new Object[list.size()][];
        for(int i = 0; i < list.size(); i++){
            LoanTransaction loanTransaction = list.get(i);
            rowData[i] = new Object[]{loanTransaction.getCustomer().getName(),
                loanTransaction.getCollateral().getName(),
                loanTransaction.getAmount()};
        }
        tableList.setRowData(rowData);
        return tableList;
    }


    public static void main(String[] args) throws SQLException {
        ManagerService managerService = new ManagerServiceImpl();
        TableList daily = managerService.getDailyCurrentTransaction();

        System.out.println("");
    }
}
