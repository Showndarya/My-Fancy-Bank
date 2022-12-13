package BusinessLogicLayer.impl;

import BusinessLogicLayer.ManagerService;
import DataAccessLayer.BaseDao;
import DataAccessLayer.Implementation.ManagerDaoImpl;
import DataAccessLayer.Interfaces.ManagerDao;
import Models.MoneyType;
import Models.Transaction.Collateral;
import Models.Transaction.LoanTransaction;
import Models.Users.Customer;
import dto.TableList;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

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
    public TableList getDailyCurrentTransaction() throws SQLException {
        return null;
    }

    @Override
    public TableList getDailyLoanTransaction() throws SQLException {
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
        new ManagerDaoImpl().getAllCustomers();
    }
}
