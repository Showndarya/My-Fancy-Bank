package BusinessLogicLayer.impl;

import BusinessLogicLayer.LoanTransactionService;
import DataAccessLayer.BaseDao;
import DataAccessLayer.Implementation.LoanTransactionDaoImpl;
import DataAccessLayer.Interfaces.LoanTransactionDao;
import Models.MoneyType;
import Models.Transaction.Collateral;
import Models.Transaction.LoanTransaction;
import Models.Users.Customer;
import Utilities.StockPriceUtils;
import dto.TableList;

import java.sql.Connection;
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

    @Override
    public int addLoan(Connection connection, Customer customer, Collateral collateral, int amount) {
        try {
            int flag = 0;
            // add stock to open intereset
            flag += loanTransactionDao.addLoan(connection, customer, collateral, amount);
            if (flag < 2){
                throw new SQLException();
            }

        } catch (SQLException e) {
            try {
                e.printStackTrace();
                System.out.println("Error occur. Try to rollback");
                connection.rollback();
            } catch (SQLException ex) {
                System.out.println("Rollback failed");
            }
        }finally {
            BaseDao.close(connection, null, null);
        }
        return 1;
    }

    public static void main(String[] args) throws SQLException {
        Customer customer = new Customer("id", "name");
        Connection connection = BaseDao.getConnection();
        MoneyType moneyType = new MoneyType(1);
        Collateral collateral = new Collateral("house", moneyType, 100);
        new LoanTransactionServiceImpl().addLoan(connection, customer, collateral, 1000);
    }
}
